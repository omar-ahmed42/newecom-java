package com.omarahmed42.newecomservlets.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TeachesCourseEntityPK implements Serializable {
    private long staffId;
    private String courseCode;

    public TeachesCourseEntityPK(){}
    public TeachesCourseEntityPK(Long staffId, String courseCode) {
        this.staffId = staffId;
        this.courseCode = courseCode;
    }

    @Column(name = "staff_id")
    @Id
    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
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

        TeachesCourseEntityPK that = (TeachesCourseEntityPK) o;

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
}
