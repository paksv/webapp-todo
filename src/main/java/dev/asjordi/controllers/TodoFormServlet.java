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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/todos/add")
public class TodoFormServlet extends HttpServlet {

    @Inject
    private IService<Todo> tService;
    @Inject
    private ILoginService<User> uService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = uService.findByUsername(uService.getSessionAttribute(req, "username").get()).get();

        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Todo todo = new Todo();
        if (id > 0) {
            var o = tService.findById(id);
            todo = o.isPresent() ? o.get() : todo;
        }

        req.setAttribute("todo", todo);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/add-todo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String dateStr = req.getParameter("date");
        boolean status = req.getParameter("status").equals("1") ? true : false;

        if (title == null || title.trim().isBlank()) errors.put("title", "Title is required");
        if (description == null || description.trim().isBlank()) errors.put("description", "Description is required");
        if (dateStr == null || dateStr.trim().isBlank()) errors.put("date", "Date is required");

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            date = null;
        }

        Integer id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Integer idUser;
        try {
            idUser = Integer.valueOf(req.getParameter("id_user"));
        } catch (NumberFormatException e) {
            idUser = 0;
        }

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDate(date);
        todo.setIsDone(status);
        User user = new User();
        user.setId(idUser);
        todo.setUser(user);

        if (errors.isEmpty()) {
            tService.save(todo);
            resp.sendRedirect(req.getContextPath() + "/todos");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("todo", todo);
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/add-todo.jsp").forward(req, resp);
        }

    }
}
