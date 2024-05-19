package com.ohgiraffers.dosirak.config;

import com.ohgiraffers.dosirak.common.UserRole;
import com.ohgiraffers.dosirak.config.handler.LoginFailHandler;
import com.ohgiraffers.dosirak.config.handler.LoginSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final LoginFailHandler loginFailHandler;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(LoginFailHandler loginFailHandler, LoginSuccessHandler loginSuccessHandler){
            this.loginFailHandler = loginFailHandler;
            this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("**", "/admin/member/join").permitAll();
                    auth.requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.getRole());
                    auth.requestMatchers("/user/myinfo/**").hasAnyAuthority(UserRole.USER.getRole());
                    auth.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.usernameParameter("id");
                    login.passwordParameter("pwd");
                    login.successHandler(loginSuccessHandler);
                    login.failureHandler(loginFailHandler);
                })
                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                    logout.logoutSuccessUrl("/logoutPage");
                })
                .sessionManagement(session -> {
                    session.maximumSessions(1);
                    // 회원탈퇴시 세션 만료시키면 logoutPage로 redirect 되는걸 막기 위해 주석처리
                    // session.invalidSessionUrl("/logoutPage");
                })
                .csrf(csrf -> csrf.disable());    // Cross-Site Request Forgery 개발단계에서만 disable() 설정해줌

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
