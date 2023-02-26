package com.none.forum.Configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyFilter extends GenericFilterBean {

    private static final String API_KEY = "XOYwD5cxVCWVUZeI1Gdv6Sq7bfQuXWQmh7YdzDvTABME5hu8PMIc66SMi7AYERmmkOQp65d55E14NBELxRPFNRrBtXntljr3NW9MbK6XQWzzjyJJQMcvasuYybtUTHu0";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String apiKey = httpRequest.getHeader("X-API-KEY");

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if (!path.startsWith("/api")) {
            chain.doFilter(request, response);
            return;
        }

        if (apiKey != null && apiKey.equals(API_KEY)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key.");
        }
    }
}