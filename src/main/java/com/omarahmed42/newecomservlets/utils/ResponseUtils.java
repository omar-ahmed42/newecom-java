package com.omarahmed42.newecomservlets.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResponseUtils {

    public static final String SUCCESS = "success";

    public static Response buildFailedRequest(int statusCode) {
        Map<String, Boolean> map = new HashMap<>();
        map.put(SUCCESS, false);
        String response;
        try {
            response = generateJsonResponse(map);
            return Response.status(statusCode).entity(response).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response.status(500).build();
    }

    public static Response buildSuccessfulRequest(int statusCode, Object data){
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, true);
        if (Objects.nonNull(data)){
            map.put("data", data);
        }
        String response;

        try {
            response = generateJsonResponse(map);
            return Response.status(statusCode).entity(response).build();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return Response.status(500).build();
    }

    public static String generateJsonResponse(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
