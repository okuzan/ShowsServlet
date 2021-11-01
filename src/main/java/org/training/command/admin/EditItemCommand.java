package org.training.command.admin;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.ExhibitionDao;
import org.training.model.dto.Exhibition;
import org.training.util.Utilities;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;


public class EditItemCommand implements Command {
    private static final String PATH = "WEB-INF/admin/edit_item.jsp";
    private static final String SUCCESS_PATH = "redirect:/all-shows";

    @Override
    public String execute(HttpServletRequest request) {
        ResourceBundle bundle = Utilities.getBundle(request);
        try {
            DaoFactory factory = DaoFactory.getInstance();
            ExhibitionDao exhibitionDao = factory.createExhibitionDao();

            String idStr = request.getParameter("id");
            long id = Long.parseLong(idStr);
            if (request.getMethod().equals("DELETE")) {
                exhibitionDao.delete(id);
                request.setAttribute("flash.deleted", bundle.getString("delete.success"));
                return SUCCESS_PATH;
            }
            request.setAttribute("halls", exhibitionDao.findAllHalls());
            Exhibition show = exhibitionDao.findById(id);
            if (show == null) throw new IllegalStateException("can't fetch id");
            request.setAttribute("show", show);

            // everything needed for GET
            if (request.getMethod().equals("GET")) return PATH;

            //retrieving POST body parameters
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String[] halls = request.getParameterValues("halls");
            String startStr = request.getParameter("startDateTime");
            String endStr = request.getParameter("endDateTime");

            try {
                Double.parseDouble(price); //if invalid -> reload with warning
                Exhibition dto = new Exhibition(id, name, price, startStr, endStr, halls);
                exhibitionDao.update(dto); // main action
                request.setAttribute("flash.edited", bundle.getString("edit.success"));
                return SUCCESS_PATH; // PRG
            } catch (NumberFormatException e) {
                request.setAttribute("price", bundle.getString("validation.price"));
                return PATH;
            }
        } catch (NumberFormatException | IllegalStateException e) { // todo not found exception
            e.printStackTrace();
            request.setAttribute("flash.failed", bundle.getString("fail"));
            return SUCCESS_PATH;
        }
    }
}
