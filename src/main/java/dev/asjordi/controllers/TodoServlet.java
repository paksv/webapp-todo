package dev.asjordi.controllers;

import dev.asjordi.models.Todo;
import dev.asjordi.models.User;
import dev.asjordi.service.ILoginService;
import dev.asjordi.service.IService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/todos.html", "/todos"})
public class TodoServlet extends HttpServlet {

    @Inject
    private ILoginService<User> uService;
    @Inject
    private IService<Todo> tService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userOptional = uService.getSessionAttribute(req, "username");
        var todos = tService.findAll(userOptional.isPresent() ? userOptional.get() : "");

        req.setAttribute("username", userOptional);
        req.setAttribute("todos", todos);
        getServletContext().getRequestDispatcher("/todos.jsp").forward(req, resp);
    }
}
