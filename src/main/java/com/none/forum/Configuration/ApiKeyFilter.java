package com.none.forum.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY = "XOYwD5cxVCWVUZeI1Gdv6Sq7bfQuXWQmh7YdzDvTABME5hu8PMIc66SMi7AYERmmkOQp65d55E14NBELxRPFNRrBtXntljr3NW9MbK6XQWzzjyJJQMcvasuYybtUTHu0";

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException, ServletException {
        String apiKey = request.getHeader("X-API-KEY");
        String path = request.getRequestURI();

        if (!path.startsWith("/api")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (apiKey != null && apiKey.equals(API_KEY)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key.");
        }
    }
}