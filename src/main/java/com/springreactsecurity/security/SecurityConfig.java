package com.springreactsecurity.security;

import com.springreactsecurity.security.handler.LoginAccessDeniedHandler;
import com.springreactsecurity.security.handler.LoginAuthenticationEntryPoint;
import com.springreactsecurity.security.handler.LoginFailureHandler;
import com.springreactsecurity.security.handler.LoginSuccessHandler;
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
        http.formLogin()
                .loginProcessingUrl("/api/sign-in")                                 // Login Url
                .usernameParameter("email")                                         // Id Parameter
                .passwordParameter("password")                                      // Password Parameter
                .successHandler(new LoginSuccessHandler())                          // LoginSuccessHandler
                .failureHandler(new LoginFailureHandler());                         // LoginFailureHandler

        http.exceptionHandling()
                .authenticationEntryPoint(new LoginAuthenticationEntryPoint())      // AuthenticationEntryPoint
                .accessDeniedHandler(new LoginAccessDeniedHandler());               // AccessDeniedHandler

        http.authorizeRequests()
                .antMatchers("/api/sign-up").permitAll()
                .antMatchers("/api/sign-in").authenticated()
                .anyRequest().authenticated();
    }

}
