package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;

import java.util.List;

public interface TeachesCourseDao {
    TeachesCourseEntity findTeachesCourseByPK(TeachesCourseEntityPK teachesCourseEntityPK);

    List<CourseEntity> getCoursesTaughtByStaff(Long id);

    void addTeachesCourse(TeachesCourseEntity teachesCourseEntity);
    void deleteTeachesCourse(TeachesCourseEntity teachesCourseEntity);
    void updateTeachesCourse(TeachesCourseEntity teachesCourseEntity);
}
