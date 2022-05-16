package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.AllCoursesServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/all/courses")
public class AllCoursesController {

    @EJB
    AllCoursesServiceImpl allCoursesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses() {
        try {
            List<CourseEntity> allCourses = allCoursesService.getAllCourses();
            return ResponseUtils.buildSuccessfulRequest(200, allCourses);
        } catch (Exception e) {
            return ResponseUtils.buildFailedRequest(500);
        }
    }
}
