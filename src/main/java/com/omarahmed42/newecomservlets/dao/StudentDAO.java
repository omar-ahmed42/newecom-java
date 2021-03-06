package com.omarahmed42.newecomservlets.dao;



import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.structures.Range;

import java.util.List;

public interface StudentDAO {
    StudentEntity findStudentById(Long id);

    List<StudentEntity> findSliceOfStudents(int page, int size);

    List<StudentEntity> findAllStudents();

    void addStudent(StudentEntity studentEntity);

    void deleteStudent(StudentEntity studentEntity);

    void updateStudent(StudentEntity studentEntity);

    StudentEntity findStudentByCredentials(Long id, String password);

    Range findMinAndMaxIdsInRange(Long min, Long max);

    Range findMinAndMaxIdsOrNextInRange(Long min, Long max);

    public Long countNumberOfIdsInRange(Long min, Long max);
}
