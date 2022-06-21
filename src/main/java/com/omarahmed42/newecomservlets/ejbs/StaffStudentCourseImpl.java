package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.StaffStudentCourseDao;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class StaffStudentCourseImpl implements StaffStudentCourse {

    @Inject
    private StaffStudentCourseDao staffStudentCourseDao;

    @Override
    public List<StudentEntity> getStudentsInStaffCourse(Long staffId, String courseCode) {
        return staffStudentCourseDao.getStudentsInStaffCourse(staffId, courseCode);
    }
}
