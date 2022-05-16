package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.CourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

@Singleton
@LocalBean
public class AllCoursesServiceImpl implements AllCoursesService {

    @Inject
    private CourseDao courseDao;

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseDao.findAllCoursesForAll();
    }
}
