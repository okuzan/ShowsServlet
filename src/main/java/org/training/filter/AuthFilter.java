package org.training.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

//        User.ROLE role = (User.ROLE) session.getAttribute("role");
//        if (role != null) {
//
//            String path = req.getRequestURI();
//            String zone = path.split("/")[0];
//            try {
//                User.ROLE destZone = User.ROLE.valueOf(zone);
//                if(destZone != role)
//                    res.sendRedirect("/shows");
//            } catch (IllegalArgumentException e) {
////                e.printStackTrace();
//                System.out.println("coulnd'tz");
//            }
//            System.out.println();
//            System.out.println(path);
//            if (path.contains(role.name().toLowerCase())
//                    || path.equals("/shows/") || path.equals("/shows/login.jsp") || path.equals("/shows/logout")) {
//                System.out.println("valid path!");
////                filterChain.doFilter(request, response);
//
//            } else {
//////                RequestDispatcher r d=req.getRequestDispatcher("login.jsp");
////                rd.include(req, res);
//                res.sendRedirect("/shows");
//                System.out.println("invalid path!");
////                filterChain.doFilter(request, response); //todo delete
//
//            }
////            System.out.println("loggedUsers: " + context.getAttribute("loggedUsers"));
//        } else {
////            filterChain.doFilter(request, response);
//        }
            filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
