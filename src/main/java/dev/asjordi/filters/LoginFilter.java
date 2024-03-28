package dev.asjordi.filters;

import dev.asjordi.models.User;
import dev.asjordi.service.ILoginService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Inject
    private ILoginService<User> service;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        var o = service.getSessionAttribute(request, "username");

        if (o.isPresent()) filterChain.doFilter(servletRequest, servletResponse);
        else response.sendRedirect(request.getContextPath() + "/login");
    }
}
