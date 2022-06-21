package com.omarahmed42.newecomservlets.servlets.admin;

import com.omarahmed42.newecomservlets.ejbs.TeachesCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AssignStaffToCoursesServlet", value = "/admin/staff-assignment")
public class AssignStaffToCoursesServlet extends HttpServlet {

    @EJB
    private TeachesCourseServiceImpl teachesCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedCourses[] = request.getParameterValues("eligible-courses");
        Long staffIdForAssignment = (Long) request.getSession().getAttribute("staffForAssignment");
        request.getSession().removeAttribute("staffForAssignment");
        for (String courseCode : selectedCourses) {
            teachesCourseService.assignStaffToCourse(new TeachesCourseEntity(staffIdForAssignment, courseCode));
        }
        response.sendRedirect("/newecom-servlets-1.0-SNAPSHOT/admin/staff-list");
    }
}
