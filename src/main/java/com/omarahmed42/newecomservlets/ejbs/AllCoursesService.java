package com.omarahmed42.newecomservlets.ejbs;

import com.omarahmed42.newecomservlets.entities.CourseEntity;

import java.util.List;

public interface AllCoursesService {
    List<CourseEntity> getAllCourses();
}
