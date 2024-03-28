package dev.asjordi.controllers;

import dev.asjordi.models.Todo;
import dev.asjordi.service.IService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/todos/delete")
public class TodoDeleteServlet extends HttpServlet {

    @Inject
    private IService<Todo> service;

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
                resp.sendRedirect(req.getContextPath() + "/todos");
            } else resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Todo not found");
        } else resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid todo id");
    }
}
