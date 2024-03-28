package dev.asjordi.repository;

import dev.asjordi.configs.MysqlConnection;
import dev.asjordi.models.Todo;
import dev.asjordi.models.User;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TodoRepository implements IRepository<Todo> {

    @Inject
    @MysqlConnection
    private Connection conn;

    @Override
    public List<Todo> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Todo> findAll(String username) throws SQLException {
        List<Todo> todos = new LinkedList<>();

        try (var ps = conn.prepareStatement("SELECT t.* FROM todos AS t INNER JOIN users AS u ON t.user_id = u.id WHERE u.username = ?;")) {
            ps.setString(1, username);

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    todos.add(createTodo(rs));
                }
            }
        }

        return todos;
    }

    @Override
    public Todo findById(Integer id) throws SQLException {
        Todo todo = null;

        try (var ps = conn.prepareStatement("SELECT * FROM todos WHERE id = ?;")) {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) todo = createTodo(rs);
            }
        }

        return todo;
    }

    @Override
    public void save(Todo todo) throws SQLException {
        String sql = "";
        boolean isUpdate = todo.getId() != null && todo.getId() > 0;

        if (isUpdate) sql = "UPDATE todos SET title = ?, description = ?, date = ?, is_done = ?, user_id = ? WHERE id = ?;";
        else sql = "INSERT INTO todos (title, description, date, is_done, user_id) VALUES (?, ?, ?, ?, ?);";

        try (var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, todo.getTitle());
            ps.setString(2, todo.getDescription());
            ps.setDate(3, Date.valueOf(todo.getDate()));
            ps.setBoolean(4, todo.getIsDone());
            ps.setInt(5, todo.getUser().getId());

            if (isUpdate) ps.setInt(6, todo.getId());

            ps.executeUpdate();

            if (todo.getId() == null) {
                try (var rs = ps.getGeneratedKeys()) {
                    if (rs.next()) todo.setId(rs.getInt(1));
                }
            }
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (var ps = conn.prepareStatement("DELETE FROM todos WHERE id = ?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Todo createTodo(ResultSet rs) throws SQLException {
        Todo t = new Todo();
        t.setId(rs.getInt("id"));
        t.setTitle(rs.getString("title"));
        t.setDescription(rs.getString("description"));
        t.setDate(rs.getDate("date").toLocalDate());
        t.setIsDone(rs.getBoolean("is_done"));
        User u = new User();
        u.setId(rs.getInt("user_id"));
        t.setUser(u);
        return t;
    }
}
