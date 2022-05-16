package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.CompletedCourseServiceImpl;
import com.omarahmed42.newecomservlets.ejbs.StudentServiceImpl;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.ID;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/past/courses")
public class PastCourseController {


    @EJB
    private StudentServiceImpl studentService;

    @EJB
    private CompletedCourseServiceImpl completedCourseService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCurrentCourses(ID idWrapper) {
        try {
            StudentEntity student = studentService.getStudentById(idWrapper.id);
            List<CompletedCourseEntity> completedCoursesForStudent = completedCourseService.getCompletedCoursesForStudent(student);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("data", completedCoursesForStudent);
            responseMap.put(ResponseUtils.SUCCESS, true);
            return Response.ok(ResponseUtils.generateJsonResponse(responseMap)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.buildFailedRequest(500);
        }
    }

}
