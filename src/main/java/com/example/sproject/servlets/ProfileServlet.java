package com.example.sproject.servlets;

import com.example.sproject.dao.DaoCategoryImpl;
import com.example.sproject.dao.DaoUserImpl;
import com.example.sproject.entities.User;
import com.example.sproject.service.FreeMarkerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    DaoUserImpl userDao;
    DaoCategoryImpl categoryDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = (DaoUserImpl) getServletContext().getAttribute("userDao");
        categoryDao = (DaoCategoryImpl) getServletContext().getAttribute("categoryDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        root.put("user", (User) req.getSession().getAttribute("user"));
        root.put("categories", categoryDao.getAll());
        FreeMarkerConfig.render(req, resp, "profile.ftl", root);
    }
}
