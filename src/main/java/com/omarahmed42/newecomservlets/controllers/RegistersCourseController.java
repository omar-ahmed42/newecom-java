package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.RegistersCourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/course/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistersCourseController {

    @EJB
    private RegistersCourseServiceImpl registersCourseService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registersInACourse(RegistersCourseEntity registersCourseEntity){ // Tested using postman
        registersCourseService.registerInACourse(registersCourseEntity);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/studentId/{studentId}/courseCode/{courseCode}")
    public Response dropFromCourse(@PathParam("studentId") Long studentId, @PathParam("courseCode") String courseCode){ // Tested using postman
        RegistersCourseEntity registersCourse = registersCourseService.getRegistersCourse(studentId, courseCode);
        registersCourseService.dropFromACourse(registersCourse);
        return Response.status(204).build();
    }

    @PUT
    @Path("/studentId/{studentId}/courseCode/{courseCode}{newStudentId: (/newStudentId/[^/]+?)?}{newCourseCode:(/newCourseCode/[^/]+?)?}")
    public Response updateRegistersCourse(@PathParam("studentId") Long studentId, @PathParam("courseCode") String courseCode,
                                          @PathParam("newStudentId") String newStudentId, @PathParam("newCourseCode") String newCourseCode){
        if (studentId == null || isEmpty(courseCode)){
            return Response.status(400).build();
        }

        RegistersCourseEntity registersCourse = registersCourseService.getRegistersCourse(studentId, courseCode);
        if (registersCourse == null){
            return Response.status(404).build();
        }

        if (newStudentId != null){
            registersCourse.setStudentId(Long.parseLong(newStudentId.split("/")[2]));
        }

        if (!isEmpty(newCourseCode)){
            registersCourse.setCourseCode(newCourseCode.split("/")[2]);
        }

        registersCourseService.updateRegistersCourse(registersCourse);
        return Response.ok().build();
    }

    private boolean isEmpty(String text){
        return text.equals("");
    }
}
