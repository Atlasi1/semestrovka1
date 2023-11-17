package com.example.sproject.servlets;

import com.example.sproject.dao.DaoUserImpl;
import com.example.sproject.entities.User;
import com.example.sproject.service.HashingHelper;
import com.example.sproject.service.Helpers;
import com.example.sproject.service.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException;

@WebServlet("/changeprofile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AjaxUpdateUserDataServlet extends HttpServlet {
    DaoUserImpl userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = (DaoUserImpl) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setCharacterEncoding("UTF-8");
        String username;
        String email;
        String password;
        req.getPart("photo");


        username = req.getParameter("name");
        email = req.getParameter("email");
        password = req.getParameter("password");

        Part p = req.getPart("photo");
        String localdir = "uploads";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        String photoPath = Helpers.savePhoto(p, pathDir, localdir);

        if(user.getPhoto() != null) {
            File file = new File(user.getPhoto());
            if (file.delete()) {
                System.out.println("Предыдущий файл удален");
            } else {
                System.out.println("Не удален");
            }
        }
        String hash = HashingHelper.getHash(password);
        String[] params = new String[]{username, email, hash, photoPath};
        userDao.update(user, params);
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        userService.auth(userService.getUser(req), req);
    }

}
