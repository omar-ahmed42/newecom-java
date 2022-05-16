package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.StudentEntity;

import java.util.List;

public interface StaffStudentCourseDao {
    List<StudentEntity> getStudentsInStaffCourse(Long staffId, String courseCode);
}
