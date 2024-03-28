package dev.asjordi.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();
    List<T> findAll(String username);
    Optional<T> findById(Integer id);
    void save(T t);
    void delete(Integer id);
}
