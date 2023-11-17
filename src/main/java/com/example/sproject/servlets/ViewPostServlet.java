package com.example.sproject.servlets;

import com.example.sproject.dao.DaoCategoryImpl;
import com.example.sproject.dao.DaoCommentImpl;
import com.example.sproject.dao.DaoPostImpl;
import com.example.sproject.dao.DaoUserImpl;
import com.example.sproject.entities.Comment;
import com.example.sproject.entities.Post;
import com.example.sproject.entities.User;
import com.example.sproject.service.FreeMarkerConfig;
import com.example.sproject.service.Helpers;
import com.example.sproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/post")
public class ViewPostServlet extends HttpServlet {
    private DaoPostImpl postDao;
    private DaoUserImpl userDao;
    private UserService userService;
    private DaoCategoryImpl categoryDao;
    private DaoCommentImpl commentDao;

    @Override
    public void init() throws ServletException {
        super.init();
        postDao = (DaoPostImpl) getServletContext().getAttribute("postDao");
        userDao = (DaoUserImpl) getServletContext().getAttribute("userDao");
        userService = (UserService) getServletContext().getAttribute("userService");
        categoryDao = (DaoCategoryImpl) getServletContext().getAttribute("categoryDao");
        commentDao = (DaoCommentImpl) getServletContext().getAttribute("commentDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str_id = req.getParameter("post_id");
        if(str_id != null) {
            try {
                Long id = Long.parseLong(str_id);
                Post post = postDao.get(id);
                HashMap<String, Object> root = new HashMap<>();
                root.put("user", userService.getUser(req));
                root.put("post", post);
                root.put("categories", categoryDao.getAll());
                List<Post> posts = postDao.getAll();
                List<Post> listOfIntrestingPosts = posts.subList(0, posts.size());
                root.put("listOfRecentPosts", listOfIntrestingPosts);
                FreeMarkerConfig.render(req, resp, "detailPost.ftl", root);
            } catch (NumberFormatException e) {
                resp.sendError(400);
            }
        }
        else {
            resp.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User writer = userService.getUser(req);
        long post_id = Long.parseLong(req.getParameter("post_id"));
        String text = req.getParameter("text");
        Comment comment = new Comment(writer.getId(), post_id, text);
        commentDao.save(comment);
        resp.sendRedirect("/post?post_id=" + post_id);
    }
}
