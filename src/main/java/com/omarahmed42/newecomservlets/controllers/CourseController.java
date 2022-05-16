package com.omarahmed42.newecomservlets.controllers;


import com.omarahmed42.newecomservlets.ejbs.CourseServiceImpl;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseController {

    //TODO: Make viewAllCourses (For admins) Singleton
    @EJB
    private CourseServiceImpl courseService;

    @POST // Tested using postman
    public Response addCourse(CourseEntity course){
        courseService.addCourse(course);
        return Response.status(201).build();
    }

    @GET
    @Path("/courseCode={courseCode}") // Tested using postman
    public Response getCourseByCourseCode(@PathParam("courseCode") String courseCode){
        CourseEntity course = courseService.getCourseByCourseCode(courseCode);
        return Response.ok(course).build();
    }

    @PUT
    @Path("/courseCode/{courseCode}{courseName: (/courseName/[^/]+?)?}") // Tested using postman
    public Response updateCourse(@PathParam("courseCode") String courseCode, @PathParam("courseName") String courseName){
        if (courseCode == null){
            return Response.status(400).build();
        }

        CourseEntity course = courseService.getCourseByCourseCode(courseCode);
        if (course == null){
            return Response.status(404).build();
        }

        if (!isEmpty(courseName)){
            course.setCourseName(courseName.split("/")[2]);
        }

        courseService.updateCourse(course);
        return Response.ok().build();
    }

    @DELETE
    @Path("/courseCode={courseCode}") // Tested using postman
    public Response deleteCourse(@PathParam("courseCode") String courseCode){
        if (courseCode == null){
            return Response.status(400).build();
        }

        CourseEntity course = courseService.getCourseByCourseCode(courseCode);
        if (course == null){
            return Response.status(404).build();
        }

        courseService.deleteCourse(course);
        return Response.status(204).build();
    }

    public void getCoursesEligibleForStudent(){

    }

    @GET
    @Path("/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses(){
        List<CourseEntity> allCourses = courseService.getAllCourses();
        return Response.ok(allCourses).build();
    }

    private boolean isEmpty(String text){
        return text.equals("");
    }
}
