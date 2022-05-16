package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.PrerequisiteDao;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/bean/prerequisite")
public class PrerequisiteServiceImpl implements PrerequisiteService {

    @Inject
    private PrerequisiteDao prerequisiteDao;

    @Override
    public List<PrerequisiteEntity> getEligibleCoursesForAcademicYear(AcademicYear academicYear) {
        return prerequisiteDao.getEligibleCoursesForAcademicYear(academicYear);
    }

    @Override
    public void addPrerequisite(PrerequisiteEntity prerequisite) {
        prerequisiteDao.addPrerequisite(prerequisite);
    }


    @POST
    @Path("/course/")
    public Response addPrerequisitee(PrerequisiteEntity prerequisite) {
        addPrerequisite(prerequisite);
        return Response.ok().build();
    }
}
