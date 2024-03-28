package dev.asjordi.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface ILoginService<T> extends IService<T> {
    Optional<String> getSessionAttribute(HttpServletRequest req, String name);
    Optional<T> login(String username, String password);
}
