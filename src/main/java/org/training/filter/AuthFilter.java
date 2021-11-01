package org.training.filter;

import org.training.model.dto.User;
import org.training.util.Utilities;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();

        User.ROLE role = (User.ROLE) session.getAttribute("role");
        String username = (String) context.getAttribute("username");
        String path = req.getRequestURI();
        String zone;
        try {
            zone = path.split("/")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("_________");
        System.out.println(username);
        System.out.println(role);
        System.out.println(path);
        System.out.println(zone);
        boolean permitted = true;
        switch (zone) {
            case "auth":
                permitted = (username != null && !username.isEmpty());
                break;
            case "unauth":
                System.out.println(username);
                permitted = (username == null || username.isEmpty());
                break;
            case "user":
                permitted = (role == User.ROLE.USER);
                break;
            case "admin":
                permitted = (role == User.ROLE.ADMIN);
                break;
        }
        System.out.println("VERDICT: " + permitted);
        if (permitted) {
            filterChain.doFilter(request, response);
        } else {
            ResourceBundle bundle = Utilities.getBundle((HttpServletRequest) request);
            request.setAttribute("flash.access", bundle.getString("access.denied"));
            ((HttpServletResponse) response).sendError(405, bundle.getString("access.denied"));
        }
    }

    @Override
    public void destroy() {

    }
}
