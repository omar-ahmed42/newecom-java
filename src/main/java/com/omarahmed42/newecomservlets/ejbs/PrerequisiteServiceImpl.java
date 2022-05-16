package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.PrerequisiteDao;
import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
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
}
