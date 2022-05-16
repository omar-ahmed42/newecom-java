package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.TeachesCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;
import com.omarahmed42.newecomservlets.structures.ID;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/teach/course")
public class TeachesCourseController {

    @EJB
    private TeachesCourseServiceImpl teachesCourseService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignStaffToCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseService.assignStaffToCourse(teachesCourseEntity);
        return Response.status(201).build();
    }


    @DELETE
    @Path("/staffId/{staffId}/courseCode/{courseCode}")
    public Response removeStaffFromCourse(@PathParam("staffId") Long staffId, @PathParam("courseCode") String courseCode) {
        TeachesCourseEntityPK teachesCourseEntityPK = new TeachesCourseEntityPK(staffId, courseCode);
        teachesCourseService.removeStaffFromCourse(teachesCourseEntityPK);
        return Response.status(204).build();
    }

    @POST
    @Path("/courses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoursesTaughtByStaff(ID idWrapper) {
        List<CourseEntity> courses = teachesCourseService.getCoursesTaughtByStaff(idWrapper.id);
        return ResponseUtils.buildSuccessfulRequest(200, courses);
    }
}
