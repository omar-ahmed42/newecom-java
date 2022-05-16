package com.omarahmed42.newecomservlets.servlets.templates;

import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentTemplateServlet", value = "/student/page")
public class StudentTemplateServlet extends HttpServlet {

    @EJB
    private StudentServiceImpl studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long studentId = Long.valueOf(request.getParameter("id"));
        StudentEntity student = studentService.getStudentById(studentId);
        request.setAttribute("completedCourses", student.getCompletedCoursesByStudentId());
        request.setAttribute("registeredCourses", student.getRegistersCoursesByStudentId());

        RequestDispatcher rd = request.getRequestDispatcher("/templates/student.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
