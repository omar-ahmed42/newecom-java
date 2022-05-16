package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import java.util.List;

public interface PrerequisiteService {
    List<PrerequisiteEntity> getEligibleCoursesForAcademicYear(AcademicYear academicYear);

    void addPrerequisite(PrerequisiteEntity prerequisite);
}
