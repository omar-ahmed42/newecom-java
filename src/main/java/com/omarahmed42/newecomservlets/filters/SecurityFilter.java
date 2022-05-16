package com.omarahmed42.newecomservlets.filters;


import com.omarahmed42.newecomservlets.enums.UserType;
import com.omarahmed42.newecomservlets.utils.JWT;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private boolean uriPathContains(ContainerRequestContext containerRequestContext, String... paths) {
        for (String path : paths) {
            if (containerRequestContext.getUriInfo().getPath().equals(path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidHeader(List<String> header) {
        return header != null && !header.isEmpty();
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        String[] loginPaths = {"/login/administrator/", "/login/staff/", "/login/student/"};
        if (uriPathContains(containerRequestContext, loginPaths) || uriPathContains(containerRequestContext, "/all/courses")) {
            return;
        }

        List<String> authHeader = containerRequestContext.getHeaders().get(JWT.AUTHORIZATION_HEADER_KEY);
        String[] adminAuthPaths = {"/admin", "/student", "/staff", "/course/courses", "prerequisite/course/", "/teach/course",
        "/completed/course/del/", "/completed/course/complete"};
        String[] staffAuthPaths = {"/completed/course/grade", "/staff/course/students", "/teach/course/courses"};
        String[] studentAuthPaths = {"course/registration", "/eligible/courses/year/FOURTH","/eligible/courses/year/THIRD","/eligible/courses/year/SECOND","/eligible/courses/year/FIRST", "/course/registration/batch",
                "/current/courses", "/past/courses", ""};

        System.out.println("URI_PATH: " + containerRequestContext.getUriInfo().getPath());

        // TODO: Implement JWT
        boolean authorized = false;
        if (uriPathContains(containerRequestContext, studentAuthPaths) && isValidHeader(authHeader)) {
            Map<String, Object> decodedMap = JWT.verifyJWT(authHeader);
            authorized = decodedMap.get("role").equals(UserType.STUDENT.getValue());
        } else if (uriPathContains(containerRequestContext, adminAuthPaths) && isValidHeader(authHeader)) {
            Map<String, Object> decodedMap = JWT.verifyJWT(authHeader);
            authorized = decodedMap.get("role").equals(UserType.ADMINISTRATOR.getValue());
        } else if (uriPathContains(containerRequestContext, staffAuthPaths) && isValidHeader(authHeader)) {
            Map<String, Object> decodedMap = JWT.verifyJWT(authHeader);
            authorized = decodedMap.get("role").equals(UserType.STAFF.getValue());
        }

        if (authorized) {
            return;
        }

        Response unauthorizedResponse = Response.status(Response.Status.UNAUTHORIZED).build();
        containerRequestContext.abortWith(unauthorizedResponse);
    }


}
