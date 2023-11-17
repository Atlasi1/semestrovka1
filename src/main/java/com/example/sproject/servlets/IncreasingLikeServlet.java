package com.example.sproject.servlets;

import com.example.sproject.dao.DaoLikeImpl;
import com.example.sproject.entities.Like;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/like")
public class IncreasingLikeServlet extends HttpServlet {
    DaoLikeImpl likeDao;

    @Override
    public void init() throws ServletException {
        likeDao = (DaoLikeImpl) getServletContext().getAttribute("likeDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long post_id = Long.parseLong(req.getParameter("post_id"));
        long user_id = Long.parseLong(req.getParameter("user_id"));
        Like like = new Like(user_id, post_id);
        likeDao.save(like);
    }
}
