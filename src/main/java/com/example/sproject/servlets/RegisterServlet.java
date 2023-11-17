package com.example.sproject.servlets;

import com.example.sproject.dao.DaoUserImpl;
import com.example.sproject.entities.User;
import com.example.sproject.service.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegisterServlet extends HttpServlet {
    DaoUserImpl userDao;
    UserService userService;

    @Override
    public void init() throws ServletException {
        userDao = (DaoUserImpl) getServletContext().getAttribute("userDao");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        FreeMarkerConfig.render(req, resp, "register.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");
        User user;
        Part p = req.getPart("photo");
        if (!password.equals(confirmPassword)) {
            resp.sendError(400, "Password and password confirmation do not match");
        } else {
            if (userDao.countByParameter("email", email) != 0 || userDao.countByParameter("username", username) != 0) {
                resp.sendError(400, "Data such as email or nickname is already in use");
            } else {
                String localdir = "uploads";
                String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
                String photoPath = Helpers.savePhoto(p, pathDir, localdir);
                String hash = HashingHelper.getHash(password);
                user = new User(username, email, hash, photoPath);
                userDao.save(user);
                User current = userDao.getByEmailAndPassword(email, hash);
                userService.auth(current, req);
                resp.sendRedirect("/");
            }
        }
    }
}
