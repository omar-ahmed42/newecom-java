package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.CourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.PrerequisiteService;
import com.omarahmed42.newecomservlets.ejbs.PrerequisiteServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CourseCreationServlet", value = "/admin/course/creation")
public class CourseCreationServlet extends HttpServlet {

    @EJB
    private CourseServiceImpl courseService;

    @EJB
    private PrerequisiteService prerequisiteService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/course/creation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseCode = request.getParameter("course-code");
        String courseName = request.getParameter("course-name");
        AcademicYear academicYear = AcademicYear.valueOf(request.getParameter("academic-year"));
        CourseEntity course = new CourseEntity(courseCode, courseName);
        try {
            courseService.addCourse(course);
            prerequisiteService.addPrerequisite(new PrerequisiteEntity(courseCode, academicYear));
            response.sendRedirect(request.getContextPath() + "/all-courses");
        } catch (Exception e){
            request.getRequestDispatcher("/admin/course/creation.jsp").forward(request, response);
        }
    }
}
