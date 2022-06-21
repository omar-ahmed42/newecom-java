package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.CourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class CourseServiceImpl implements CourseService {

    @Inject
    private CourseDao courseDao;

    @Override
    public void addCourse(CourseEntity courseEntity) {
        courseDao.addCourse(courseEntity);
    }

    @Override
    public void addCourses(List<CourseEntity> courseEntities) {
        courseDao.addCourses(courseEntities);
    }

    @Override
    public void deleteCourse(CourseEntity courseEntity) {
        courseDao.deleteCourse(courseEntity);
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        courseDao.updateCourse(courseEntity);
    }

    @Override
    public CourseEntity getCourseByCourseCode(String courseCode) {
        return courseDao.findCourseByCourseCode(courseCode);
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseDao.findAllCourses();
    }
}
