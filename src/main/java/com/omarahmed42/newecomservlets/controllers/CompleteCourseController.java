package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/completed/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompleteCourseController {

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    @POST
    @Path("/complete")
    public Response addCompletedCourse(CompletedCourseEntity completedCourse) {
        completedCourseService.addCompletedCourse(completedCourse);
        return ResponseUtils.buildSuccessfulRequest(201, null);
    }

    @DELETE
    @Path("/del/{id}")
    public Response deleteCompletedCourse(@PathParam("id") Long id) {
        if (Objects.isNull(id)) {
            return ResponseUtils.buildFailedRequest(400);
        }

        CompletedCourseEntity completedCourse = completedCourseService.findCompletedCourse(id);
        if (Objects.isNull(completedCourse)) {
            return ResponseUtils.buildFailedRequest(404);
        }

        try {
            completedCourseService.deleteCompletedCourse(completedCourse);
            return ResponseUtils.buildSuccessfulRequest(204, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.buildFailedRequest(500);
        }
    }

    @POST
    @Path("/grade")
    public Response addGradeToCompletedCourse(CompletedCourseEntity completedCourse) { // id, grade
        completedCourseService.updateGrade(completedCourse.getId(), completedCourse.getGrade());
        return ResponseUtils.buildSuccessfulRequest(200, null);
    }

}
