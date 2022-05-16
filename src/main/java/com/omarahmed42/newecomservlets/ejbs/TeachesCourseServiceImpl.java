package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.TeachesCourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;
import com.omarahmed42.newecomservlets.structures.ID;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@LocalBean
@Path("/bean/teach/course")
public class TeachesCourseServiceImpl implements TeachesCourseService {

    @Inject
    private TeachesCourseDao teachesCourseDao;

    @Override
    public void assignStaffToCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseDao.addTeachesCourse(teachesCourseEntity);
    }

    @Override
    public void removeStaffFromCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseDao.deleteTeachesCourse(teachesCourseEntity);
    }

    @Override
    public void removeStaffFromCourse(TeachesCourseEntityPK teachesCourseEntityPK) {
        TeachesCourseEntity teachesCourse = teachesCourseDao.findTeachesCourseByPK(teachesCourseEntityPK);
        removeStaffFromCourse(teachesCourse);
    }

    @Override
    public void updateStaffTeachingCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseDao.updateTeachesCourse(teachesCourseEntity);
    }

    @Override
    public List<CourseEntity> getCoursesTaughtByStaff(Long id){
        return teachesCourseDao.getCoursesTaughtByStaff(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignStaffToCoursee(TeachesCourseEntity teachesCourseEntity) {
        assignStaffToCourse(teachesCourseEntity);
        return Response.status(201).build();
    }


    @DELETE
    @Path("/staffId/{staffId}/courseCode/{courseCode}")
    public Response removeStaffFromCoursee(@PathParam("staffId") Long staffId, @PathParam("courseCode") String courseCode) {
        TeachesCourseEntityPK teachesCourseEntityPK = new TeachesCourseEntityPK(staffId, courseCode);
        removeStaffFromCourse(teachesCourseEntityPK);
        return Response.status(204).build();
    }

    @POST
    @Path("/courses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoursesTaughtByStafff(ID idWrapper) {
        List<CourseEntity> courses = getCoursesTaughtByStaff(idWrapper.id);
        return ResponseUtils.buildSuccessfulRequest(200, courses);
    }
}
