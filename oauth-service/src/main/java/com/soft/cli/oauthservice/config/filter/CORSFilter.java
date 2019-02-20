package com.soft.cli.oauthservice.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(CORSFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String originHeader = request.getHeader("Origin")!=null?request.getHeader("Origin"):request.getHeader("origin");
        log.debug("Origin :: {} ",originHeader);
        //response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, X-Requested-With, Content-Type, Accept, Loading-Overlay, X-Prototype-Version, X-CSRF-Token, access, refresh, expires, pragma, AuthorizationBasic, Cache-Control");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        request.getHeaderNames();
        log.debug("-------------------------------------------------------");
        if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
