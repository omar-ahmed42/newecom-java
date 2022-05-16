package com.omarahmed42.newecomservlets.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RegistersCourseEntityPK implements Serializable {
    private long studentId;
    private String courseCode;

    @Column(name = "student_id")
    @Id
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Column(name = "course_code")
    @Id
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

        RegistersCourseEntityPK that = (RegistersCourseEntityPK) o;

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
}
