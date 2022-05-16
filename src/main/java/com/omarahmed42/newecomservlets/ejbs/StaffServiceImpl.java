package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.StaffDao;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Stateless
@LocalBean
@Path("/bean/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffServiceImpl implements StaffService{
    @Inject
    private StaffDao staffDao;

    @Override
    public void addStaff(StaffEntity staff) {
        staffDao.addStaff(staff);
    }

    @Override
    public void deleteStaff(StaffEntity staff) {
        staffDao.deleteStaff(staff);
    }

    @Override
    public void updateStaff(StaffEntity staff) {
        staffDao.updateStaff(staff);
    }

    @Override
    public StaffEntity getStaffById(Long id) {
        return staffDao.findStaffById(id);
    }

    @Override
    public List<StaffEntity> findAllStaff(){
        return staffDao.findAllStaff();
    }

    @POST // Tested using postman
    public Response addStafff(StaffEntity staff){
        addStaff(staff);
        return Response.status(201).build();
    }

    @GET
    @Path("/id={id}") // Tested using postman
    public Response getStaffByIdd(@PathParam("id") Long id){
        StaffEntity staff = getStaffById(id);
        return Response.ok(staff).build();
    }

    @PUT
    @Path("/id/{id}{firstName: (/firstName/[^/]+?)?}{lastName: (/lastName/[^/]+?)?}{dob: (/dob/[^/]+?)?}") // Tested using postman
    public Response updateStafff(@PathParam("id") Long id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName,
                                @PathParam("dob") String dateOfBirth){
        if (id == null){
            return Response.status(400).build();
        }

        StaffEntity staff = getStaffById(id);
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
            staff.setDateOfBirth(LocalDate.parse((dateOfBirth.split("/")[2])));
        }

        updateStaff(staff);
        return Response.ok().build();
    }

    @DELETE
    @Path("/id={id}") // Tested using postman
    public Response deleteStafff(@PathParam("id") Long id){
        if (id == null){
            return Response.status(400).build();
        }

        StaffEntity staff = getStaffById(id);
        if (staff == null){
            return Response.status(404).build();
        }

        deleteStaff(staff);
        return Response.status(204).build();
    }


    private boolean isEmpty(String text){
        return text.equals("");
    }
}
