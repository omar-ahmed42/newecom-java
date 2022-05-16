package com.omarahmed42.newecomservlets.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "completed_course")
public class CompletedCourseEntity implements Serializable {
    private long id;
    private long studentId;
    private String courseCode;
    private Double grade;
    private CourseEntity courseByCourseCode;
    private StudentEntity studentByStudentId;


    public CompletedCourseEntity(long id, long studentId, String courseCode, Double grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    public CompletedCourseEntity() {
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "student_id")
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "course_code")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Basic
    @Column(name = "grade")
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompletedCourseEntity that = (CompletedCourseEntity) o;

        if (id != that.id) return false;
//        if (courseCode != null ? !courseCode.equals(that.courseCode) : that.courseCode != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "course_code", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "course-completedCourse")
    public CourseEntity getCourseByCourseCode() {
        return courseByCourseCode;
    }

    public void setCourseByCourseCode(CourseEntity courseByCourseCode) {
        this.courseByCourseCode = courseByCourseCode;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "student-completedCourse")
    public StudentEntity getStudentByStudentId() {
        return studentByStudentId;
    }

    public void setStudentByStudentId(StudentEntity studentByStudentId) {
        this.studentByStudentId = studentByStudentId;
    }
}
