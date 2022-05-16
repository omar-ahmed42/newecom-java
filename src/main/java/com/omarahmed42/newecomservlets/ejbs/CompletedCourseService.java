package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import java.util.List;

public interface CompletedCourseService {
    void addCompletedCourse(CompletedCourseEntity completedCourse);
    void deleteCompletedCourse(CompletedCourseEntity completedCourse);
    void updateGrade(CompletedCourseEntity completedCourse);
    void updateGrade(Long completedCourseId, Double grade);
    CompletedCourseEntity findCompletedCourse(Long id);

    List<CompletedCourseEntity> getCompletedCoursesForStudent(StudentEntity student);
}
