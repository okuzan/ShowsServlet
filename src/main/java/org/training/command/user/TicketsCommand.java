package org.training.command.user;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.TicketDao;
import org.training.model.dto.Ticket;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class TicketsCommand implements Command {
    private static final String PATH = "WEB-INF/user/tickets.jsp";
    private static final String SUCCESS_PATH = "redirect:/login";

    @Override
    public String execute(HttpServletRequest request) {

        //setting up access to db
        DaoFactory factory = DaoFactory.getInstance();
        TicketDao dao = factory.createTicketDao();
        String username= (String) request.getServletContext().getAttribute("username");
        List<Ticket> tickets = dao.getAllOfUser(username);

        request.setAttribute("tickets", tickets);

        return PATH;
    }
}
