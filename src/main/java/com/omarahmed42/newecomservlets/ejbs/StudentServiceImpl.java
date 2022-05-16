package com.omarahmed42.newecomservlets.ejbs;

import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDAO studentDAO;

    @Override
    public void addStudent(StudentEntity student) {
        studentDAO.addStudent(student);
    }

    @Override
    public void deleteStudent(StudentEntity student) {
        studentDAO.deleteStudent(student);
    }

    @Override
    public void updateStudent(StudentEntity student) {
        studentDAO.updateStudent(student);
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentDAO.findStudentById(id);
    }

    @Override
    public List<StudentEntity> findSliceOfStudents(int page, int size){
        return studentDAO.findSliceOfStudents(page, size);
    }

    @Override
    public List<StudentEntity> findAllStudents(){
        return studentDAO.findAllStudents();
    }

}
