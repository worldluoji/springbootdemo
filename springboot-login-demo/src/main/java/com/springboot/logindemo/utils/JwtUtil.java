package com.springboot.logindemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import excpetions.ServiceException;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

public class JwtUtil {
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_SUPPORT = "support";

    public static String generateJwt(String userId, String signingToken, boolean support, long duration) {
        if (StringUtils.isEmpty(signingToken)) {
            throw new ServiceException("Signing token can not be null...");
        }
        Algorithm algorithm = Algorithm.HMAC512(signingToken);
        String token = JWT.create()
                .withClaim(CLAIM_USER_ID, userId)
                .withClaim(CLAIM_SUPPORT, support)
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT verifyToken(String tokenString, String signingToken) {
        Algorithm algorithm = Algorithm.HMAC512(signingToken);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(tokenString);
        return jwt;
    }
}
