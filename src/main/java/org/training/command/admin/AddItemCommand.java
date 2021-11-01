package org.training.command.admin;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.ExhibitionDao;
import org.training.model.dto.Exhibition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;


public class AddItemCommand implements Command {
    private static final String PATH = "WEB-INF/admin/add_item.jsp";
    private static final String SUCCESS_PATH = "redirect:/all-shows";

    @Override
    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        ExhibitionDao exhibitionDao = factory.createExhibitionDao();

        if (request.getMethod().equals("GET")) {
            request.setAttribute("halls", exhibitionDao.findAllHalls());
            return PATH;
        }
        String lang = request.getParameter("lang");
        ResourceBundle bundle = lang == null
                ? ResourceBundle.getBundle("messages")
                : ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String[] halls = request.getParameterValues("halls");
        String startStr = request.getParameter("startDateTime");
        String endStr = request.getParameter("endDateTime");
        try {
            Double.parseDouble(price);
            Exhibition show = new Exhibition(name, price, startStr, endStr, halls);
            exhibitionDao.save(show);
            request.setAttribute("flash.added", bundle.getString("add.success"));
            return SUCCESS_PATH;
        } catch (NumberFormatException e) {
            request.setAttribute("price", bundle.getString("validation.price"));
        }
        request.setAttribute("halls", exhibitionDao.findAllHalls());

        System.out.println(name);
        System.out.println(price);
        System.out.println(endStr);
        System.out.println(startStr);
        System.out.println(Arrays.toString(halls));
        return PATH;
    }
}
