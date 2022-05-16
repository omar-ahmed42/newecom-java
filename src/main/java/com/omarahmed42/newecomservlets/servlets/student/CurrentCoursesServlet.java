package com.omarahmed42.newecomservlets.servlets.student;

import com.omarahmed42.newecomservlets.ejbs.RegistersCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CurrentCoursesServlet", value = "/current-courses")
public class CurrentCoursesServlet extends HttpServlet {
    @EJB
    private StudentServiceImpl studentService;

    @EJB
    private RegistersCourseServiceImpl registersCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long studentId = (Long) request.getSession().getAttribute("studentId");
        StudentEntity student = studentService.getStudentById(studentId);
        List<RegistersCourseEntity> registeredCoursesForStudent = registersCourseService.getRegisteredCoursesForStudent(student);
        request.setAttribute("registeredCoursesForStudent", registeredCoursesForStudent);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/courses.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
