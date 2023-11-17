package com.example.sproject.servlets;

import com.example.sproject.service.FreeMarkerConfig;
import com.example.sproject.dao.DaoUserImpl;
import com.example.sproject.entities.User;
import com.example.sproject.service.HashingHelper;
import com.example.sproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    DaoUserImpl daoUser;
    UserService userService;
    @Override
    public void init() throws ServletException {
        daoUser = (DaoUserImpl) getServletContext().getAttribute("userDao");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        FreeMarkerConfig.render(req, resp, "login.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String checkbox = Optional.ofNullable(req.getParameter("rememberMe")).orElse("off");
        if (email != null && password != null) {
            try {
                String hash = HashingHelper.getHash(password);
                User user = daoUser.getByEmailAndPassword(email, hash);
                if(user == null) {
                    req.setAttribute("message", "Wrong pair email-password");
                }
                else {
                    User current = daoUser.getByEmailAndPassword(email, hash);
                    userService.auth(user, req);
                    if (checkbox.equals("on")) {
                        userService.setCookie(email, hash, resp);
                    }
                    resp.sendRedirect("/");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
