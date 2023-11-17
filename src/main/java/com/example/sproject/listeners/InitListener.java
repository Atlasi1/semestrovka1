package com.example.sproject.listeners;

import com.example.sproject.dao.*;
import com.example.sproject.service.LikeService;
import com.example.sproject.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("postDao", new DaoPostImpl());
        sce.getServletContext().setAttribute("userDao", new DaoUserImpl());
        sce.getServletContext().setAttribute("userService", new UserService());
        sce.getServletContext().setAttribute("categoryDao", new DaoCategoryImpl());
        sce.getServletContext().setAttribute("commentDao", new DaoCommentImpl());
        sce.getServletContext().setAttribute("likeDao", new DaoLikeImpl());
        sce.getServletContext().setAttribute("likeService", new LikeService());
    }
}
