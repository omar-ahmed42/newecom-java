package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.PrerequisiteEntity;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrerequisiteDaoImpl implements PrerequisiteDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PrerequisiteEntity findPrerequisiteById(Long id) {
        return entityManager.find(PrerequisiteEntity.class, id);
    }

    @Override
    public void addPrerequisite(PrerequisiteEntity prerequisite) {
        entityManager.persist(prerequisite);
//        entityManager.flush();
    }

    @Override
    public void deletePrerequisite(PrerequisiteEntity prerequisiteEntity) {
        entityManager.remove(entityManager.contains(prerequisiteEntity) ? prerequisiteEntity : entityManager.merge(prerequisiteEntity));
    }

    @Override
    public void updatePrerequisite(PrerequisiteEntity prerequisiteEntity) {
        entityManager.merge(prerequisiteEntity);
    }

    @Override
    public List<PrerequisiteEntity> getEligibleCoursesForAcademicYear(AcademicYear academicYear) {
        TypedQuery<PrerequisiteEntity> typedQuery = (TypedQuery<PrerequisiteEntity>) entityManager
                .createQuery("SELECT NEW com.omarahmed42.newecomservlets.entities.PrerequisiteEntity(p.id AS id, p.courseCode AS courseCode, c.courseName AS courseName, p.academicYear AS academicYear) FROM PrerequisiteEntity p INNER JOIN CourseEntity c ON p.courseCode = c.courseCode WHERE p.academicYear = :year"
                );
        typedQuery.setParameter("year", academicYear);
        List<PrerequisiteEntity> courses = typedQuery.getResultList();
        return courses;
    }

    @Override
    public List<PrerequisiteEntity> getPrerequisitesByCourseCode(String courseCode){
        TypedQuery<PrerequisiteEntity> typedQuery = entityManager.createQuery("SELECT p FROM PrerequisiteEntity p WHERE p.courseCode = :code", PrerequisiteEntity.class);
        typedQuery.setParameter("code", courseCode);
        return typedQuery.getResultList();
    }
}
