package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.StaffStudentCourseImpl;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/staff/course")
public class StaffStudentCourseController {

    @EJB
    private StaffStudentCourseImpl staffStudentCourse;

    @POST
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getStudentsInStaffCourse(TeachesCourseEntityPK teachesCourseEntityPK) {
        try {
            List<StudentEntity> studentsInStaffCourse = staffStudentCourse.getStudentsInStaffCourse(teachesCourseEntityPK.getStaffId(), teachesCourseEntityPK.getCourseCode());
            return ResponseUtils.buildSuccessfulRequest(200, studentsInStaffCourse);
        } catch (Exception e) {
            return ResponseUtils.buildFailedRequest(500);
        }

    }

}
