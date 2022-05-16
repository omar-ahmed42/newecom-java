package com.omarahmed42.newecomservlets.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registers_course")
@IdClass(RegistersCourseEntityPK.class)
public class RegistersCourseEntity implements Serializable {
    private long studentId;
    private String courseCode;
    private StudentEntity studentByStudentId;
    private CourseEntity courseByCourseCode;

    public RegistersCourseEntity(long studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public RegistersCourseEntity() {
    }

    @Id
    @Column(name = "student_id")
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistersCourseEntity that = (RegistersCourseEntity) o;

        if (studentId != that.studentId) return false;
        if (courseCode != null ? !courseCode.equals(that.courseCode) : that.courseCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (studentId ^ (studentId >>> 32));
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "student-registersCourse")
    public StudentEntity getStudentByStudentId() {
        return studentByStudentId;
    }

    public void setStudentByStudentId(StudentEntity studentByStudentId) {
        this.studentByStudentId = studentByStudentId;
    }

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "course_code", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "course-registersCourse")
    public CourseEntity getCourseByCourseCode() {
        return courseByCourseCode;
    }

    public void setCourseByCourseCode(CourseEntity courseByCourseCode) {
        this.courseByCourseCode = courseByCourseCode;
    }
}
