package dev.asjordi.controllers;

import dev.asjordi.models.User;
import dev.asjordi.service.ILoginService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private ILoginService<User> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> usernameOptional = service.getSessionAttribute(req, "username");

        if (usernameOptional.isPresent()) {
            req.setAttribute("username", usernameOptional.get());
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> usuarioOptional = service.login(username, password);

        if (usuarioOptional.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login");
        }
        else resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Username or password incorrect. Try again.");

    }
}
