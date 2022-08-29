package com.kjh.miniprj.security;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.miniprj.account.AccountDTO;
import com.kjh.miniprj.security.auth.MakeUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class SecurityFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public SecurityFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AccountDTO accountDTO = new AccountDTO();
        try {
            accountDTO.setUsername(request.getParameter("username"));
            accountDTO.setPassword(request.getParameter("password"));
        } catch (Exception e) {

            System.out.println("error");
            e.printStackTrace();
        }
        System.out.println(accountDTO.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(

                        accountDTO.getUsername(),
                        accountDTO.getPassword()
                );
        System.out.println("token");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        MakeUserDetails makeUserDetails = (MakeUserDetails) authentication.getPrincipal();
        System.out.println(makeUserDetails.getAccount().getNickname());
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Login success");
        SecurityContextHolder.getContext().setAuthentication(authResult);

        response.sendRedirect("/");
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("/login?msg=NotFound.");
    }
}
