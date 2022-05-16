package com.omarahmed42.newecomservlets.ejbs;

import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.Local;

@Local
public interface StudentCreationService {
    void setRange(Long min, Long max);

    void addStudent(StudentEntity student);
}
