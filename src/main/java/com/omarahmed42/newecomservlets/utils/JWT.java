package com.omarahmed42.newecomservlets.utils;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.omarahmed42.newecomservlets.enums.UserType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWT {

    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    public static Map<String, Object> verifyJWT(List<String> authHeader) {
        String authToken = authHeader.get(0);
        authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_BEARER, "");
        Algorithm algorithm = Algorithm.HMAC256("SECRET");
        JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(authToken);

        String id = decodedJWT.getSubject();
        int role = decodedJWT.getClaim("role").asInt();

        Map<String, Object> decodedTokenMap = new HashMap<>();
        decodedTokenMap.put("id", id);
        decodedTokenMap.put("role", role);
        return decodedTokenMap;
    }

    public static String issueJWT(String id, UserType userType) {
        Algorithm algorithm = Algorithm.HMAC256("SECRET");
        return com.auth0.jwt.JWT.create()
                .withSubject(id)
                .withClaim("role", userType.getValue())
                .sign(algorithm);
    }
}
