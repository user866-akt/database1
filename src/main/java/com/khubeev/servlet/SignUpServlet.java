package com.khubeev.servlet;

import com.khubeev.entity.User;
import com.khubeev.service.UserService;
import com.khubeev.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("sign_up.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setLogin(login);
        user.setPassword(password);

        try {
            userService.registerUser(user);
            resp.sendRedirect("login.html?registration=success");
        } catch (Exception e) {
            resp.sendRedirect("sign_up.html?error=" + e.getMessage());
        }
    }
}