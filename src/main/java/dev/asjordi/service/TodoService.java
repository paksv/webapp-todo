package dev.asjordi.service;

import dev.asjordi.configs.Service;
import dev.asjordi.exceptions.ServiceJDBCException;
import dev.asjordi.models.Todo;
import dev.asjordi.repository.IRepository;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements IService<Todo> {

    @Inject
    private IRepository<Todo> repo;

    @Override
    public List<Todo> findAll() {
        try {
            return this.repo.findAll();
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Todo> findAll(String username) {
        try {
            return this.repo.findAll(username);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Todo> findById(Integer id) {
        try {
            return Optional.ofNullable(this.repo.findById(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Todo p) {
        try {
            this.repo.save(p);
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
