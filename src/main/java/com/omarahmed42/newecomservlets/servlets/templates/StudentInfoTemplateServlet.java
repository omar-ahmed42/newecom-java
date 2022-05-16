package com.omarahmed42.newecomservlets.servlets.templates;

import com.omarahmed42.newecomservlets.ejbs.StaffStudentCourseImpl;
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

@WebServlet(name = "StudentInfoTemplateServlet", value = "/student-info")
public class StudentInfoTemplateServlet extends HttpServlet {

    @EJB
    private StaffStudentCourseImpl staffStudentCourse;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long staffId = (Long) request.getSession().getAttribute("staffId"); //Handle it
        String courseCode = request.getParameter("courseCode");
        List<StudentEntity> studentsInStaffCourse = staffStudentCourse.getStudentsInStaffCourse(staffId, courseCode);
        request.setAttribute("students", studentsInStaffCourse);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/templates/student-info.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
