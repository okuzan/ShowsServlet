package org.training.model.dao;

import org.training.model.dto.Exhibition;

import java.util.List;

public interface ExhibitionDao extends GenericDao<Exhibition> {
    double getMaxPrice();
    double getMinPrice();
    List<Exhibition> countFiltered(String title, String priceMin, String priceMax, String start, String end, int offset, int noRecords);
    int countFiltered(String title, String priceMin, String priceMax, String start, String end);
    List<Integer> findHalls(Long id);
    List<Integer> findAllHalls();

    void update(Exhibition entity);

    boolean delete(long id);
}
