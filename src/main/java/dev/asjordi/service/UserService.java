package dev.asjordi.service;

import dev.asjordi.configs.Service;
import dev.asjordi.exceptions.ServiceJDBCException;
import dev.asjordi.models.User;
import dev.asjordi.repository.ILoginRepository;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ILoginService<User> {

    @Inject
    private ILoginRepository<User> repo;

    @Override
    public Optional<String> getSessionAttribute(HttpServletRequest req, String name) {
        var session = req.getSession();
        String username = (String) session.getAttribute("username");
        return username != null ? Optional.of(username) : Optional.empty();
    }

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(
                    repo.findByUsername(username))
                    .filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return this.repo.findAll();
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<User> findAll(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(this.repo.findById(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(User u) {
        try {
            this.repo.save(u);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            this.repo.delete(id);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }
}
