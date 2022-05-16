package com.omarahmed42.newecomservlets.controllers;

import com.omarahmed42.newecomservlets.ejbs.AdministratorServiceImpl;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministratorController {

    @EJB
    private AdministratorServiceImpl administratorService;

    @POST
    public Response addAdministrator(AdministratorEntity administrator) {
        administratorService.addAdministrator(administrator);
        return Response.status(201).build();
    }

    @GET
    @Path("/id={id}")
    public Response getAdministratorById(@PathParam("id") Long id) {
        AdministratorEntity administrator = administratorService.getAdministratorById(id);
        return Response.ok(administrator).build();
    }

    @PUT
    @Path("/id/{id}{firstName: (/firstName/[^/]+?)?}{lastName: (/lastName/[^/]+?)?}{dob: (/dob/[^/]+?)?}")
    public Response updateAdministrator(@PathParam("id") Long id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
                                        @PathParam("dob") String dateOfBirth) {
        if (id == null) {
            return Response.status(400).build();
        }

        AdministratorEntity administrator = administratorService.getAdministratorById(id);
        if (administrator == null) {
            return Response.status(404).build();
        }

        if (!isEmpty(firstName)) {
            administrator.setFirstName(firstName.split("/")[2]);
        }

        if (!isEmpty(lastName)) {
            administrator.setLastName(lastName.split("/")[2]);
        }

        if (!isEmpty(dateOfBirth)) {
            administrator.setDateOfBirth(Date.valueOf(dateOfBirth.split("/")[2]));
        }

        administratorService.updateAdministrator(administrator);
        return Response.ok().build();
    }

    @DELETE
    @Path("/id={id}")
    public Response deleteAdministrator(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(400).build();
        }

        AdministratorEntity administrator = administratorService.getAdministratorById(id);
        if (administrator == null) {
            return Response.status(404).build();
        }

        administratorService.deleteAdministrator(administrator);
        return Response.status(204).build();
    }


    private boolean isEmpty(String text) {
        return text.equals("");
    }
}
