package com.omarahmed42.newecomservlets.ejbs;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.omarahmed42.newecomservlets.dao.AdministratorDao;
import com.omarahmed42.newecomservlets.dao.StaffDao;
import com.omarahmed42.newecomservlets.dao.StudentDAO;
import com.omarahmed42.newecomservlets.entities.AdministratorEntity;
import com.omarahmed42.newecomservlets.entities.StaffEntity;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.UserType;
import com.omarahmed42.newecomservlets.exceptions.UserNotFoundException;
import com.omarahmed42.newecomservlets.exceptions.UserTimeoutException;
import com.omarahmed42.newecomservlets.utils.JWT;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@LocalBean
@Stateful
@StatefulTimeout(unit = TimeUnit.DAYS, value = 2)
@Path("/bean/login")
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


    @POST
    @Path("/administrator/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAsAdminn(@FormParam("id") String id, @FormParam("password") String password) {
        return getLoginResponse(id, password, UserType.ADMINISTRATOR);
    }

    @POST
    @Path("/student/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAsStudentt(@FormParam("id") String id, @FormParam("password") String password) {
        return getLoginResponse(id, password, UserType.STUDENT);
    }

    @POST
    @Path("/staff/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAsStafff(@FormParam("id") String id, @FormParam("password") String password) {
        return getLoginResponse(id, password, UserType.STAFF);
    }

    private Response getLoginResponse(String id, String password, UserType userType) {
        try {
            String response = loginn(id, password, userType);
            return Response.ok(response).build();
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            return ResponseUtils.buildFailedRequest(400);
        } catch (UserNotFoundException userNotFoundException) {
            userNotFoundException.printStackTrace();
            return ResponseUtils.buildFailedRequest(404);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
            return ResponseUtils.buildFailedRequest(500);
        } catch (UserTimeoutException userTimeoutException) {
            userTimeoutException.printStackTrace();
            return ResponseUtils.buildFailedRequest(403);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.buildFailedRequest(500);
        }
    }

    private String loginn(String id, String password, UserType userType) throws JsonProcessingException, UserTimeoutException {
        if (Objects.isNull(id) || id.trim().length() == 0) {
            throw new NullPointerException("ID is Null");
        }

        Object userObject = login(id, password, userType);
        Map<String, Object> responseBody = new HashMap<>();

        if (Objects.isNull(userObject)) {
            responseBody.put(ResponseUtils.SUCCESS, false);
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        String token = JWT.issueJWT(id, userType);
        if (userType == UserType.STUDENT) {
            StudentEntity student = (StudentEntity) userObject;
            responseBody.put("academicYear", student.getAcademicYear());
        }
        responseBody.put("token", token);
        responseBody.put(ResponseUtils.SUCCESS, true);
        return ResponseUtils.generateJsonResponse(responseBody);
    }

}
