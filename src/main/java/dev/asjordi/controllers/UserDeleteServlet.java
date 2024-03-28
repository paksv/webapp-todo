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

@WebServlet("/users/delete")
public class UserDeleteServlet extends HttpServlet {

    @Inject
    private ILoginService<User> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        if (id > 0) {
            var o = service.findById(id);
            if (o.isPresent()) {
                service.delete(id);
                resp.sendRedirect(req.getContextPath() + "/users");
            } else resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        } else resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user id");
    }
}
