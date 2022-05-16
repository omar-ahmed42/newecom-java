package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.RegistersCourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@LocalBean
public class RegistersCourseServiceImpl implements RegistersCourseService{

    @Inject
    private RegistersCourseDao registersCourseDao;

    @Override
    public void registerInACourse(RegistersCourseEntity registersCourseEntity) {
        registersCourseDao.addRegistersCourse(registersCourseEntity);
    }

    @Override
    public RegistersCourseEntity getRegistersCourse(Long studentId, String courseCode){
        return registersCourseDao.getRegistersCourse(studentId, courseCode);
    }

    @Override
    public void dropFromACourse(RegistersCourseEntity registersCourseEntity) {
        registersCourseDao.deleteRegistersCourse(registersCourseEntity);
    }

    @Override
    public void updateRegistersCourse(RegistersCourseEntity registersCourseEntity) {
        registersCourseDao.updateRegistersCourse(registersCourseEntity);
    }

    @Override
    public List<RegistersCourseEntity> getRegisteredCoursesForStudent(StudentEntity student) {
        return registersCourseDao.getRegisteredCoursesForStudent(student);
    }

    @Override
    public List<RegistersCourseEntity> getRegisteredCoursesForCourse(CourseEntity course) {
        return registersCourseDao.getRegisteredCoursesForCourse(course);
    }
}
