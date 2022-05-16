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

    @POST
    @Path("/course/")
    public Response addPrerequisite(PrerequisiteEntity prerequisite) {
        prerequisiteService.addPrerequisite(prerequisite);
        return Response.ok().build();
    }
}
