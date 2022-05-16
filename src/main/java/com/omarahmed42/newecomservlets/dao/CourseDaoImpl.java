package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CourseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CourseDaoImpl implements CourseDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CourseEntity findCourseByCourseCode(String courseCode) {
        return entityManager.find(CourseEntity.class, courseCode);
    }

    @Override
    public void addCourse(CourseEntity courseEntity) {
        entityManager.persist(courseEntity);
    }

    @Override
    public void deleteCourse(CourseEntity courseEntity) {
        entityManager.remove(entityManager.contains(courseEntity) ? courseEntity : entityManager.merge(courseEntity));
    }

    @Override
    public void updateCourse(CourseEntity courseEntity) {
        entityManager.merge(courseEntity);
        entityManager.flush();
    }

    @Override
    public void addCourses(List<CourseEntity> courseEntities) {
        for (CourseEntity course : courseEntities){
            entityManager.persist(course);
            entityManager.flush();
        }
    }

    @Override
    public List<CourseEntity> findAllCourses(){
        return entityManager.createQuery("SELECT c FROM CourseEntity c", CourseEntity.class).getResultList();
    }

    @Override
    public List<CourseEntity> findAllCoursesForAll(){
        return entityManager.createQuery("SELECT NEW CourseEntity(c.courseCode, c.courseName) FROM CourseEntity c", CourseEntity.class).getResultList();
    }
}
