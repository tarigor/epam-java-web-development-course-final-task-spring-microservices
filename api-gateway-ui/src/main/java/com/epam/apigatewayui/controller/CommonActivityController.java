package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.entity.User;
import com.epam.apigatewayui.feignservice.CommonServiceFeignClient;
import com.epam.apigatewayui.feignservice.CommonUserActivityServiceClient;
import com.epam.apigatewayui.model.UserDataWhileLogin;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@Slf4j
public class CommonActivityController extends BaseController {

    @Autowired
    private CommonUserActivityServiceClient commonUserActivityServiceClient;

    @Autowired
    private CommonServiceFeignClient commonServiceFeignClient;

    @Autowired
    private Gson gson;

    @GetMapping("/")
    public String showHome() {
        return openPage("index");
    }

    @GetMapping("/page")
    public String menuPageNavigate(@RequestParam(name = "name") String pageName, Model model) {
        if (pageName.equals("signup")) {
            model.addAttribute("registration", new UserDataWhileLogin());
        }
        if (pageName.equals("login")) {
            model.addAttribute("login", new UserDataWhileLogin());
        }
        return openPage(pageName);
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "loginAndCompleteRequest", required = false) String loginAndCompleteRequest,
            HttpServletRequest httpServletRequest,
            Model model) {

        UserDataWhileLogin userDataWhileLogin = new UserDataWhileLogin  (email, password);
        try {
            String body = Objects.requireNonNull(commonUserActivityServiceClient.getUserWithHttpStatusCode(userDataWhileLogin).getBody()).toString();
            User user = gson.fromJson(body, User.class);
            httpServletRequest.getSession().setAttribute("user", user);
            return "redirect:/clientcabinet";
        } catch (feign.FeignException e) {
            log.warn("an error has occurred {}", e.status());
            switch (e.status()) {
                case 403:
                    model.addAttribute("errorWhileLogin", true);
                    model.addAttribute("errorWhileLoginMessage", "login.wrong.password");
                    return "login";
                case 404:
                    model.addAttribute("errorWhileLogin", true);
                    model.addAttribute("errorWhileLoginMessage", "login.no.such.user");
                    return "login";
                default:
                    System.out.println("server error");
                    return "login";
            }
        }
    }

    @GetMapping("/clientcabinet")
    public String openClientCabinetPage(){
        System.out.println("in clientcbinet controller");
        return "index";
    }
}
