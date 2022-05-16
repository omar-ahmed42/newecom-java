package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.RegistersCourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateful
@LocalBean
@Path("/bean/course/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistersCourseServiceImpl implements RegistersCourseService{

    @Inject
    private RegistersCourseDao registersCourseDao;

    @Override
    public void registerInACourse(RegistersCourseEntity registersCourseEntity) {
        registersCourseDao.addRegistersCourse(registersCourseEntity);
    }

    @Override
    public RegistersCourseEntity getRegistersCourse(Long studentId, String courseCode){
        return registersCourseDao.getRegistersCourse(studentId, courseCode);
    }

    @Override
    public void dropFromACourse(RegistersCourseEntity registersCourseEntity) {
        registersCourseDao.deleteRegistersCourse(registersCourseEntity);
    }

    @Override
    public void updateRegistersCourse(RegistersCourseEntity registersCourseEntity) {
        registersCourseDao.updateRegistersCourse(registersCourseEntity);
    }

    @Override
    public List<RegistersCourseEntity> getRegisteredCoursesForStudent(StudentEntity student) {
        return registersCourseDao.getRegisteredCoursesForStudent(student);
    }

    @Override
    public List<RegistersCourseEntity> getRegisteredCoursesForCourse(CourseEntity course) {
        return registersCourseDao.getRegisteredCoursesForCourse(course);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registersInACoursee(RegistersCourseEntity registersCourseEntity){ // Tested using postman
        registerInACourse(registersCourseEntity);
        return Response.status(201).build();
    }

    @DELETE
    @Path("/studentId/{studentId}/courseCode/{courseCode}")
    public Response dropFromCoursee(@PathParam("studentId") Long studentId, @PathParam("courseCode") String courseCode){ // Tested using postman
        RegistersCourseEntity registersCourse = getRegistersCourse(studentId, courseCode);
        dropFromACourse(registersCourse);
        return Response.status(204).build();
    }

    @PUT
    @Path("/studentId/{studentId}/courseCode/{courseCode}{newStudentId: (/newStudentId/[^/]+?)?}{newCourseCode:(/newCourseCode/[^/]+?)?}")
    public Response updateRegistersCoursee(@PathParam("studentId") Long studentId, @PathParam("courseCode") String courseCode,
                                          @PathParam("newStudentId") String newStudentId, @PathParam("newCourseCode") String newCourseCode){
        if (studentId == null || isEmpty(courseCode)){
            return Response.status(400).build();
        }

        RegistersCourseEntity registersCourse = getRegistersCourse(studentId, courseCode);
        if (registersCourse == null){
            return Response.status(404).build();
        }

        if (newStudentId != null){
            registersCourse.setStudentId(Long.parseLong(newStudentId.split("/")[2]));
        }

        if (!isEmpty(newCourseCode)){
            registersCourse.setCourseCode(newCourseCode.split("/")[2]);
        }

        updateRegistersCourse(registersCourse);
        return Response.ok().build();
    }

    private boolean isEmpty(String text){
        return text.equals("");
    }
}
