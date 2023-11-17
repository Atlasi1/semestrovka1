package com.example.sproject.servlets;

import com.example.sproject.dao.DaoCategoryImpl;
import com.example.sproject.dao.DaoPostImpl;
import com.example.sproject.entities.Category;
import com.example.sproject.service.FreeMarkerConfig;
import com.example.sproject.entities.User;
import com.example.sproject.entities.Post;
import com.example.sproject.service.LikeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@WebServlet("")
public class PostListServlet extends HttpServlet {
    private DaoPostImpl postDao;
    private DaoCategoryImpl categoryDao;
    private LikeService likeService;

    public void init() throws ServletException {
        super.init();
        FreeMarkerConfig.setServletContext(this.getServletContext());
        postDao = (DaoPostImpl) getServletContext().getAttribute("postDao");
        categoryDao = (DaoCategoryImpl) getServletContext().getAttribute("categoryDao");
        likeService = (LikeService) getServletContext().getAttribute("likeService");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(Optional.ofNullable(req.getParameter("currentPage")).orElse("1"));
        int recordsPerPage = Integer.parseInt(Optional.ofNullable(req.getParameter("recordsPerPage")).orElse("10"));
        String cat = req.getParameter("category_id");
        Integer cat_id;
        int rows;
        List<Post> posts;
        List<Post> listOfIntrestingPosts;
        List<Post> listOfRecentPosts;
        if(cat != null && isNumeric(cat)) {
            cat_id = Integer.parseInt(cat);
            posts = postDao.getPostsByLimitWithCategory(currentPage, recordsPerPage, cat_id);
            rows = postDao.getCountOfRowsWithCategory(cat_id);
            listOfIntrestingPosts = postDao.getIntrestingPostsWithCategory(cat_id);
            listOfRecentPosts = postDao.getRecentPostsWithCategory(cat_id);
        }
        else {
            posts = postDao.getPostsByLimit(currentPage, recordsPerPage);
            rows = postDao.getCountOfRows();
            listOfIntrestingPosts = postDao.getIntrestingPosts();
            listOfRecentPosts = postDao.getRecentPosts();
        }

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }

        List<Category> categories = categoryDao.getAll();

        HashMap<String, Object> root = new HashMap<>();
        root.put("posts", posts);
        root.put("categories", categories);
        root.put("user", (User) req.getSession().getAttribute("user"));
        root.put("noOfPages", nOfPages);
        root.put("currentPage", currentPage);
        root.put("recordsPerPage", recordsPerPage);
        root.put("likeService", likeService);
        root.put("countOfIntrestingPosts", listOfIntrestingPosts.size());
        root.put("listOfIntrestingPosts", listOfIntrestingPosts);
        root.put("listOfRecentPosts", listOfRecentPosts);
        FreeMarkerConfig.render(req, resp, "listPost.ftl", root);
    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
