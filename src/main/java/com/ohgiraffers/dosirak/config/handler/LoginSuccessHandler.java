package com.ohgiraffers.dosirak.config.handler;

import com.ohgiraffers.dosirak.common.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Set;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> role = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if(role.contains(UserRole.ADMIN.getRole())){
            response.sendRedirect("/admin/main");
        }else{
            response.sendRedirect("/user/main");
        }
    }
}
