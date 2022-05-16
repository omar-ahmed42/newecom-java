package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.StaffStudentCourseDao;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@LocalBean
@Path("/bean/staff/course")
public class StaffStudentCourseImpl implements StaffStudentCourse {

    @Inject
    private StaffStudentCourseDao staffStudentCourseDao;

    @Override
    public List<StudentEntity> getStudentsInStaffCourse(Long staffId, String courseCode){
        return staffStudentCourseDao.getStudentsInStaffCourse(staffId, courseCode);
    }


    @POST
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getStudentsInStaffCoursee(TeachesCourseEntityPK teachesCourseEntityPK){
        try {
            List<StudentEntity> studentsInStaffCourse = getStudentsInStaffCourse(teachesCourseEntityPK.getStaffId(), teachesCourseEntityPK.getCourseCode());
            return ResponseUtils.buildSuccessfulRequest(200, studentsInStaffCourse);
        } catch (Exception e){
            return ResponseUtils.buildFailedRequest(500);
        }

    }

}
