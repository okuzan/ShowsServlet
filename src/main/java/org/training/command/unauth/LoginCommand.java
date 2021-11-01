package org.training.command.unauth;

import org.mindrot.jbcrypt.BCrypt;
import org.training.command.Command;
import org.training.command.CommandUtility;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.UserDao;
import org.training.model.dto.User;
import org.training.util.Utilities;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if(request.getMethod().equals("GET"))
            return "/WEB-INF/unauth/login.jsp";

        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        ResourceBundle bundle = Utilities.getBundle(request);
        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            request.setAttribute("badData",bundle.getString("login.fail"));
            return "/WEB-INF/unauth/login.jsp";
        }

        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        User user = userDao.findByUsername(name);

        User.ROLE role = User.ROLE.UNKNOWN;
        if (user != null && BCrypt.checkpw(pass, user.getPassword())) {
            System.out.println("logged in");
            System.out.println(user.getRole().name());
            role = user.getRole();
        } else {
            System.out.println("not found");
        }

        if (CommandUtility.checkUserIsLogged(request, name)) {
            return "/WEB-INF/error.jsp";
        }

        if (role == User.ROLE.ADMIN) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, name);
            return "redirect:/admin";
        } else if (role == User.ROLE.USER) {
            CommandUtility.setUserRole(request, User.ROLE.USER, name);

            return "redirect:/user";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, name);
            return "/login";
        }
    }

}
