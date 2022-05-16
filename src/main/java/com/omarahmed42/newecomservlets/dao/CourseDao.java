package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CourseEntity;

import java.util.List;

public interface CourseDao {
    CourseEntity findCourseByCourseCode(String courseCode);
    void addCourse(CourseEntity courseEntity);
    void deleteCourse(CourseEntity courseEntity);
    void updateCourse(CourseEntity courseEntity);
    void addCourses(List<CourseEntity> courseEntities);
    List<CourseEntity> findAllCourses();
    List<CourseEntity> findAllCoursesForAll();
}
