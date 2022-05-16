package com.omarahmed42.newecomservlets.servlets.staff;

import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GradeAssignmentServlet", value = "/staff/grade/assignment")
public class GradeAssignmentServlet extends HttpServlet {

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/staff/grades.jsp").forward(request,  response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long completedCourseId = Long.valueOf(request.getParameter("completed-course-id"));
        Double grade = Double.valueOf(request.getParameter("completed-course-grade"));
        completedCourseService.updateGrade(completedCourseId, grade);
        request.getRequestDispatcher("/all-courses");
    }
}
