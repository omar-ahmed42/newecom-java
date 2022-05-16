package com.omarahmed42.newecomservlets.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prerequisite")
public class PrerequisiteEntity implements Serializable {
    private long id;
    private String courseCode;
    private String courseName;
    private AcademicYear academicYear;
    private CourseEntity courseByCourseCode;

    public PrerequisiteEntity() {
    }

    public PrerequisiteEntity(long id, String courseCode, String courseName, AcademicYear academicYear) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.academicYear = academicYear;
    }

    public PrerequisiteEntity(String courseCode, AcademicYear academicYear) {
        this.courseCode = courseCode;
        this.academicYear = academicYear;
    }

    public PrerequisiteEntity(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "course_code")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "academic_year", columnDefinition = "ENUM('FIRST','SECOND', 'THIRD', 'FOURTH')", nullable = false)
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrerequisiteEntity that = (PrerequisiteEntity) o;

        if (id != that.id) return false;
        if (courseCode != null ? !courseCode.equals(that.courseCode) : that.courseCode != null) return false;
        if (academicYear != that.academicYear) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        result = 31 * result + (academicYear != null ? academicYear.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "course_code", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "course-prerequisite")
    public CourseEntity getCourseByCourseCode() {
        return courseByCourseCode;
    }

    public void setCourseByCourseCode(CourseEntity courseByCourseCode) {
        this.courseByCourseCode = courseByCourseCode;
    }
}
