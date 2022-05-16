package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.CourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@LocalBean
@Path("/bean/all/courses")
public class AllCoursesServiceImpl implements AllCoursesService {

    @Inject
    private CourseDao courseDao;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseEntity> getAllCourses() {
        return courseDao.findAllCoursesForAll();
    }
}
