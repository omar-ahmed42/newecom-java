package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.CourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCoursesServlet", value = "/admin/courses")
public class AdminCoursesServlet extends HttpServlet {

    @EJB
    private CourseServiceImpl courseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CourseEntity> allCourses = courseService.getAllCourses();
        request.setAttribute("courses", allCourses);

        RequestDispatcher rd = request.getRequestDispatcher("/admin/courses.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
