package org.training.command.all;

import org.training.command.Command;
import org.training.model.dao.DaoFactory;
import org.training.model.dao.ExhibitionDao;
import org.training.model.dto.Exhibition;
import org.training.util.Utilities;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowsCommand implements Command {
    private static final String PATH = "WEB-INF/shows.jsp";
    private static final String SUCCESS_PATH = "redirect:/login";

    @Override
    public String execute(HttpServletRequest request) {

        //retrieving data
        String priceMax = request.getParameter("priceMax");
        String priceMin = request.getParameter("priceMin");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String title = request.getParameter("title");

        //setting up access to db
        DaoFactory factory = DaoFactory.getInstance();
        ExhibitionDao exhibitionDao = factory.createExhibitionDao();
        List<Exhibition> shows;
        System.out.println("GJFKLJKJFGLKJKFJKD________");
        //pagination settings
        int page = 1;
        int recordsPerPage = Utilities.RECORDS_PER_PAGE;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        shows = exhibitionDao.countFiltered(title, priceMin, priceMax, start, end,
                (page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = exhibitionDao.countFiltered(title, priceMin, priceMax, start, end);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);


        //setting attrs
        request.setAttribute("pq", noOfPages);
        request.setAttribute("curPage", page);
        request.setAttribute("maxPrice", exhibitionDao.getMaxPrice());
        request.setAttribute("minPrice", exhibitionDao.getMinPrice());
        request.setAttribute("shows", shows);

        return PATH;
    }
}
