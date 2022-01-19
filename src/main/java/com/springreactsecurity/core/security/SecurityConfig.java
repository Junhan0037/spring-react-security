package com.springreactsecurity.core.security;

import com.springreactsecurity.core.security.handler.*;
import com.springreactsecurity.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.servlet.session.cookie.name}")
    private String COOKIE_NAME;

    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UserDetailServiceImpl userDetailService;
    private final DataSource dataSource;

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
                .loginProcessingUrl("/api/auth/sign-in")                            // Login Url (POST form)
                .usernameParameter("userId")                                        // Id Parameter
                .passwordParameter("userPassword")                                  // Password Parameter
                .successHandler(loginSuccessHandler)                                // LoginSuccessHandler
                .failureHandler(loginFailureHandler);                               // LoginFailureHandler

        http.logout()
                .logoutUrl("/api/auth/logout")                                      // Logout Url (POST)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me", COOKIE_NAME)            // Logout 후 Cookie 삭제
                .logoutSuccessHandler(logoutSuccessHandler);                        // Logout 성공 후 Handler

        http.rememberMe()
                .rememberMeParameter("remember-me")                                 // Login form Parameter (boolean)
                .rememberMeCookieName("remember-me")                                // Cookie 명칭
                .tokenValiditySeconds(10800)                                        // 로그인 기억하기 기간 (3시간)
                .alwaysRemember(false)                                              // 항상 기능 활성화
                .userDetailsService(userDetailService)                              // userDetailService
                .tokenRepository(tokenRepository());                                // DB 저장

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)                 // AuthenticationEntryPoint (인증)
                .accessDeniedHandler(accessDeniedHandler);                          // AccessDeniedHandler (인가)

        http.authorizeRequests()
                .antMatchers("/", "/api/auth/**").permitAll()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**").permitAll()
                .antMatchers("/api/admin/**").hasRole(Role.ADMIN.toString())
                .anyRequest().authenticated();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
