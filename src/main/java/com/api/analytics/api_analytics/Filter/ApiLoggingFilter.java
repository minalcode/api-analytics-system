
package com.api.analytics.api_analytics.Filter;

import com.api.analytics.api_analytics.Service.ApiLogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiLoggingFilter extends OncePerRequestFilter {

    private final ApiLogService apiLogService;

    public ApiLoggingFilter(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return !path.startsWith("/api/")
                || path.startsWith("/api/logs")
                || path.startsWith("/api/analytics");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long responseTime =
                    System.currentTimeMillis() - startTime;

            try {
                apiLogService.saveLog(
                        request.getRequestURI(),
                        request.getMethod(),
                        response.getStatus(),
                        responseTime
                );
            } catch (Exception exception) {
                // Logging failure should not break the API response.
                logger.error("Failed to save API log", exception);
            }
        }
    }
}
