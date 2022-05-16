package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.CompletedCourseDao;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Stateless
@LocalBean
@Path("/bean/completed/course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompletedCourseServiceImpl implements CompletedCourseService{

    @Inject
    private CompletedCourseDao completedCourseDao;

    @Override
    public CompletedCourseEntity findCompletedCourse(Long id) {
        return completedCourseDao.findCompletedCourseById(id);
    }

    @Override
    public void addCompletedCourse(CompletedCourseEntity completedCourse) {
        completedCourseDao.addCompletedCourse(completedCourse);
    }

    @Override
    public void deleteCompletedCourse(CompletedCourseEntity completedCourse) {
        completedCourseDao.deleteCompletedCourse(completedCourse);
    }

    @Override
    public void updateGrade(CompletedCourseEntity completedCourse) {
        completedCourseDao.updateCompletedCourse(completedCourse);
    }

    @Override
    public void updateGrade(Long completedCourseId, Double grade) {
        completedCourseDao.updateGradeToCompletedCourse(completedCourseId, grade);
    }

    @Override
    public List<CompletedCourseEntity> getCompletedCoursesForStudent(StudentEntity student){
        return completedCourseDao.getCompletedCoursesForStudent(student);

    }


    @POST
    @Path("/complete")
    public Response addCompletedCoursee(CompletedCourseEntity completedCourse) {
        addCompletedCourse(completedCourse);
        return ResponseUtils.buildSuccessfulRequest(201, null);
    }

    @DELETE
    @Path("/del/{id}")
    public Response deleteCompletedCoursee(@PathParam("id") Long id) {
        if (Objects.isNull(id)) {
            return ResponseUtils.buildFailedRequest(400);
        }

        CompletedCourseEntity completedCourse = findCompletedCourse(id);
        if (Objects.isNull(completedCourse)) {
            return ResponseUtils.buildFailedRequest(404);
        }

        try {
            deleteCompletedCourse(completedCourse);
            return ResponseUtils.buildSuccessfulRequest(204, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.buildFailedRequest(500);
        }
    }

    @POST
    @Path("/grade")
    public Response addGradeToCompletedCoursee(CompletedCourseEntity completedCourse) { // id, grade
        updateGrade(completedCourse.getId(), completedCourse.getGrade());
        return ResponseUtils.buildSuccessfulRequest(200, null);
    }

}
