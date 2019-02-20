package com.soft.cli.clientbe.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(CORSFilter.class);
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        log.debug("-------------------------------------filter-----------------------------------");
        chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}
