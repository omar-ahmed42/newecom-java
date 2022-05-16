package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.StaffServiceImpl;
import com.omarahmed42.newecomservlets.entities.StaffEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffController {

    @EJB
    private StaffServiceImpl staffService;

    @POST // Tested using postman
    public Response addStaff(StaffEntity staff){
        staffService.addStaff(staff);
        return Response.status(201).build();
    }

    @GET
    @Path("/id={id}") // Tested using postman
    public Response getStaffById(@PathParam("id") Long id){
        StaffEntity staff = staffService.getStaffById(id);
        return Response.ok(staff).build();
    }

    @PUT
    @Path("/id/{id}{firstName: (/firstName/[^/]+?)?}{lastName: (/lastName/[^/]+?)?}{dob: (/dob/[^/]+?)?}") // Tested using postman
    public Response updateStaff(@PathParam("id") Long id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
                                  @PathParam("dob") String dateOfBirth){
        if (id == null){
            return Response.status(400).build();
        }

        StaffEntity staff = staffService.getStaffById(id);
        if (staff == null){
            return Response.status(404).build();
        }

        if (!isEmpty(firstName)){
            staff.setFirstName(firstName.split("/")[2]);
        }

        if (!isEmpty(lastName)){
            staff.setLastName(lastName.split("/")[2]);
        }

        if (!isEmpty(dateOfBirth)){
            staff.setDateOfBirth(Date.valueOf(dateOfBirth.split("/")[2]));
        }

        staffService.updateStaff(staff);
        return Response.ok().build();
    }

    @DELETE
    @Path("/id={id}") // Tested using postman
    public Response deleteStaff(@PathParam("id") Long id){
        if (id == null){
            return Response.status(400).build();
        }

        StaffEntity staff = staffService.getStaffById(id);
        if (staff == null){
            return Response.status(404).build();
        }

        staffService.deleteStaff(staff);
        return Response.status(204).build();
    }


    private boolean isEmpty(String text){
        return text.equals("");
    }
}
