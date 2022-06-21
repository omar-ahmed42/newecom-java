package com.omarahmed42.newecomservlets.ejbs;


import com.omarahmed42.newecomservlets.dao.AdministratorDao;
import com.omarahmed42.newecomservlets.dao.StaffDao;
import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.UserType;
import com.omarahmed42.newecomservlets.exceptions.UserTimeoutException;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@LocalBean
@Stateful
@StatefulTimeout(unit = TimeUnit.DAYS, value = 2)
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private StudentDAO studentDAO;

    @Inject
    private StaffDao staffDao;

    @Inject
    private AdministratorDao administratorDao;

    private String id;
    private String password;

    private int loginAttempts;

    private LocalDateTime timeoutExpirationDate;

    @PostConstruct
    private void initializeAttributes() {
        loginAttempts = 0;
        timeoutExpirationDate = LocalDateTime.now();
    }

    @Override
//    @Remove
    public Object login(String id, String password, UserType userType) throws UserTimeoutException {
        this.id = id;
        this.password = password;

        if (LocalDateTime.now().isBefore(timeoutExpirationDate)) {
            throw new UserTimeoutException("Time out");
        }

        Object user = null;
        switch (userType) {
            case STAFF:
                user = loginAsStaff();
                break;
            case STUDENT:
                user = loginAsStudent();
                break;
            case ADMINISTRATOR:
                user = loginAsAdmin();
                break;
        }

        if (Objects.isNull(user)) {
            loginAttempts++;
            System.out.println("times: " + loginAttempts);
        }

        if (loginAttempts > 5) {
            setTimeoutExpirationDate();
        }

        if (Objects.nonNull(user)) {
            removeSession();
        }

        return user;
    }

    @Remove
    private void removeSession() {
        loginAttempts = 0;
        timeoutExpirationDate = LocalDateTime.now();
    }

    private void setTimeoutExpirationDate() {
        timeoutExpirationDate = LocalDateTime.now().plusMinutes(5 * (loginAttempts - 5));
    }

    public StudentEntity loginAsStudent() {
        try {
            StudentEntity studentByCredentials = studentDAO.findStudentByCredentials(Long.valueOf(this.id), this.password);
            return studentByCredentials;
        } catch (NoResultException noResultException) {
//            noResultException.printStackTrace();
            return null;
        }
    }

    public StaffEntity loginAsStaff() {
        try {
            StaffEntity staffByCredentials = staffDao.findStaffByCredentials(Long.valueOf(this.id), this.password);
            return staffByCredentials;
        } catch (NoResultException noResultException) {
//            noResultException.printStackTrace();
            return null;
        }
    }

    public AdministratorEntity loginAsAdmin() {
        try {
            AdministratorEntity administratorByCredentials = administratorDao.findAdministratorByCredentials(Long.valueOf(this.id), this.password);
            return administratorByCredentials;
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}
