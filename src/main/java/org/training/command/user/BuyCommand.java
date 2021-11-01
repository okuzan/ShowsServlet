package org.training.command.user;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.TicketDao;
import org.training.model.dao.UserDao;
import org.training.model.dao.impl.JDBCDaoFactory;
import org.training.model.dto.Ticket;
import org.training.model.dto.User;
import org.training.util.Utilities;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class BuyCommand implements Command {
    private static final String PATH = "WEB-INF/shows.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        DaoFactory factory = new JDBCDaoFactory();
        TicketDao dao = factory.createTicketDao();

        String username = (String) request.getServletContext().getAttribute("username");
        UserDao userDao = factory.createUserDao();
        User user = userDao.findByUsername(username);
        String idStr = request.getParameter("id");
        Long showId = Long.parseLong(idStr);
        String priceStr = request.getParameter("price");
        double price = Double.parseDouble(priceStr);
        ResourceBundle bundle = Utilities.getBundle(request);
        Ticket ticket = new Ticket(showId, user, price);

        if (user.getBalance().compareTo(BigDecimal.valueOf(price)) < 0) {
            return "reply:" + bundle.getString("not.enough.funds");
        } else {
            dao.buy(ticket);
            return "reply:" + bundle.getString("buy.success");
        }
    }
}
