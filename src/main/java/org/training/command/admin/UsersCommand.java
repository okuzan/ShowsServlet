package org.training.command.admin;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.UserDao;
import org.training.model.dto.User;
import org.training.util.Utilities;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


public class UsersCommand implements Command {
    private static final String PATH = "WEB-INF/admin/users.jsp";
    private static final String SUCCESS_PATH = "redirect:/login";

    @Override
    public String execute(HttpServletRequest request) {
        //setting up access to db
        DaoFactory factory = DaoFactory.getInstance();
        UserDao userDao = factory.createUserDao();
        List<User> users;
        if (request.getMethod().equals("POST")) {
            System.out.println("post");
            String id = request.getParameter("id");
            String enabled = request.getParameter("checked");
            System.out.println(id);
            System.out.println(enabled);
            try {
                Long i = Long.parseLong(id);
                boolean enabl = Boolean.parseBoolean(enabled);
                userDao.changeStatus(i, enabl);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        //pagination settings
        int page = 1;
        int recordsPerPage = Utilities.RECORDS_PER_PAGE;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        users = userDao.findAll((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = userDao.findAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        System.out.println(Arrays.toString(users.toArray()));
        System.out.println(noOfPages);

        //setting attrs
        request.setAttribute("pq", noOfPages);
        request.setAttribute("curPage", page);

        request.setAttribute("users", users);



        return PATH;
    }
}
