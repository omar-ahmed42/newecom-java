package com.omarahmed42.newecomservlets.servlets.student;

import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoursesServlet", value = "/student/courses")
public class CoursesServlet extends HttpServlet {

    @EJB
    private StudentServiceImpl studentService;

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    //TODO: Handle authentication and authorization
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/past-courses");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
