package com.omarahmed42.newecomservlets.servlets.templates;

import com.omarahmed42.newecomservlets.ejbs.CourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.StaffServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.TeachesCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.StaffEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "StaffTemplateServlet", value = "/staff/page")
public class StaffTemplateServlet extends HttpServlet {

    @EJB
    private StaffServiceImpl staffService;

    @EJB
    private CourseServiceImpl courseService;

    @EJB
    private TeachesCourseServiceImpl teachesCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long staffId = Long.valueOf(request.getParameter("id"));
        request.getSession().setAttribute("staffForAssignment", staffId);
        StaffEntity staff = staffService.getStaffById(staffId);

        List<CourseEntity> allCourses = courseService.getAllCourses();
        List<CourseEntity> coursesTaughtByStaff = teachesCourseService.getCoursesTaughtByStaff(staffId);
        List<CourseEntity> eligibleCourses = filterTeachesCourses(allCourses, coursesTaughtByStaff);
        request.setAttribute("eligibleCourses", eligibleCourses);

        request.getRequestDispatcher("/templates/staff-assignment.jsp").forward(request, response);
    }

    private List<CourseEntity> filterTeachesCourses(List<CourseEntity> courses, List<CourseEntity> teachesCourses) {
        for (Iterator<CourseEntity> coursesIterator = courses.iterator(); coursesIterator.hasNext(); ) {
            CourseEntity course = coursesIterator.next();
            for (int i = 0, j = 0; i < teachesCourses.size(); i++, j++) {
                if ((isTeaching(course, teachesCourses.get(i)))) {
                    coursesIterator.remove();
                }
            }
        }
        return courses;
    }

    private boolean isTeaching(CourseEntity course, CourseEntity teachesCourse) {
        return course.getCourseCode().equals(teachesCourse.getCourseCode());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
