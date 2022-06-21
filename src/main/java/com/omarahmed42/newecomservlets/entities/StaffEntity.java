package com.omarahmed42.newecomservlets.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "staff")
public class StaffEntity implements Serializable {
    private long staffId;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate dateOfBirth;
    private Collection<TeachesCourseEntity> teachesCoursesByStaffId;

    public StaffEntity() {
    }

    public StaffEntity(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
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
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

        StaffEntity that = (StaffEntity) o;

        if (staffId != that.staffId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (staffId ^ (staffId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "staffByStaffId", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "staff-teachesCourse")
    public Collection<TeachesCourseEntity> getTeachesCoursesByStaffId() {
        return teachesCoursesByStaffId;
    }

    public void setTeachesCoursesByStaffId(Collection<TeachesCourseEntity> teachesCoursesByStaffId) {
        this.teachesCoursesByStaffId = teachesCoursesByStaffId;
    }
}
