package com.omarahmed42.newecomservlets.servlets.student;

import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
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

@WebServlet(name = "PastCoursesServlet", value = "/past-courses")
public class PastCoursesServlet extends HttpServlet {

    @EJB
    private StudentServiceImpl studentService;

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = (Long) request.getSession().getAttribute("studentId");
        StudentEntity student = studentService.getStudentById(id);
        List<CompletedCourseEntity> completedCoursesForStudent = completedCourseService.getCompletedCoursesForStudent(student);

        request.setAttribute("completedCoursesForStudent", completedCoursesForStudent);

        RequestDispatcher rd = request.getRequestDispatcher("/current-courses");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
