package com.omarahmed42.newecomservlets.servlets.staff;

import com.omarahmed42.newecomservlets.ejbs.TeachesCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StaffCoursesServlet", value = "/staff/courses")
public class StaffCoursesServlet extends HttpServlet {

    @EJB
    private TeachesCourseServiceImpl teachesCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle ID using Headers/Filters (Authentication and Authorization)
        Long staffId = (Long) request.getSession().getAttribute("staffId");
        List<CourseEntity> courses = teachesCourseService.getCoursesTaughtByStaff(staffId);

        request.setAttribute("courses", courses);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/staff/courses.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
