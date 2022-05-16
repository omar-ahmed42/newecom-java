package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.entities.StudentEntity;

import java.util.List;

public interface StudentService {
    void addStudent(StudentEntity student);
    void deleteStudent(StudentEntity student);
    void updateStudent(StudentEntity student);
    StudentEntity getStudentById(Long id);

    List<StudentEntity> findSliceOfStudents(int page, int size);

    List<StudentEntity> findAllStudents();
}
