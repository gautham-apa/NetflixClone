package com.hari.netflix.filter;

import com.hari.netflix.auth.UserAuthRole;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdminActionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserAuthRole.ADMIN.name()))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access");
    }
}
