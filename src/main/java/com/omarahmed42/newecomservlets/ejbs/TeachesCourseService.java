package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;

import java.util.List;

public interface TeachesCourseService {
    void assignStaffToCourse(TeachesCourseEntity teachesCourseEntity);
    void removeStaffFromCourse(TeachesCourseEntity teachesCourseEntity);
    void removeStaffFromCourse(TeachesCourseEntityPK teachesCourseEntityPK);
    void updateStaffTeachingCourse(TeachesCourseEntity teachesCourseEntity);

    List<CourseEntity> getCoursesTaughtByStaff(Long id);
}
