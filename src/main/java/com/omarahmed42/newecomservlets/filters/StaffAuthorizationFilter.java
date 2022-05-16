package com.omarahmed42.newecomservlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "StaffAuthorizationFilter", urlPatterns = {"/staff/grade/assignment", "/staff/courses", "/student-info"})
public class StaffAuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (Objects.nonNull(httpServletRequest.getSession().getAttribute("staffId"))) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/staff/login").forward(request, response);
        }

    }
}
