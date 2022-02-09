package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.feignservice.CommonServiceFeignClientService;
import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.model.UserDataWhileLogin;
import com.epam.apigatewayui.model.UserDataWhileRegistration;
import com.epam.apigatewayui.service.IInputValidation;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@Slf4j
public class CommonActivityController extends BaseController {

    @Autowired
    private CommonServiceFeignClientService commonUserActivityServiceClient;
    @Autowired
    private IInputValidation inputValidation;
    @Autowired
    private Gson gson;

    @GetMapping("/")
    public String showHome() {
        return openPage("index");
    }

    @GetMapping("/page")
    public String menuPageNavigate(@RequestParam(name = "name") String pageName, Model model) {

        setAttributes();
        if (pageName.equals("signup")) {
            model.addAttribute("registration", new UserDataWhileRegistration());
        }
        if (pageName.equals("login")) {
            model.addAttribute("login", new UserDataWhileLogin());
        }
        return openPage(pageName);
    }

    @RequestMapping("/command")
    public String doCommand(@RequestParam(name = "name") String commandName) {

        log.info("the following command detected -> {}", commandName);
        return "forward:/" + commandName;
    }

    @RequestMapping(value = "/log-in")
    public String login(
            @Valid @ModelAttribute("login") UserDataWhileLogin userDataWhileLogin,
            @RequestParam(name = "loginAndCompleteRequest", required = false) String loginAndCompleteRequest,
            HttpServletRequest request, Model model) {

        ResponseEntity<Object> responseEntity = commonUserActivityServiceClient.getUserWithStatusCode(userDataWhileLogin);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        System.out.println("first status code -> " + httpStatus);
        if (httpStatus.equals(HttpStatus.OK)) {
            String body = Objects.requireNonNull(responseEntity.getBody()).toString();
            User user = gson.fromJson(body, User.class);
            request.getSession().setAttribute("user", user);
            if (user.getUserType().equals("CLIENT")) {
                if (Boolean.parseBoolean(loginAndCompleteRequest)) {
                    return "forward:/client-request";
                }
                return "redirect:/client-cabinet";
            } else {
                return "redirect:/admin-cabinet";
            }
        } else {
            System.out.println("status code -> " + httpStatus.value());
            switch (httpStatus.value()) {
                case 403:
                    model.addAttribute("errorWhileLogin", true);
                    model.addAttribute("errorWhileLoginMessage", "login.wrong.password");
                    return openPage("login");
                case 404:
                    model.addAttribute("errorWhileLogin", true);
                    model.addAttribute("errorWhileLoginMessage", "login.no.such.user");
                    return openPage("login");
                case 503:
                    request.setAttribute("serviceErrorMessage", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
                    return openPage("login");
                default:
                    return openPage("login");
            }
        }


//        try {
//            String body = Objects.requireNonNull(commonUserActivityServiceClient.getUserWithStatusCode(userDataWhileLogin).getBody()).toString();
//            User user = gson.fromJson(body, User.class);
//            request.getSession().setAttribute("user", user);
//            if (user.getUserType().equals("CLIENT")) {
//                if (Boolean.parseBoolean(loginAndCompleteRequest)) {
//                    return "forward:/client-request";
//                }
//                return "redirect:/client-cabinet";
//            } else {
//                return "redirect:/admin-cabinet";
//            }
//        } catch (feign.FeignException e) {
//            log.warn("an error has occurred {}", e.status());
//            switch (e.status()) {
//                case 403:
//                    model.addAttribute("errorWhileLogin", true);
//                    model.addAttribute("errorWhileLoginMessage", "login.wrong.password");
//                    return openPage("login");
//                case 404:
//                    model.addAttribute("errorWhileLogin", true);
//                    model.addAttribute("errorWhileLoginMessage", "login.no.such.user");
//                    return openPage("login");
//                case 503:
//                    System.out.println("service not available");
//                    model.addAttribute("serviceError", "Sorry, but login service temporally not available, please try later");
//                    return openPage("login");
//                default:
//                    return openPage("login");
//            }
//        }
    }

    @RequestMapping("/logout")
    public String doLogOut(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("login", new UserDataWhileLogin());
        return "redirect:/login-cmd";
    }

    @RequestMapping(value = "/login-cmd")
    public String doLogin(Model model) {
        UserDataWhileLogin userDataWhileLogin = new UserDataWhileLogin();
        model.addAttribute("login", userDataWhileLogin);
        return openPage("login");
    }

    @PostMapping("/registration")
    public String doRegistration(@Valid @ModelAttribute("registration") UserDataWhileRegistration user, BindingResult bindingResult, HttpServletRequest request) throws ServiceUnavailableException {

        if (bindingResult.hasErrors() || !inputValidation.validatePasswordTwice(user.getPassword(), user.getRepeatedPassword())) {
            log.warn("input fault while registration detected");
            ArrayList<String> fieldErrorList = inputValidation.doValidation(bindingResult);
            request.setAttribute("fieldErrorList", fieldErrorList);
            return openPage("signup");
        } else {
            HttpStatus httpStatus = commonUserActivityServiceClient.doRegistration(user);
            if (checkForServiceError(httpStatus, request)) {
                return "redirect:/signup?serviceErrorMessage=" + httpStatus.getReasonPhrase();
            }
            return "redirect:/login-cmd";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signup")
    public String getWelcome(@RequestParam(value = "serviceErrorMessage", required = false) String serviceErrorMessage, Model model) {
        model.addAttribute("serviceErrorMessage", serviceErrorMessage);
        UserDataWhileRegistration user = new UserDataWhileRegistration();
        model.addAttribute("registration", user);
        return openPage("signup");
    }

    @RequestMapping("/request")
    public String doRequest(@RequestParam(name = "persons") String persons,
                            @RequestParam(name = "roomClass") String roomClass,
                            @RequestParam(name = "datefilter") String dateFilter,
                            HttpServletRequest request) {

        request.setAttribute("persons", persons);
        request.setAttribute("roomClass", roomClass);
        request.setAttribute("datefilter", dateFilter);
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            request.setAttribute("loginAndCompleteRequest", true);
            return "forward:/login-cmd";
        } else {
            return "forward:/client-request";
        }
    }

    @ExceptionHandler({Exception.class, ServiceUnavailableException.class, java.net.ConnectException.class})
    public ModelAndView handleMyException(Exception exception) {
        log.warn(exception.getMessage());
        System.out.println("in exception handler, lastpage -> " + getLastPage());
        return new ModelAndView("redirect:" + getLastPage() + "?serviceErrorMessage=" + "Sorry, but service temporally not available, please try later");
    }
}
