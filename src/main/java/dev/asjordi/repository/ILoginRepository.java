package dev.asjordi.repository;

import java.sql.SQLException;

public interface ILoginRepository<T> extends IRepository<T> {
    T findByUsername(String username) throws SQLException;
}
