package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;
    protected static HashMap<String, Object> attributesMap = new HashMap<>();

    protected String openPage(String pageName) {
        storeAttributesInMap(request);
        request.getSession().setAttribute("lastpage", pageName);
        return pageName;
    }

    protected void setLastPage(String pageName){
        request.getSession().setAttribute("lastpage", pageName);
    }

    protected User getLoggedUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    private void storeAttributesInMap(HttpServletRequest request) {
        Enumeration<String> e = request.getAttributeNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            Object value = request.getAttribute(name);
            attributesMap.put(name, value);
            request.getSession().setAttribute("attributesMap", attributesMap);
        }
    }

    protected void setAttributes(){
        if (attributesMap != null) {
            if (attributesMap.size() != 0) {
                for (Map.Entry<String, Object> entry : attributesMap.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
