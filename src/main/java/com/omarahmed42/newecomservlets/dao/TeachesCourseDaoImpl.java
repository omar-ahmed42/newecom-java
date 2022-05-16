package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntity;
import com.omarahmed42.newecomservlets.entities.TeachesCourseEntityPK;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeachesCourseDaoImpl implements TeachesCourseDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TeachesCourseEntity findTeachesCourseByPK(TeachesCourseEntityPK teachesCourseEntityPK) {
        return entityManager.find(TeachesCourseEntity.class, teachesCourseEntityPK);
    }

    @Override
    public List<CourseEntity> getCoursesTaughtByStaff(Long id) {
        String queryStatement = "SELECT NEW CourseEntity(tc.courseByCourseCode.courseCode, tc.courseByCourseCode.courseName) FROM TeachesCourseEntity tc WHERE tc.staffId = :id";
        TypedQuery<CourseEntity> query = entityManager.createQuery(queryStatement, CourseEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void addTeachesCourse(TeachesCourseEntity teachesCourseEntity) {
        entityManager.persist(teachesCourseEntity);
        entityManager.flush();
    }

    @Override
    public void deleteTeachesCourse(TeachesCourseEntity teachesCourseEntity) {
        entityManager.remove(entityManager.contains(teachesCourseEntity) ? teachesCourseEntity : entityManager.merge(teachesCourseEntity));
    }

    @Override
    public void updateTeachesCourse(TeachesCourseEntity teachesCourseEntity) {
        entityManager.merge(teachesCourseEntity);
    }
}
