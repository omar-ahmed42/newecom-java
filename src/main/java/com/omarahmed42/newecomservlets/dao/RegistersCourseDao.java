package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import java.util.List;
import java.util.Set;

public interface RegistersCourseDao {
    RegistersCourseEntity findRegistersCourseById(Long id);
    void addRegistersCourse(RegistersCourseEntity registersCourseEntity);
    void addRegistersCourses(Set<RegistersCourseEntity> registersCourses);

    RegistersCourseEntity getRegistersCourse(Long studentId, String courseCode);

    void deleteRegistersCourse(RegistersCourseEntity registersCourseEntity);
    void updateRegistersCourse(RegistersCourseEntity registersCourseEntity);
    List<RegistersCourseEntity> getRegisteredCoursesForStudent(StudentEntity student);
    List<RegistersCourseEntity> getRegisteredCoursesForCourse(CourseEntity course);
}
