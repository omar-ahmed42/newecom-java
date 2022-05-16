package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompletedCourseDaoImpl implements CompletedCourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CompletedCourseEntity findCompletedCourseById(Long id) {
        return entityManager.find(CompletedCourseEntity.class, id);
    }

    @Override
    public void addCompletedCourse(CompletedCourseEntity completedCourseEntity) {
        entityManager.persist(completedCourseEntity);
        entityManager.flush();
    }

    @Override
    public void deleteCompletedCourse(CompletedCourseEntity completedCourseEntity) {
        entityManager.remove(entityManager.contains(completedCourseEntity) ? completedCourseEntity : entityManager.merge(completedCourseEntity));
    }

    @Override
    public void updateCompletedCourse(CompletedCourseEntity completedCourseEntity) {
        entityManager.merge(completedCourseEntity);
    }

    @Override
    public void updateGradeToCompletedCourse(Long completedCourseId, Double grade) {
        String queryStatement = "UPDATE CompletedCourseEntity SET grade = :grade WHERE id = :id";
        Query query = entityManager.createQuery(queryStatement);
        query.setParameter("id", completedCourseId);
        query.setParameter("grade", grade);
        query.executeUpdate();
    }

    @Override
    public List<CompletedCourseEntity> getCompletedCoursesForStudent(StudentEntity student) {
        String queryStatement = "SELECT NEW CompletedCourseEntity(id, studentId, courseCode, grade) FROM CompletedCourseEntity WHERE studentId = :id";
        TypedQuery typedQuery = entityManager.createQuery(queryStatement, CompletedCourseEntity.class);
        typedQuery.setParameter("id", student.getStudentId());
        return (List<CompletedCourseEntity>) typedQuery.getResultList();
    }
}
