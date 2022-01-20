package com.epam.apigatewayui.controller.interceptor;

import com.epam.apigatewayui.model.User;
import com.epam.apigatewayui.service.ICommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CommandInterceptor implements HandlerInterceptor {

    @Autowired
    private ICommandService commandService;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String command = request.getParameter("name");
        log.info("command detected -> {}", command);
        log.info("command role -> {}", commandService.getCommandRole(command.toUpperCase()));
        User user = (User) request.getSession().getAttribute("user");
        if (checkCommandRole(user, command)) {
            log.info("user has rights to invoke this command");
            return true;
        } else {
            log.warn("there is no rights to invoke this command");
        }
        return true;
    }

    private boolean checkCommandRole(User user, String command) throws IOException {

        String commandRole = commandService.getCommandRole(command.toUpperCase());
        if (commandRole.contains("ANYONE")) {
            return true;
        }
        return commandRole.contains(user.getUserType());
    }
}
