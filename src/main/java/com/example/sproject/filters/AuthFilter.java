package com.example.sproject.filters;

import com.example.sproject.entities.User;
import com.example.sproject.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthFilter extends HttpFilter {
    UserService userService;
    private final List<String> deniedPaths = new ArrayList<>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) getServletContext().getAttribute("userService");
        deniedPaths.add("create");
    }
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String[] strings = req.getServletPath().split("/");

        User userByToken = userByToken(cookies);

        if (strings.length == 1 || !deniedPaths.contains(strings[1])) {
            if (!strings[strings.length - 1].equals("logout") && (userByToken != null || user != null)) {
                session.setAttribute("user", user == null ? userByToken : user);
            }
            chain.doFilter(req, resp);
        } else {
            if (user != null) {
                chain.doFilter(req, resp);
                return;
            }

            if (userByToken != null) {
                session.setAttribute("current", userByToken);
                chain.doFilter(req, resp);
                return;
            }

            req.getRequestDispatcher("/login");
        }
    }

    private User userByToken(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String[] split = cookie.getValue().split("_");
                    String email = split[0];
                    String hash = split[1];
                    return userService.get(email, hash);
                }
            }
        }
        return null;
    }
}
