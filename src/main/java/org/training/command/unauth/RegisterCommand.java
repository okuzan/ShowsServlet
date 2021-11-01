package org.training.command.unauth;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.UserDao;
import org.training.model.dto.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;


public class RegisterCommand implements Command {
    private static final String PATH = "WEB-INF/unauth/registration.jsp";
    private static final String SUCCESS_PATH = "redirect:/login";

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equals("GET")) return PATH;
        String lang = request.getParameter("lang");
        ResourceBundle bundle = lang == null
                ? ResourceBundle.getBundle("messages")
                : ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));

        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String username = request.getParameter("username");

        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        User user = userDao.findByUsername(email);

        if (user != null)
            request.setAttribute("email", bundle.getString("validation.email"));
        else if (!password.equals(confirmPassword))
            request.setAttribute("confirmPassword", bundle.getString("validation.confirm.password"));
        else {
            userDao.save(new User(name, username, email, password));
            request.setAttribute("flash.registered",bundle.getString("registration.success"));
            return SUCCESS_PATH;
        }
        return PATH;
    }
}
