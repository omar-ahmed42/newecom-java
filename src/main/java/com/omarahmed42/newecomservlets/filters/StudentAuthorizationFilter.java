package com.omarahmed42.newecomservlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "StudentAuthorizationFilter", urlPatterns = {"/eligible-courses", "/course/registration", "/student/courses", "/current-courses",
        "/past-courses"})
public class StudentAuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (Objects.nonNull(httpServletRequest.getSession().getAttribute("studentId"))) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/student/login").forward(request, response);
        }

    }
}
