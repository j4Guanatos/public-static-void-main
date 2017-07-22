package com.j4gdl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j4gdl.security.authentication.JwtAuthenticationProcessingFilter;
import com.j4gdl.security.authentication.JwtAuthenticationProvider;
import com.j4gdl.security.authorization.JwtAuthorizationProcessFilter;
import com.j4gdl.security.authorization.JwtAuthorizationProvider;
import com.j4gdl.security.authorization.SkipPathRequestMatcher;
import com.j4gdl.security.authorization.extractor.TokenExtractor;
import com.j4gdl.security.error.RestErrorEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Emmanuel_Garcia on 3/29/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_ENTRY_POINT = "/auth/login";
    public static final String TOKEN_AUTH_ENTRY_POINT = "/**";

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    JwtAuthorizationProvider jwtAuthorizationProvider;

    @Autowired
    private RestErrorEntryPoint restErrorEntryPoint;

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired
    private  AuthenticationSuccessHandler successHandler;
    @Autowired
    private  AuthenticationFailureHandler failureHandler;
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private TokenExtractor tokenExtractor;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(jwtAuthorizationProvider);
    }

    protected JwtAuthenticationProcessingFilter buildJwtAuthenticationProcessingFilter(){
        JwtAuthenticationProcessingFilter authFilter = new JwtAuthenticationProcessingFilter(LOGIN_ENTRY_POINT,
                successHandler, failureHandler, objectMapper);
        authFilter.setAuthenticationManager(authenticationManager);
        return authFilter;
    }

    protected JwtAuthorizationProcessFilter buildJwtAuthorizationProcessFilter(){
        List<String> pathsToSkip = Arrays.asList(LOGIN_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_AUTH_ENTRY_POINT);
        JwtAuthorizationProcessFilter filter
                = new JwtAuthorizationProcessFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restErrorEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(LOGIN_ENTRY_POINT).permitAll()
                .and()
                    .authorizeRequests()
                        .antMatchers(TOKEN_AUTH_ENTRY_POINT).authenticated()
                .and()
                    .addFilterBefore(buildJwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(buildJwtAuthorizationProcessFilter(),UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
