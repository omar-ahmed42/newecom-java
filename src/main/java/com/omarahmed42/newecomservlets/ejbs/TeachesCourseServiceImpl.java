package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.TeachesCourseDao;
import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class TeachesCourseServiceImpl implements TeachesCourseService {

    @Inject
    private TeachesCourseDao teachesCourseDao;

    @Override
    public void assignStaffToCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseDao.addTeachesCourse(teachesCourseEntity);
    }

    @Override
    public void removeStaffFromCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseDao.deleteTeachesCourse(teachesCourseEntity);
    }

    @Override
    public void removeStaffFromCourse(TeachesCourseEntityPK teachesCourseEntityPK) {
        TeachesCourseEntity teachesCourse = teachesCourseDao.findTeachesCourseByPK(teachesCourseEntityPK);
        removeStaffFromCourse(teachesCourse);
    }

    @Override
    public void updateStaffTeachingCourse(TeachesCourseEntity teachesCourseEntity) {
        teachesCourseDao.updateTeachesCourse(teachesCourseEntity);
    }

    @Override
    public List<CourseEntity> getCoursesTaughtByStaff(Long id){
        return teachesCourseDao.getCoursesTaughtByStaff(id);
    }
}
