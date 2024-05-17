package com.petmatchmaking.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseController {

    protected long getUserId(HttpServletRequest request) {
        long result = 0;
        String value = getCookieValue("Id", request);
        try {
            result = Long.parseLong(value);
        } catch (Exception ex) {
            result = 0;
        }
        return result;
    }

    protected String getUserName(HttpServletRequest request) {
        return getCookieValue("username", request);
    }

    protected boolean isUserLoggedIn(HttpServletRequest request) {
        String username = getCookieValue("username", request);
        return username != null && !username.isEmpty();
    }
    protected void writeCookieValue(String name, String value, HttpServletResponse response) {
        // Create a new cookie
        Cookie cookie = new Cookie(name, value);

        // Optionally, set additional cookie attributes
        cookie.setMaxAge(3600); // Cookie will expire in 1 hour (3600 seconds)
        cookie.setPath("/"); // Cookie is valid for the entire application

        // Add the cookie to the response
        response.addCookie(cookie);
    }

    protected String getCookieValue(String name, HttpServletRequest request) {
        String result = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // Iterate over the cookies to find the one you're interested in
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) { // Replace "cookieName" with the name of the cookie you want to
                                                     // read
                    result = cookie.getValue();
                    break;
                }
            }
        }
        return result;
    }
}
