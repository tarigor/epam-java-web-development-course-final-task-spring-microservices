package com.epam.apigatewayui.controller.interceptor;

import com.epam.apigatewayui.entity.User;
import com.epam.apigatewayui.service.ISiteMenuService;
import com.epam.apigatewayui.types.MenuRole;
import com.epam.apigatewayui.types.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MenuInterceptor implements HandlerInterceptor {
    @Autowired
    private ISiteMenuService siteMenuService;

    /**
     * Interception point before the execution of a handler. Called after
     * HandlerMapping determined an appropriate handler object, but before
     * HandlerAdapter invokes the handler.
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can decide to abort the execution chain,
     * typically sending an HTTP error or writing a custom response.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation returns {@code true}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            UserType userRole = UserType.valueOf(loggedUser.getUserType());
            if (userRole.equals(UserType.ADMIN)) {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(
                        MenuRole.COMMON,
                        MenuRole.ADMIN_LOGGED,
                        MenuRole.ANYONE_LOGGED));
            } else if (userRole.equals(UserType.CLIENT)) {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(
                        MenuRole.COMMON,
                        MenuRole.CLIENT,
                        MenuRole.ANYONE_LOGGED));
            } else {
                request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(
                        MenuRole.COMMON,
                        MenuRole.ANYONE_NOT_LOGGED));
            }
        } else {
            request.getSession().setAttribute("menuList", siteMenuService.getMenuListCollectedByRoleSortedByID(
                    MenuRole.COMMON,
                    MenuRole.ANYONE_NOT_LOGGED));
        }
        return true;
    }
}
