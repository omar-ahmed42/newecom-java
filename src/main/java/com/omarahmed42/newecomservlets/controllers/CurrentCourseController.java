package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.RegistersCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.ID;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/current/courses")
public class CurrentCourseController {

    @EJB
    private StudentServiceImpl studentService;

    @EJB
    private RegistersCourseServiceImpl registersCourseService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCurrentCourses(ID idWrapper){
        try{
            StudentEntity student = studentService.getStudentById(idWrapper.id);
            List<RegistersCourseEntity> registeredCoursesForStudent = registersCourseService.getRegisteredCoursesForStudent(student);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("data", registeredCoursesForStudent);
            responseMap.put(ResponseUtils.SUCCESS, true);
            return Response.ok(ResponseUtils.generateJsonResponse(responseMap)).build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.buildFailedRequest(500);
        }
    }

}
