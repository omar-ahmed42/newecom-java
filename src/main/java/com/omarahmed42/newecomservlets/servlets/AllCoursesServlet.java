package com.omarahmed42.newecomservlets.servlets;

import com.omarahmed42.newecomservlets.ejbs.AllCoursesServiceImpl;
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

@WebServlet(name = "AllCourses", value = "/all-courses")
public class AllCoursesServlet extends HttpServlet {

    @EJB
    private AllCoursesServiceImpl allCoursesService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CourseEntity> allCourses = allCoursesService.getAllCourses();
        request.setAttribute("allCourses", allCourses);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("all-courses.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
