package com.example.sproject.service;

import com.example.sproject.dao.DaoUserImpl;
import com.example.sproject.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserService {
    DaoUserImpl userDao;

    public UserService() {
        userDao = new DaoUserImpl();
    }
    public void auth(User user, HttpServletRequest req) {
        req.getSession().setAttribute("user", user);
    }

    public void setCookie(String email, String hash, HttpServletResponse resp) {
        Cookie token = new Cookie("token", email + "_" + hash);
        token.setMaxAge(60*60*4);
        resp.addCookie(token);
    }

    public boolean hasCurrentUser(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }

    public User getUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("user");
    }

    public User get(String email, String password) {
        return userDao.getByEmailAndPassword(email, password);
    }
    public void logout(HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                cookie.setMaxAge(0);
                break;
            }
        }
    }
}
