package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.PaymentServiceFeignClientService;
import com.epam.apigatewayui.feignservice.RequestOrderServiceFeignClientService;
import com.epam.apigatewayui.model.OrderView;
import com.epam.apigatewayui.model.PaymentModelAttribute;
import com.epam.apigatewayui.model.RequestView;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ClientController extends BaseController {

    public static final String SUCCESS_URL = "pay-success";
    public static final String CANCEL_URL = "pay/cancel";

    @Autowired
    private RequestOrderServiceFeignClientService requestOrderServiceFeignClientService;
    @Autowired
    private PaymentServiceFeignClientService paymentServiceFeignClientService;

    @RequestMapping(value = "/client-cabinet")
    public String openClientCabinetPage(@RequestParam(value = "serviceErrorMessage", required = false) String serviceErrorMessage, HttpServletRequest request) {

        request.setAttribute("serviceErrorMessage", serviceErrorMessage);
        ResponseEntity<List<RequestView>> requestViewResponseEntity = requestOrderServiceFeignClientService.getClientRequestsForView(getLoggedUser().getId());
        checkForServiceError(requestViewResponseEntity.getStatusCode(), request);
        request.setAttribute("clientRequests", requestViewResponseEntity.getBody());

        ResponseEntity<List<OrderView>> orderViewResponseEntity = requestOrderServiceFeignClientService.getClientOrdersForView(getLoggedUser().getId());
        checkForServiceError(orderViewResponseEntity.getStatusCode(), request);
        request.setAttribute("clientOrders", orderViewResponseEntity.getBody());
        return openPage("clientcabinet");
    }

    @RequestMapping(value = "/client-request")
    public String doRequest(@RequestParam(name = "persons") String persons,
                            @RequestParam(name = "roomClass") String roomClass,
                            @RequestParam(name = "datefilter") String dateFilter,
                            HttpServletRequest request) {

        checkForServiceError(requestOrderServiceFeignClientService.insertRequest(getLoggedUser(), Integer.valueOf(persons), roomClass, dateFilter), request);
        return "forward:/client-cabinet";
    }

    @GetMapping("/invoice")
    public String openInvoiceScreen(Model model,
                                    @RequestParam(value = "orderID") String orderID,
                                    @RequestParam(value = "requestID") String requestID,
                                    @RequestParam(value = "roomID") String roomID,
                                    @RequestParam(value = "roomClass") String roomClass,
                                    @RequestParam(value = "dateFrom") String dateFrom,
                                    @RequestParam(value = "dateTo") String dateTo,
                                    HttpServletRequest request) {
        model.addAttribute("orderID", orderID);
        model.addAttribute("requestID", requestID);
        model.addAttribute("roomID", roomID);
        model.addAttribute("roomClass", roomClass);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);

        ResponseEntity<Double> roomPriceResponseEntity = requestOrderServiceFeignClientService.getRoomPriceByRoomID(Integer.parseInt(roomID));
        checkForServiceError(roomPriceResponseEntity.getStatusCode(), request);
        model.addAttribute("roomPrice", roomPriceResponseEntity.getBody());
        model.addAttribute("payment", new PaymentModelAttribute());
        return openPage("invoice");
    }


    @PostMapping("/pay")
    public String payment(@ModelAttribute("payment") PaymentModelAttribute paymentModelAttribute, HttpServletRequest request) {

        ResponseEntity<Payment> payment = paymentServiceFeignClientService.createPayment(
                Double.parseDouble(paymentModelAttribute.getRoomPrice()),
                "USD",
                "paypal",
                "sale",
                String.format("Payment for room booking as per order#%s", paymentModelAttribute.getOrderID()),
                "http://localhost:8080/" + CANCEL_URL,
                "http://localhost:8080/" + SUCCESS_URL);
        System.out.println("PAYMENT -> " + payment.toString());

        if (checkForServiceError(payment.getStatusCode(), request)) {
            System.out.println("payment error");
            return "redirect:/client-cabinet?serviceErrorMessage=" + request.getAttribute("serviceErrorMessage");
//            return "redirect:/client-cabinet?serviceErrorMessage=" + "Payment Service Temporally Unvilable";
        }
        for (Links link : payment.getBody().getLinks()) {
            if (link.getRel().equals("approval_url")) {
                System.out.println("payment has been created");
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
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request) {

        ResponseEntity<Payment> paymentResponseEntity = paymentServiceFeignClientService.executePayment(paymentId, payerId);
        if (checkForServiceError(paymentResponseEntity.getStatusCode(), request)) {
            return "redirect:/client-cabinet?serviceErrorMessage=" + request.getAttribute("serviceErrorMessage");
//            return "redirect:/client-cabinet?serviceErrorMessage=" + "Payment Service Temporally Unvilable";
        }
        Payment payment = paymentResponseEntity.getBody();
        if (payment.getState().equals("approved")) {
            System.out.println("intent -> " + payment.getIntent());
            System.out.println("note to payer -> " + payment.getNoteToPayer());
            System.out.println("state -> " + payment.getState());
            System.out.println("payment has been successfully submitted");
            paymentResponseEntity.getBody().getTransactions().forEach(System.out::println);
            String descr = paymentResponseEntity.getBody().getTransactions().get(0).getDescription();
            System.out.println("description->" + descr);
            System.out.println("order id -> " + getOrderIdFromMessage(descr));
            requestOrderServiceFeignClientService.changeOrderStatus(Integer.parseInt(getOrderIdFromMessage(descr)));
            return "redirect:/client-cabinet";
        }
        return "redirect:/";
    }

    public String getOrderIdFromMessage(String description) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(description);

        String res = "";
        while (m.find()) {
            res = m.group();
        }
        return res;
    }
}
