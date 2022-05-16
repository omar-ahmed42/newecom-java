package com.omarahmed42.newecomservlets.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omarahmed42.newecomservlets.ejbs.AuthenticationService;
import com.omarahmed42.newecomservlets.entities.StudentEntity;
import com.omarahmed42.newecomservlets.enums.UserType;
import com.omarahmed42.newecomservlets.exceptions.UserNotFoundException;
import com.omarahmed42.newecomservlets.exceptions.UserTimeoutException;
import com.omarahmed42.newecomservlets.utils.JWT;
import com.omarahmed42.newecomservlets.utils.ResponseUtils;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SessionScoped
@Path("/login")
public class AuthenticationController implements Serializable {

    @EJB
    private AuthenticationService authenticationService;

    @POST
    @Path("/administrator/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAsAdmin(@FormParam("id") String id, @FormParam("password") String password) {
        return getLoginResponse(id, password, UserType.ADMINISTRATOR);
    }

    @POST
    @Path("/student/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAsStudent(@FormParam("id") String id, @FormParam("password") String password) {
        return getLoginResponse(id, password, UserType.STUDENT);
    }

    @POST
    @Path("/staff/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAsStaff(@FormParam("id") String id, @FormParam("password") String password) {
        return getLoginResponse(id, password, UserType.STAFF);
    }

    private Response getLoginResponse(String id, String password, UserType userType) {
        try {
            String response = login(id, password, userType);
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

    private String login(String id, String password, UserType userType) throws JsonProcessingException, UserTimeoutException {
        if (Objects.isNull(id) || id.trim().length() == 0) {
            throw new NullPointerException("ID is Null");
        }

        Object userObject = authenticationService.login(id, password, userType);
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
