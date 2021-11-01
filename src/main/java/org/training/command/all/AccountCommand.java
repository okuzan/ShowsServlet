package org.training.command.all;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.UserDao;
import org.training.model.dto.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


public class AccountCommand implements Command {
    private static final String PATH = "WEB-INF/user/account.jsp";
    private static final String SUCCESS_PATH = "redirect:/unauth/login";

    @Override
    public String execute(HttpServletRequest request) {
        //setting up access to db
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        String username = (String) request.getServletContext().getAttribute("username");
        User user = userDao.findByUsername(username);
        if (request.getMethod().equals("POST")) {
            String balance = request.getParameter("balance");
            try {
                double dec = Double.parseDouble(balance);
                BigDecimal decimal = BigDecimal.valueOf(dec);
                userDao.replenish(username, decimal);
                return "redirect:/auth/account";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        System.out.println(request.getMethod());
        request.setAttribute("user", user);
        return PATH;
    }
}
