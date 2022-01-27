package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.PaymentServiceFeignClientService;
import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClientService;
import com.epam.apigatewayui.model.PaymentModelAttribute;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController extends BaseController {

    public static final String SUCCESS_URL = "pay-success";
    public static final String CANCEL_URL = "pay/cancel";

    @Autowired
    private RequestOrderServiceFeignClientService requestOrderServiceFeignClientService;
    @Autowired
    private PaymentServiceFeignClientService paymentServiceFeignClientService;

    @RequestMapping(value = "/client-cabinet")
    public String openClientCabinetPage(HttpServletRequest request) {
        request.setAttribute("clientRequests", requestOrderServiceFeignClientService.getClientRequestsForView(getLoggedUser().getId()));
        request.setAttribute("clientOrders", requestOrderServiceFeignClientService.getClientOrdersForView(getLoggedUser().getId()));
        return openPage("clientcabinet");
    }

    @RequestMapping(value = "/client-request")
    public String doRequest(@RequestParam(name = "persons") String persons,
                            @RequestParam(name = "roomClass") String roomClass,
                            @RequestParam(name = "datefilter") String dateFilter) {

        requestOrderServiceFeignClientService.insertRequest(getLoggedUser(), Integer.valueOf(persons), roomClass, dateFilter);
        return "forward:/client-cabinet";
    }

    @GetMapping("/invoice")
    public String openInvoiceScreen(Model model,
                                    @RequestParam(value = "orderID") String orderID,
                                    @RequestParam(value = "requestID") String requestID,
                                    @RequestParam(value = "roomID") String roomID,
                                    @RequestParam(value = "roomClass") String roomClass,
                                    @RequestParam(value = "dateFrom") String dateFrom,
                                    @RequestParam(value = "dateTo") String dateTo) {
        model.addAttribute("orderID", orderID);
        model.addAttribute("requestID", requestID);
        model.addAttribute("roomID", roomID);
        model.addAttribute("roomClass", roomClass);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("roomPrice", requestOrderServiceFeignClientService.getRoomPriceByRoomID(Integer.parseInt(roomID)));
        model.addAttribute("payment", new PaymentModelAttribute());
        return "invoice";
    }


    @PostMapping("/pay")
    public String payment(@ModelAttribute("payment") PaymentModelAttribute paymentModelAttribute) throws PayPalRESTException {
        System.out.println("in pay");
        System.out.println(paymentModelAttribute.toString());
        Payment payment = paymentServiceFeignClientService.createPayment(
                Double.parseDouble(paymentModelAttribute.getRoomPrice()),
                "USD",
                "paypal",
                "sale",
                String.format("Payment for room booking as per %s made by %s", paymentModelAttribute.getOrderID(), paymentModelAttribute.getClientID()),
                "http://localhost:8080/" + CANCEL_URL,
                "http://localhost:8080/" + SUCCESS_URL);
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                return "redirect:" + link.getHref();
            }
        }

        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "index";
    }

    @GetMapping("/pay-success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        System.out.println("in successPay");
        Payment payment = paymentServiceFeignClientService.executePayment(paymentId, payerId);
        System.out.println(payment.toJSON());
        if (payment.getState().equals("approved")) {
            System.out.println("in successPay - 2");
            return "redirect:/client-cabinet";
        }
        System.out.println("in successPay - 3");
        return "redirect:/";
    }
}
