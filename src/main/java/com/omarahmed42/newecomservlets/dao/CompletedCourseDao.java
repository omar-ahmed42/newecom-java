package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import java.util.List;

public interface CompletedCourseDao {
    CompletedCourseEntity findCompletedCourseById(Long id);
    void addCompletedCourse(CompletedCourseEntity completedCourseEntity);
    void deleteCompletedCourse(CompletedCourseEntity completedCourseEntity);
    void updateCompletedCourse(CompletedCourseEntity completedCourseEntity);

    void updateGradeToCompletedCourse(Long completedCourseId, Double grade);

    List<CompletedCourseEntity> getCompletedCoursesForStudent(StudentEntity student);
}
