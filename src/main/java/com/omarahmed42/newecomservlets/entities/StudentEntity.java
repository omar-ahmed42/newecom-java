package com.omarahmed42.newecomservlets.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.omarahmed42.newecomservlets.enums.AcademicYear;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentEntity implements Serializable {
    private long studentId;
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private AcademicYear academicYear;
    private Set<CompletedCourseEntity> completedCoursesByStudentId;
    private Collection<RegistersCourseEntity> registersCoursesByStudentId;

    public StudentEntity() {
    }

    public StudentEntity(long studentId, String firstName, String lastName, AcademicYear academicYear) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicYear = academicYear;
    }

    @Id
    @Column(name = "student_id")
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    @PrePersist
    public void generatePassword() {
        // It would be better to store it hashed in the database
        this.password = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (studentId != that.studentId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (academicYear != that.academicYear) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (studentId ^ (studentId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (academicYear != null ? academicYear.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "studentByStudentId", fetch = FetchType.EAGER)
    @JsonManagedReference("student-registersCourse")
    public Collection<RegistersCourseEntity> getRegistersCoursesByStudentId() {
        return registersCoursesByStudentId;
    }

    public void setRegistersCoursesByStudentId(Collection<RegistersCourseEntity> registersCoursesByStudentId) {
        this.registersCoursesByStudentId = registersCoursesByStudentId;
    }

    @OneToMany(mappedBy = "studentByStudentId", fetch = FetchType.EAGER)
    @JsonManagedReference("student-completedCourse")
    public Set<CompletedCourseEntity> getCompletedCoursesByStudentId() {
        return completedCoursesByStudentId;
    }

    public void setCompletedCoursesByStudentId(Set<CompletedCourseEntity> completedCoursesByStudentId) {
        this.completedCoursesByStudentId = completedCoursesByStudentId;
    }
}
