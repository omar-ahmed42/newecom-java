package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RegistersCourseService {
    void registerInACourse(RegistersCourseEntity registersCourseEntity);

    RegistersCourseEntity getRegistersCourse(Long studentId, String courseCode);

    void dropFromACourse(RegistersCourseEntity registersCourseEntity);
    void updateRegistersCourse(RegistersCourseEntity registersCourseEntity);
    List<RegistersCourseEntity> getRegisteredCoursesForStudent(StudentEntity student);
    List<RegistersCourseEntity> getRegisteredCoursesForCourse(CourseEntity course);
}
