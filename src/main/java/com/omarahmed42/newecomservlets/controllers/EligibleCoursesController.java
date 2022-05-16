package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.PrerequisiteService;
import com.omarahmed42.newecomservlets.ejbs.RegistersCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;
import com.omarahmed42.newecomservlets.structures.ID;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;

@Path("/eligible")
public class EligibleCoursesController {

    @EJB
    private PrerequisiteService prerequisiteService;

    @EJB
    private RegistersCourseServiceImpl registersCourseService;

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    @POST
    @Path("/courses/year/{academicYear}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEligibleCourses(ID idWrapper, @PathParam("academicYear") AcademicYear academicYear) {
        List<PrerequisiteEntity> eligibleCourses = prerequisiteService.getEligibleCoursesForAcademicYear(academicYear);
        StudentEntity student = new StudentEntity();
        student.setStudentId(idWrapper.id);
        List<RegistersCourseEntity> registeredCourses = registersCourseService.getRegisteredCoursesForStudent(student);
        List<CompletedCourseEntity> completedCourses = completedCourseService.getCompletedCoursesForStudent(student);

        for (Iterator<PrerequisiteEntity> eligibleCourseIterator = eligibleCourses.iterator(); eligibleCourseIterator.hasNext();){
            PrerequisiteEntity prerequisiteCourse = eligibleCourseIterator.next();
            for (int i = 0, j = 0; i < registeredCourses.size() || j < completedCourses.size(); i++, j++) {
                if ((i < registeredCourses.size() && isRegistered(prerequisiteCourse, registeredCourses.get(i)))
                        || (j < completedCourses.size() && isComplete(prerequisiteCourse, completedCourses.get(j)))) {
                    eligibleCourseIterator.remove();
                }
            }
        }
        return Response.ok(eligibleCourses).build();
    }

    private boolean isRegistered(PrerequisiteEntity prerequisite, RegistersCourseEntity registersCourse){
        return prerequisite.getCourseCode().equals(registersCourse.getCourseCode());
    }

    private boolean isComplete(PrerequisiteEntity prerequisite, CompletedCourseEntity completedCourse){
        return prerequisite.getCourseCode().equals(completedCourse.getCourseCode());
    }


}
