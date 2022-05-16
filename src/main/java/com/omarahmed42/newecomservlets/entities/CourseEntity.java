package com.omarahmed42.newecomservlets.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "course")
public class CourseEntity implements Serializable {
    private String courseCode;
    private String courseName;
    private Set<CompletedCourseEntity> completedCoursesByCourseCode;
    private Set<PrerequisiteEntity> prerequisitesByCourseCode;
    private Set<RegistersCourseEntity> registersCoursesByCourseCode;
    private Set<TeachesCourseEntity> teachesCoursesByCourseCode;

    public CourseEntity(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public CourseEntity(String courseCode, String courseName, Set<PrerequisiteEntity> prerequisitesByCourseCode) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.prerequisitesByCourseCode = prerequisitesByCourseCode;
    }

    public CourseEntity() {
    }

    @Id
    @Column(name = "course_code")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Basic
    @Column(name = "course_name")
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

        CourseEntity that = (CourseEntity) o;

        if (courseCode != null ? !courseCode.equals(that.courseCode) : that.courseCode != null) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseCode != null ? courseCode.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "courseByCourseCode", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "course-completedCourse")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<CompletedCourseEntity> getCompletedCoursesByCourseCode() {
        return completedCoursesByCourseCode;
    }

    public void setCompletedCoursesByCourseCode(Set<CompletedCourseEntity> completedCoursesByCourseCode) {
        this.completedCoursesByCourseCode = completedCoursesByCourseCode;
    }

    @OneToMany(mappedBy = "courseByCourseCode", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "course-prerequisite")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<PrerequisiteEntity> getPrerequisitesByCourseCode() {
        return prerequisitesByCourseCode;
    }

    public void setPrerequisitesByCourseCode(Set<PrerequisiteEntity> prerequisitesByCourseCode) {
        this.prerequisitesByCourseCode = prerequisitesByCourseCode;
    }

    @OneToMany(mappedBy = "courseByCourseCode", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "course-registersCourse")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<RegistersCourseEntity> getRegistersCoursesByCourseCode() {
        return registersCoursesByCourseCode;
    }

    public void setRegistersCoursesByCourseCode(Set<RegistersCourseEntity> registersCoursesByCourseCode) {
        this.registersCoursesByCourseCode = registersCoursesByCourseCode;
    }

    @OneToMany(mappedBy = "courseByCourseCode", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "course-teachesCourse")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<TeachesCourseEntity> getTeachesCoursesByCourseCode() {
        return teachesCoursesByCourseCode;
    }

    public void setTeachesCoursesByCourseCode(Set<TeachesCourseEntity> teachesCoursesByCourseCode) {
        this.teachesCoursesByCourseCode = teachesCoursesByCourseCode;
    }
}
