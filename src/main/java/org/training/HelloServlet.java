package org.training;


import org.training.command.Command;
import org.training.command.admin.AddItemCommand;
import org.training.command.admin.EditItemCommand;
import org.training.command.admin.UsersCommand;
import org.training.command.all.AccountCommand;
import org.training.command.all.ExceptionCommand;
import org.training.command.all.LogOutCommand;
import org.training.command.all.ShowsCommand;
import org.training.command.unauth.LoginCommand;
import org.training.command.unauth.RegisterCommand;
import org.training.command.user.BuyCommand;
import org.training.command.user.TicketsCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HelloServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.substring(1);
//        path = path.replaceAll(".*/shows/", "");
        Command command = commands.getOrDefault(path,
                (r) -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        //request.getRequestDispatcher(page).forward(request,response);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout",
                new LogOutCommand());
        commands.put("login",
                new LoginCommand());
        commands.put("all-shows",
                new ShowsCommand());
        commands.put("add-item",
                new AddItemCommand());
        commands.put("buy",
                new BuyCommand());
        commands.put("edit-item",
                new EditItemCommand());
        commands.put("account",
                new AccountCommand());
        commands.put("tickets",
                new TicketsCommand());
        commands.put("users",
                new UsersCommand());
        commands.put("register",
                new RegisterCommand());
        commands.put("exception",
                new ExceptionCommand());
    }

}