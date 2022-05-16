package com.omarahmed42.newecomservlets.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teaches_course")
@IdClass(TeachesCourseEntityPK.class)
public class TeachesCourseEntity implements Serializable {
    private long staffId;
    private String courseCode;
    private StaffEntity staffByStaffId;
    private CourseEntity courseByCourseCode;

    @Id
    @Column(name = "staff_id")
    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    @Id
    @Column(name = "course_code")
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

        TeachesCourseEntity that = (TeachesCourseEntity) o;

        if (staffId != that.staffId) return false;
        if (courseCode != null ? !courseCode.equals(that.courseCode) : that.courseCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (staffId ^ (staffId >>> 32));
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "staff-teachesCourse")
    public StaffEntity getStaffByStaffId() {
        return staffByStaffId;
    }

    public void setStaffByStaffId(StaffEntity staffByStaffId) {
        this.staffByStaffId = staffByStaffId;
    }

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "course_code", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "course-teachesCourse")
    public CourseEntity getCourseByCourseCode() {
        return courseByCourseCode;
    }

    public void setCourseByCourseCode(CourseEntity courseByCourseCode) {
        this.courseByCourseCode = courseByCourseCode;
    }
}
