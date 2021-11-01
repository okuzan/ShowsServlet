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
        if (request.getMethod().equals("GET"))
            return "/WEB-INF/unauth/login.jsp";

        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        ResourceBundle bundle = Utilities.getBundle(request);
        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            request.setAttribute("badData", bundle.getString("login.fail"));
            return "/WEB-INF/unauth/login.jsp";
        }

        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        User user = userDao.findByUsername(name);

//        User.ROLE role = User.ROLE.UNKNOWN;
        if (user != null && BCrypt.checkpw(pass, user.getPassword())) {
            if (CommandUtility.checkUserIsLogged(request, name)) {
                System.out.println("is logged!!!");
                return "/WEB-INF/error.jsp";
            }
            System.out.println("logged in");
            System.out.println(user.getRole().name());
            CommandUtility.setUserRole(request, user.getRole(), name);
            return "redirect:/";

        } else {
            request.setAttribute("badData", bundle.getString("login.fail"));
            return "/WEB-INF/unauth/login.jsp";
        }

//        return "/WEB-INF/error.jsp";
    }
}
