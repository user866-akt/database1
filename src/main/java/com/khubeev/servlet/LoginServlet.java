package com.khubeev.servlet;

import com.khubeev.service.UserService;
import com.khubeev.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.authenticate(login, password)) {
            var user = userService.getByLogin(login);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("userLogin", user.getLogin());
            httpSession.setAttribute("userName", user.getName());
            httpSession.setMaxInactiveInterval(60 * 60);

            Cookie cookie = new Cookie("user", user.getLogin());
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);

            resp.sendRedirect("main.jsp");
        } else {
            resp.sendRedirect("/login?error=invalid_credentials");
        }
    }
}