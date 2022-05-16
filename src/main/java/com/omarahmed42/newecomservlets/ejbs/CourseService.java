package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.CourseEntity;

import java.util.List;

public interface CourseService {
    void addCourse(CourseEntity courseEntity);
    void addCourses(List<CourseEntity> courseEntities);
    void deleteCourse(CourseEntity courseEntity);
    void updateCourse(CourseEntity courseEntity);
    CourseEntity getCourseByCourseCode(String courseCode);

    List<CourseEntity> getAllCourses();
}
