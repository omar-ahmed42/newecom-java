package com.omarahmed42.newecomservlets.dao;


import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class StaffStudentCourseDaoImpl implements StaffStudentCourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentEntity> getStudentsInStaffCourse(Long staffId, String courseCode){
        String queryStatement = "SELECT NEW StudentEntity(co.studentByStudentId.studentId, co.studentByStudentId.firstName, co.studentByStudentId.lastName, co.studentByStudentId.academicYear) FROM TeachesCourseEntity tc INNER JOIN CompletedCourseEntity co " +
                "ON  tc.courseCode = co.courseCode WHERE tc.courseCode = :code AND tc.staffId = :staffId";
        TypedQuery typedQuery = entityManager.createQuery(queryStatement, StudentEntity.class);
        typedQuery.setParameter("code", courseCode);
        typedQuery.setParameter("staffId", staffId);
        return typedQuery.getResultList();
    }
}
