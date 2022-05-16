package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.CourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@LocalBean
@Path("/bean/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseServiceImpl implements CourseService{

    @Inject
    private CourseDao courseDao;

    @Override
    public void addCourse(CourseEntity courseEntity) {
        courseDao.addCourse(courseEntity);
    }

    @Override
    public void addCourses(List<CourseEntity> courseEntities) {
        courseDao.addCourses(courseEntities);
    }

    @Override
    public void deleteCourse(CourseEntity courseEntity) {
        courseDao.deleteCourse(courseEntity);
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseDao.updateCourse(courseEntity);
    }

    @Override
    public CourseEntity getCourseByCourseCode(String courseCode) {
        return courseDao.findCourseByCourseCode(courseCode);
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseDao.findAllCourses();
    }

    @POST // Tested using postman
    public Response addCoursee(CourseEntity course){
        addCourse(course);
        return Response.status(201).build();
    }

    @GET
    @Path("/courseCode={courseCode}") // Tested using postman
    public Response getCourseByCourseCodee(@PathParam("courseCode") String courseCode){
        CourseEntity course = getCourseByCourseCode(courseCode);
        return Response.ok(course).build();
    }

    @PUT
    @Path("/courseCode/{courseCode}{courseName: (/courseName/[^/]+?)?}") // Tested using postman
    public Response updateCoursee(@PathParam("courseCode") String courseCode, @PathParam("courseName") String courseName){
        if (courseCode == null){
            return Response.status(400).build();
        }

        CourseEntity course = getCourseByCourseCode(courseCode);
        if (course == null){
            return Response.status(404).build();
        }

        if (!isEmpty(courseName)){
            course.setCourseName(courseName.split("/")[2]);
        }

        updateCourse(course);
        return Response.ok().build();
    }

    @DELETE
    @Path("/courseCode={courseCode}") // Tested using postman
    public Response deleteCoursee(@PathParam("courseCode") String courseCode){
        if (courseCode == null){
            return Response.status(400).build();
        }

        CourseEntity course = getCourseByCourseCode(courseCode);
        if (course == null){
            return Response.status(404).build();
        }

        deleteCourse(course);
        return Response.status(204).build();
    }

    public void getCoursesEligibleForStudent(){

    }

    @GET
    @Path("/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCoursess(){
        List<CourseEntity> allCourses = getAllCourses();
        return Response.ok(allCourses).build();
    }

    private boolean isEmpty(String text){
        return text.equals("");
    }
}
