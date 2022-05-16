package com.omarahmed42.newecomservlets.dao;

import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import java.util.List;

public interface PrerequisiteDao {
    PrerequisiteEntity findPrerequisiteById(Long id);

    void addPrerequisite(PrerequisiteEntity prerequisiteEntity);

    void deletePrerequisite(PrerequisiteEntity prerequisiteEntity);

    void updatePrerequisite(PrerequisiteEntity prerequisiteEntity);

    List<PrerequisiteEntity> getEligibleCoursesForAcademicYear(AcademicYear academicYear);

    List<PrerequisiteEntity> getPrerequisitesByCourseCode(String courseCode);

}
