package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.StudentEntity;

import java.util.List;

public interface StaffStudentCourse {
    List<StudentEntity> getStudentsInStaffCourse(Long staffId, String courseCode);
}
