package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.Range;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StudentEntity findStudentById(Long id) {
        return entityManager.find(StudentEntity.class, id);
    }

    @Override
    public List<StudentEntity> findSliceOfStudents(int page, int size) {
        TypedQuery<StudentEntity> typedQuery = entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class);
        typedQuery.setFirstResult((page - 1) * size);
        typedQuery.setMaxResults(size);
        return typedQuery.getResultList();
    }

    @Override
    public List<StudentEntity> findAllStudents() {
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
    public Range findMinAndMaxIdsInRange(Long min, Long max) {
        Query findMaxQuery = entityManager.createQuery("SELECT MAX(student.studentId) FROM StudentEntity student WHERE student.studentId <= :max");
        findMaxQuery.setParameter("max", max);
        Long maxResult = (Long) findMaxQuery.getSingleResult();
        Query findMinQuery = entityManager.createQuery("SELECT MIN(student.studentId) FROM StudentEntity student WHERE student.studentId >= :min");
        findMinQuery.setParameter("min", min);
        Long minResult = (Long) findMinQuery.getSingleResult();

        if (Objects.nonNull(maxResult) && Objects.nonNull(minResult) && minResult.equals(min) && maxResult.equals(max)) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("get_next_student_id_procedure");
            storedProcedureQuery.setParameter("min_id", minResult);
            storedProcedureQuery.setParameter("max_id", maxResult);
            Long nextId = (Long) storedProcedureQuery.getOutputParameterValue("next_student_id");
            minResult = nextId;
        }

        return new Range(minResult, maxResult);
    }

    @Override
    public Range findMinAndMaxIdsOrNextInRange(Long min, Long max) {
        Query findMaxQuery = entityManager.createQuery("SELECT MAX(student.studentId) FROM StudentEntity student WHERE student.studentId <= :max");
        findMaxQuery.setParameter("max", max);
        Long maxResult = (Long) findMaxQuery.getSingleResult();
        Query findMinQuery = entityManager.createQuery("SELECT MIN(student.studentId) FROM StudentEntity student WHERE student.studentId >= :min");
        findMinQuery.setParameter("min", min);
        Long minResult = (Long) findMinQuery.getSingleResult();

        if (Objects.nonNull(maxResult) && Objects.nonNull(minResult) && minResult.equals(min) && maxResult.equals(max)) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("get_next_student_id_procedure");
            storedProcedureQuery.setParameter("min_id", minResult);
            storedProcedureQuery.setParameter("max_id", maxResult);
            Long nextId = (Long) storedProcedureQuery.getOutputParameterValue("next_student_id");
            minResult = nextId;
            return new Range(minResult, maxResult, true);
        }

        return new Range(minResult, maxResult, false);
    }

    @Override
    public Long countNumberOfIdsInRange(Long min, Long max) { //"SELECT COUNT(student.studentId) FROM StudentEntity student WHERE studentId >= min and studentId <= :max"
        String queryStatement = "SELECT COUNT(student.studentId) FROM StudentEntity student WHERE student.studentId >= :min AND student.studentId <= :max";
        Query query = entityManager.createQuery(queryStatement);
        query.setParameter("min", min);
        query.setParameter("max", max);
        Long count = (Long) query.getSingleResult();
        return count;

    }

}
