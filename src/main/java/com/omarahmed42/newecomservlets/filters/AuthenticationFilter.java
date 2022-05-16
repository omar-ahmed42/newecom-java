package com.omarahmed42.newecomservlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getRequestURI().contains("/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (Objects.nonNull(httpServletRequest.getSession().getAttribute("administratorId"))
                || Objects.nonNull(httpServletRequest.getSession().getAttribute("staffId"))
                || Objects.nonNull(httpServletRequest.getSession().getAttribute("studentId"))) {
            chain.doFilter(request, response);
        } else {
            response.getWriter().println("Unauthenticated, Forbidden");
            httpServletRequest.getRequestDispatcher("/all-courses").forward(request, response);
        }
    }
}
