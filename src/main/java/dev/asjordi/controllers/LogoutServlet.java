package dev.asjordi.controllers;

import dev.asjordi.models.User;
import dev.asjordi.service.ILoginService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject
    private ILoginService<User> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usernameOptional = service.getSessionAttribute(req, "username");

        if (usernameOptional.isPresent()) {
            var session = req.getSession();
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
