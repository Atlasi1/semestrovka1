package com.example.sproject.servlets;

import com.example.sproject.entities.User;
import com.example.sproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userService.hasCurrentUser(req)) {
            userService.logout(req);
            resp.sendRedirect("/");
        }
        else {
            resp.setContentType("text/html");
            resp.setStatus(401);
            resp.getWriter().println("Вы не авторизованы.");
        }
    }
}
