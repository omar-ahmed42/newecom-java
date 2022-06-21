package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.CompletedCourseDao;
import com.omarahmed42.newecomservlets.entities.CompletedCourseEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class CompletedCourseServiceImpl implements CompletedCourseService {

    @Inject
    private CompletedCourseDao completedCourseDao;

    @Override
    public CompletedCourseEntity findCompletedCourse(Long id) {
        return completedCourseDao.findCompletedCourseById(id);
    }

    @Override
    public void addCompletedCourse(CompletedCourseEntity completedCourse) {
        completedCourseDao.addCompletedCourse(completedCourse);
    }

    @Override
    public void deleteCompletedCourse(CompletedCourseEntity completedCourse) {
        completedCourseDao.deleteCompletedCourse(completedCourse);
    }

    @Override
    public void updateGrade(CompletedCourseEntity completedCourse) {
        completedCourseDao.updateCompletedCourse(completedCourse);
    }

    @Override
    public void updateGrade(Long completedCourseId, Double grade) {
        completedCourseDao.updateGradeToCompletedCourse(completedCourseId, grade);
    }

    @Override
    public List<CompletedCourseEntity> getCompletedCoursesForStudent(StudentEntity student) {
        return completedCourseDao.getCompletedCoursesForStudent(student);

    }
}
