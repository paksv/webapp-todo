package dev.asjordi.repository;

import dev.asjordi.configs.MysqlConnection;
import dev.asjordi.models.User;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements ILoginRepository<User> {

    @Inject
    @MysqlConnection
    private Connection conn;

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new LinkedList<>();

        try (var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM users;")) {
            while (rs.next()) {
                users.add(createUser(rs));
            }
        }

        return users;
    }

    @Override
    public List<User> findAll(Integer id) throws SQLException {
        return null;
    }

    @Override
    public User findById(Integer id) throws SQLException {
        User user = null;

        try (var ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?;")) {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) user = createUser(rs);
            }
        }

        return user;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = "";
        boolean isUpdate = user.getId() != null && user.getId() > 0;

        if (isUpdate) sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ? WHERE id = ?;";
        else sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?);";

        try (var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());

            if (isUpdate) ps.setInt(5, user.getId());

            ps.executeUpdate();

            if (user.getId() == null) {
                try (var rs = ps.getGeneratedKeys()) {
                    if (rs.next()) user.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (var ps = conn.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    @Override
    public User findByUsername(String username) throws SQLException {
        User user = null;

        try (var ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?;")) {
            ps.setString(1, username);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) user = createUser(rs);
            }
        }

        return user;
    }

    private User createUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        return u;
    }

}
