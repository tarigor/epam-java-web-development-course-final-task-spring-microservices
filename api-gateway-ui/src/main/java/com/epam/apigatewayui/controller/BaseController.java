package com.epam.apigatewayui.controller;

import com.epam.apigatewayui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    protected static HashMap<String, Object> attributesMap = new HashMap<>();
    @Autowired
    private HttpServletRequest request;
    private String lastPage;

    protected static Date convertStringToSqlDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSQL = null;
        try {
            java.util.Date dateUtil = formatter.parse(date);
            dateSQL = new Date(dateUtil.getTime());
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return dateSQL;
    }

    protected String openPage(String pageName) {
        storeAttributesInMap(request);
        request.getSession().setAttribute("lastpage", pageName);
        return pageName;
    }

    protected String getLastPage() {
        return (String) request.getSession().getAttribute("lastpage");
    }

    protected void setLastPage(String page) {
        lastPage = page;
        request.getSession().setAttribute("lastpage", lastPage);
    }

    protected User getLoggedUser() {
        return (User) request.getSession().getAttribute("user");
    }

    protected boolean checkForServiceError(HttpStatus status, HttpServletRequest request) {
        if (status.value() != 200) {
            request.setAttribute("serviceErrorMessage", status.getReasonPhrase());
            return true;
        }
        if (status.value() == 500) {
            request.setAttribute("serviceErrorMessage", status.getReasonPhrase());
        }
        return false;
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

    protected void setAttributes() {
        if (attributesMap != null) {
            if (attributesMap.size() != 0) {
                for (Map.Entry<String, Object> entry : attributesMap.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
