package com.omarahmed42.newecomservlets.servlets.course;

import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.PrerequisiteService;
import com.omarahmed42.newecomservlets.ejbs.RegistersCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "EligibleCoursesServlet", value = "/eligible-courses")
public class EligibleCoursesServlet extends HttpServlet {

    @EJB
    private PrerequisiteService prerequisiteService;

    @EJB
    private RegistersCourseServiceImpl registersCourseService;

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcademicYear academicYear = (AcademicYear) request.getSession().getAttribute("academicYear");
        Long studentId =  (Long) request.getSession().getAttribute("studentId");
        List<PrerequisiteEntity> eligibleCourses = getEligibleCourses(studentId, academicYear);
        request.setAttribute("eligibleCourses", eligibleCourses);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/course/registration");
        requestDispatcher.forward(request, response);

    }

    private List<PrerequisiteEntity> getEligibleCourses(Long studentId, AcademicYear academicYear) {
        List<PrerequisiteEntity> eligibleCourses = prerequisiteService.getEligibleCoursesForAcademicYear(academicYear);
        StudentEntity student = new StudentEntity();
        student.setStudentId(studentId);
        List<RegistersCourseEntity> registeredCourses = registersCourseService.getRegisteredCoursesForStudent(student);
        List<CompletedCourseEntity> completedCourses = completedCourseService.getCompletedCoursesForStudent(student);

        for (Iterator<PrerequisiteEntity> eligibleCourseIterator = eligibleCourses.iterator(); eligibleCourseIterator.hasNext(); ) {
            PrerequisiteEntity prerequisiteCourse = eligibleCourseIterator.next();
            for (int i = 0, j = 0; i < registeredCourses.size() || j < completedCourses.size(); i++, j++) {
                if ((i < registeredCourses.size() && isRegistered(prerequisiteCourse, registeredCourses.get(i)))
                        || (j < completedCourses.size() && isComplete(prerequisiteCourse, completedCourses.get(j)))) {
                    eligibleCourseIterator.remove();
                }
            }
        }
        return eligibleCourses;
    }

    private boolean isRegistered(PrerequisiteEntity prerequisite, RegistersCourseEntity registersCourse) {
        return prerequisite.getCourseCode().equals(registersCourse.getCourseCode());
    }

    private boolean isComplete(PrerequisiteEntity prerequisite, CompletedCourseEntity completedCourse) {
        return prerequisite.getCourseCode().equals(completedCourse.getCourseCode());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
