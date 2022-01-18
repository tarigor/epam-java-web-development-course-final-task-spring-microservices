package com.epam.apigatewayui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;

    protected String openPage(String pageName) {

        request.getSession().setAttribute("lastpage", pageName);
        return pageName;
    }
}
