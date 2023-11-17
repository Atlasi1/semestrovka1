package com.example.sproject.servlets;

import com.example.sproject.dao.DaoCategoryImpl;
import com.example.sproject.dao.DaoPostImpl;
import com.example.sproject.entities.Post;
import com.example.sproject.entities.User;
import com.example.sproject.service.FreeMarkerConfig;
import com.example.sproject.service.Helpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

@WebServlet("/create")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class CreatePostServlet extends HttpServlet {
    DaoPostImpl postDao;
    DaoCategoryImpl categoryDao;



    @Override
    public void init() throws ServletException {
        super.init();
        postDao = (DaoPostImpl) getServletContext().getAttribute("postDao");
        categoryDao = (DaoCategoryImpl) getServletContext().getAttribute("categoryDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        root.put("user", (User) req.getSession().getAttribute("user"));
        root.put("categories", categoryDao.getAll());
        FreeMarkerConfig.render(req, resp, "createPost.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Post post;

        String title = req.getParameter("title");

        Part p = req.getPart("photo");

        String content = req.getParameter("content");

        String category = req.getParameter("category");

        User user = (User) req.getSession().getAttribute("user");

        String localdir = "uploads";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        String photoPath = Helpers.savePhoto(p, pathDir, localdir);

        long now = System.currentTimeMillis();
        post = new Post(title, content, new Date(now), photoPath, user.getId(), category);

        postDao.save(post);
        resp.sendRedirect("/");
    }
}
