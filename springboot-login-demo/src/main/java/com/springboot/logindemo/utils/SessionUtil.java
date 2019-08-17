package com.springboot.logindemo.utils;

import com.springboot.logindemo.constants.AuthConstants;
import com.springboot.logindemo.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class SessionUtil {
    public static final long SESSION_DURATION = TimeUnit.HOURS.toMillis(12);

    public static void loginUser(User user, HttpServletResponse response,
                                 String signingToken, String externalApex) {
        loginUser(user.getUserId(), user.isSupport(), signingToken, externalApex, response);
    }

    public static void loginUser(String userId,
                                 boolean support,
                                 String signingSecret,
                                 String externalApex,
                                 HttpServletResponse response) {
        int maxAge = (int) SESSION_DURATION / 1000;
        String token = JwtUtil.generateJwt(userId, signingSecret, support, SESSION_DURATION);
        Cookie cookie = new Cookie(AuthConstants.COOKIE_NAME, token);
        cookie.setPath("/");
        cookie.setDomain(externalApex);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static void logout(String externalApex, HttpServletResponse response) {
        Cookie cookie = new Cookie(AuthConstants.COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setDomain(externalApex);
        response.addCookie(cookie);
    }
}
