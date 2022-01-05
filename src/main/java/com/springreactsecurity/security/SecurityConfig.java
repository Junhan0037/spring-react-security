package com.springreactsecurity.security;

import com.springreactsecurity.security.handler.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.formLogin()
                .loginProcessingUrl("/api/sign-in")                                 // Login Url (POST form)
                .usernameParameter("email")                                         // Id Parameter
                .passwordParameter("password")                                      // Password Parameter
                .successHandler(new LoginSuccessHandler())                          // LoginSuccessHandler
                .failureHandler(new LoginFailureHandler());                         // LoginFailureHandler

        http.logout()
                .logoutUrl("/api/logout")                                           // Logout Url (POST)
                .deleteCookies("JSESSIONID", "remember-me")                         // Logout 후 쿠키 삭제
                .logoutSuccessHandler(new LogoutSuccessHandler());                  // Logout 성공 후 Handler

        http.exceptionHandling()
                .authenticationEntryPoint(new LoginAuthenticationEntryPoint())      // AuthenticationEntryPoint (인증)
                .accessDeniedHandler(new LoginAccessDeniedHandler());               // AccessDeniedHandler (인가)

        http.authorizeRequests()
                .antMatchers("/", "/api/sign-up", "/api/sign-in").permitAll()
                .anyRequest().authenticated();
    }

}
