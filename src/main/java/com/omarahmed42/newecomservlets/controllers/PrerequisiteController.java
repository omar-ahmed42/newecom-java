package com.omarahmed42.newecomservlets.controllers;

import com.omarahmed42.newecomservlets.ejbs.PrerequisiteService;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/prerequisite")
public class PrerequisiteController {

    @EJB
    private PrerequisiteService prerequisiteService;

//    @GET
//    @Path("/courses/year/{academicYear}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getEligibleCourses(@PathParam("academicYear") AcademicYear academicYear){
//        List<PrerequisiteEntity> eligibleCourses = prerequisiteService.getEligibleCoursesForAcademicYear(academicYear);
//        return Response.ok(eligibleCourses).build();
//    }

    @POST
    @Path("/course/")
    public Response addPrerequisite(PrerequisiteEntity prerequisite) {
        System.out.println("PRE: " + prerequisite.getCourseCode());
        prerequisiteService.addPrerequisite(prerequisite);
        return Response.ok().build();
    }
}
