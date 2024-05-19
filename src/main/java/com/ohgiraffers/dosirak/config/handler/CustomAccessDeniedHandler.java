package com.ohgiraffers.dosirak.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String errorMessage = accessDeniedException.getMessage();
        int errorCode = HttpServletResponse.SC_FORBIDDEN;

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("errorCode", errorCode);
        request.getRequestDispatcher("/errorPage").forward(request, response);
    }

}
