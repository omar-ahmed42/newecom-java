package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CourseEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntity;
import com.omarahmed42.newecomservlets.entities.RegistersCourseEntityPK;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class RegistersCourseDaoImpl implements RegistersCourseDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RegistersCourseEntity findRegistersCourseById(Long id) {
        return entityManager.find(RegistersCourseEntity.class, id);
    }

    @Override
    public void addRegistersCourse(RegistersCourseEntity registersCourseEntity) {
        entityManager.persist(registersCourseEntity);
        entityManager.flush();
    }

    @Override
    public void addRegistersCourses(Set<RegistersCourseEntity> registersCourses) {
        // TODO: Enable batch persistence
        for (RegistersCourseEntity registersCourseEntity : registersCourses){
            entityManager.persist(registersCourseEntity);
        }
        entityManager.flush();
    }

    @Override
    public RegistersCourseEntity getRegistersCourse(Long studentId, String courseCode){
        RegistersCourseEntityPK registersCoursePK = new RegistersCourseEntityPK();
        registersCoursePK.setStudentId(studentId.longValue());
        registersCoursePK.setCourseCode(courseCode);
        return entityManager.find(RegistersCourseEntity.class, registersCoursePK);
    }

    @Override
    public void deleteRegistersCourse(RegistersCourseEntity registersCourseEntity) {
        entityManager.remove(entityManager.contains(registersCourseEntity) ? registersCourseEntity : entityManager.merge(registersCourseEntity));
    }

    @Override
    public List<RegistersCourseEntity> getRegisteredCoursesForStudent(StudentEntity student) {
        String queryStatement = "SELECT NEW RegistersCourseEntity(studentId, courseCode) FROM RegistersCourseEntity WHERE studentId = :id";
        TypedQuery typedQuery = entityManager.createQuery(queryStatement, RegistersCourseEntity.class);
        typedQuery.setParameter("id", student.getStudentId());
        return typedQuery.getResultList();
    }

    @Override
    public List<RegistersCourseEntity> getRegisteredCoursesForCourse(CourseEntity course) {
        String queryStatement = "SELECT studentId, courseCode FROM RegistersCourseEntity WHERE courseCode = :course_code";
        TypedQuery typedQuery = entityManager.createQuery(queryStatement, RegistersCourseEntity.class);
        typedQuery.setParameter("course_code", course.getCourseCode());
        return (List<RegistersCourseEntity>) typedQuery.getResultList();
    }

    @Override
    public void updateRegistersCourse(RegistersCourseEntity registersCourseEntity) { // Not working correctly
        entityManager.merge(registersCourseEntity);
        entityManager.flush();
    }
}
