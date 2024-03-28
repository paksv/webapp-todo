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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users/add")
public class UserFormServlet extends HttpServlet {

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

        User user = new User();
        if (id > 0) {
            var o = service.findById(id);
            user = o.isPresent() ? o.get() : user;
        }

        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/add-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (firstName == null || firstName.trim().isBlank()) errors.put("firstName", "First name is required");
        if (lastName == null || lastName.trim().isBlank()) errors.put("lastName", "Last name is required");
        if (username == null || username.trim().isBlank()) errors.put("username", "Username is required");
        if (password == null || password.trim().isBlank()) errors.put("password", "Password is required");

        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);

        if (errors.isEmpty()) {
            service.save(user);
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/add-user.jsp").forward(req, resp);
        }

    }
}
