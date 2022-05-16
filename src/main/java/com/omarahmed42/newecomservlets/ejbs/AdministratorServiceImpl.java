package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.AdministratorDaoImpl;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

@Stateless
@LocalBean
@Path("/bean/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministratorServiceImpl implements AdministratorService{

    @Inject
    private AdministratorDaoImpl administratorDao;

    @POST
    public void addAdministrator(AdministratorEntity administrator) {
        administratorDao.addAdministrator(administrator);
    }

    public void deleteAdministrator(AdministratorEntity administrator) {
        administratorDao.deleteAdministrator(administrator);
    }

    public void updateAdministrator(AdministratorEntity administrator) {
        administratorDao.updateAdministrator(administrator);
    }


    @PUT
    @Path("/id/{id}{firstName: (/firstName/[^/]+?)?}{lastName: (/lastName/[^/]+?)?}{dob: (/dob/[^/]+?)?}")
    public Response updateAdministrator(@PathParam("id") Long id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
                                        @PathParam("dob") String dateOfBirth) {
        if (id == null) {
            return Response.status(400).build();
        }

        AdministratorEntity administrator = getAdministratorById(id);
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

        updateAdministrator(administrator);
        return Response.ok().build();
    }

    @DELETE
    @Path("/id={id}")
    public Response deleteAdministratorr(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(400).build();
        }

        AdministratorEntity administrator = getAdministratorById(id);
        if (administrator == null) {
            return Response.status(404).build();
        }

        deleteAdministrator(administrator);
        return Response.status(204).build();
    }


    private boolean isEmpty(String text) {
        return text.equals("");
    }

    @GET
    @Path("/id={id}")
    public AdministratorEntity getAdministratorById(Long id) {
        return administratorDao.findAdministratorById(id);
    }
}
