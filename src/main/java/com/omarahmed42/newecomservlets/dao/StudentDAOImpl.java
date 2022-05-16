package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.Range;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class StudentDAOImpl implements StudentDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StudentEntity findStudentById(Long id) {
        return entityManager.find(StudentEntity.class, id);
    }

    @Override
    public List<StudentEntity> findSliceOfStudents(int page, int size){
        TypedQuery<StudentEntity> typedQuery = entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class);
        typedQuery.setFirstResult((page-1) * size);
        typedQuery.setMaxResults(size);
        return typedQuery.getResultList();
    }

    @Override
    public List<StudentEntity> findAllStudents(){
        return entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class).getResultList();
    }

    @Override
    public void addStudent(StudentEntity studentEntity) {
        entityManager.persist(studentEntity);
    }

    @Override
    public void deleteStudent(StudentEntity studentEntity) {
        entityManager.remove(entityManager.contains(studentEntity) ? studentEntity : entityManager.merge(studentEntity));
    }

    @Override
    public void updateStudent(StudentEntity studentEntity) {
        entityManager.merge(studentEntity);
    }

    @Override
    public StudentEntity findStudentByCredentials(Long id, String password) {
        TypedQuery<StudentEntity> studentTypedQuery = entityManager.createQuery("SELECT student FROM StudentEntity student WHERE student.studentId = :id AND student.password = :password", StudentEntity.class);
        studentTypedQuery.setParameter("id", id);
        studentTypedQuery.setParameter("password", password);
        return studentTypedQuery.getSingleResult();
    }

    @Override
    public Range findMinAndMaxIdsInRange(Long min, Long max){
        Query findMaxQuery = entityManager.createQuery("SELECT MAX(student.studentId) FROM StudentEntity student WHERE student.studentId <= :max");
        findMaxQuery.setParameter("max", max);
        Long maxResult = (Long) findMaxQuery.getSingleResult();
        Query findMinQuery = entityManager.createQuery("SELECT MIN(student.studentId) FROM StudentEntity student WHERE student.studentId >= :min");
        findMinQuery.setParameter("min", min);
        Long minResult = (Long) findMinQuery.getSingleResult();


        return new Range(minResult, maxResult);
    }

}
