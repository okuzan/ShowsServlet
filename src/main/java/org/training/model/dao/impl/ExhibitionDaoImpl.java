package org.training.model.dao.impl;

import org.apache.commons.codec.binary.Base64;
import org.training.model.dao.ExhibitionDao;
import org.training.model.dto.Exhibition;
import org.training.util.Utilities;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionDaoImpl implements ExhibitionDao {
    private final Connection con;

    public ExhibitionDaoImpl(Connection connection) {
        this.con = connection;
    }

    @Override
    public Exhibition save(Exhibition entity) {
        try (PreparedStatement statement =
                     con.prepareStatement("insert into exhibitions_server.public.exhibitions(" +
                             "name, price, start_date, end_date) values(?,?,?,?) returning id")) {
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getPrice());
            statement.setTimestamp(3, Utilities.ldtToStamp(entity.getStartDate()));
            statement.setTimestamp(4, Utilities.ldtToStamp(entity.getEndDate()));
            try (ResultSet set = statement.executeQuery()) {
                set.next();
                entity.setId(set.getLong(1));
                System.out.println(entity.getId());
                for (Integer hall : entity.getHalls())
                    insertHall(getIdByNumber(hall), entity.getId());
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Long getIdByNumber(Integer hall) {
        try (PreparedStatement statement = con.prepareStatement(
                "select id from exhibitions_server.public.halls where number = ?")) {
            statement.setInt(1, hall);
            if (!statement.execute()) return null;
            try (ResultSet set = statement.getResultSet()) {
                set.next();
                return set.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get id by number", e);
        }
    }

    private boolean insertHall(Long hall, Long id) {
        try (PreparedStatement statement =
                     con.prepareStatement("insert into exhibitions_server.public.exhibitions_halls(" +
                             "id, hall_id) values(?,?)")) {
            statement.setLong(1, id);
            statement.setLong(2, hall);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Exhibition findById(long idParam) {
        try (PreparedStatement statement = con.prepareStatement(
                "select id, name, price, end_date, start_date from exhibitions_server.public.exhibitions where id = ?")) {
            statement.setLong(1, idParam);
            if (!statement.execute()) return null;
            try (ResultSet set = statement.getResultSet()) {
                if (set.next()) {
                    return getExhibitionFromSet(set);
                } else return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find show", e);
        }
    }


    @Override
    public List<Exhibition> findAll() {
        try (PreparedStatement statement = con.prepareStatement(
                "select id, name, price, end_date, start_date from exhibitions_server.public.exhibitions")) {
            if (!statement.execute()) return null;
            try (ResultSet set = statement.getResultSet()) {
                return getExhibitions(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find user", e);
        }

    }

    public List<Integer> findHalls(Long id) {
        try (PreparedStatement statement = con.prepareStatement(
                "select number from exhibitions_server.public.exhibitions_halls " +
                        "left join exhibitions_server.public.halls on exhibitions_halls.hall_id  = halls.id " +
                        "where exhibitions_halls.id = ?" )) {
            statement.setLong(1, id);
            return getIntegers(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Can't find halls", e);
        }
    }

    public boolean deleteHalls(Long id) {
        try (PreparedStatement statement = con.prepareStatement("delete from exhibitions_server.public.exhibitions_halls where id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Can't find halls", e);
        }
    }

    public List<Integer> findAllHalls() {
        try (PreparedStatement statement = con.prepareStatement(
                "select number from exhibitions_server.public.halls")) {
            return getIntegers(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Can't find halls", e);
        }
    }

    private List<Integer> getIntegers(PreparedStatement statement) throws SQLException {
        if (!statement.execute()) return null;
        try (ResultSet set = statement.getResultSet()) {
            List<Integer> resultList = new ArrayList<>();
            while (set.next()) resultList.add(set.getInt(1));
            return resultList;
        }
    }

    public List<Exhibition> countFiltered(String nameStr,
                                          String priceMin, String priceMax,
                                          String startDate, String endDate,
                                          int offset, int noRecords) {
        try (Statement statement = con.createStatement()) {
            String query = "select id, name, price, start_date, end_date from exhibitions_server.public.exhibitions where 1=1 ";
            query = getString(nameStr, priceMin, priceMax, startDate, endDate, query);
            query += "limit " + noRecords + " offset " + offset;
            System.out.println(query);
            try (ResultSet set = statement.executeQuery(query)) {
                return getExhibitions(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get shows", e);
        }
    }

    public int countFiltered(String nameStr,
                             String priceMin, String priceMax,
                             String startDate, String endDate
    ) {
        try (Statement statement = con.createStatement()) {
            String query = "select count(*) from exhibitions_server.public.exhibitions where 1=1 ";
            query = getString(nameStr, priceMin, priceMax, startDate, endDate, query);
            System.out.println(query);
            try (ResultSet set = statement.executeQuery(query)) {
                set.next();
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get shows", e);
        }
    }

    private String getString(String nameStr, String priceMin, String priceMax, String startDate, String endDate, String query) {
        if (nameStr != null && !nameStr.isEmpty())
            query += " and name ilike '%" + nameStr + "%' ";
        if (priceMax != null && !priceMax.isEmpty())
            query += " and price <= " + priceMax + " ";
        if (priceMin != null && !priceMin.isEmpty())
            query += " and price >= " + priceMin + " ";
        if (startDate != null && !startDate.isEmpty()) {
            Timestamp startLdt = Utilities.stringToStamp(new String(Base64.decodeBase64(startDate.getBytes(StandardCharsets.UTF_8))));
            query += " and start_date >= '" + startLdt + "' ";
        }
        if (endDate != null && !endDate.isEmpty()) {
            Timestamp endLdt = Utilities.stringToStamp(new String(Base64.decodeBase64(endDate.getBytes(StandardCharsets.UTF_8))));
            query += "and end_date <= '" + endLdt + "' ";
        }
        return query;
    }

    private List<Exhibition> getExhibitions(ResultSet set) throws SQLException {
        List<Exhibition> resultList = new ArrayList<>();
        while (set.next())
            resultList.add(getExhibitionFromSet(set));

        return resultList;
    }

    public Exhibition getExhibitionFromSet(ResultSet set) throws SQLException {
        Long id = set.getLong(1);
        String name = set.getString(2);
        Double price = set.getDouble(3);
        Timestamp startStamp = set.getTimestamp(4);
        Timestamp endStamp = set.getTimestamp(5);
        List<Integer> halls = findHalls(id);
        return new Exhibition(id, name, price, startStamp, endStamp, halls);
    }

    public List<Exhibition> findAll(int offset, int noRecords) {
        try (PreparedStatement statement = con.prepareStatement(
                "select id, name, price, start_date, end_date " +
                        "from exhibitions_server.public.exhibitions " +
                        "limit " + noRecords + " offset " + offset)) {
            if (!statement.execute()) return null;
            try (ResultSet set = statement.getResultSet()) {
                return getExhibitions(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find show", e);
        }

    }

    public double getMaxPrice() {
        try (PreparedStatement statement = con.prepareStatement(
                "select max(price) as max_price from exhibitions_server.public.exhibitions");
             ResultSet set = statement.executeQuery();
        ) {

            set.next();
            return set.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find show", e);
        }
    }

    public double getMinPrice() {
        try (PreparedStatement statement = con.prepareStatement(
                "select min(price) as min_price from exhibitions_server.public.exhibitions");
             ResultSet set = statement.executeQuery()) {
            set.next();
            return set.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find show", e);
        }
    }

    @Override
    public void update(Exhibition entity) {
        try (PreparedStatement statement =
                     con.prepareStatement("update  exhibitions_server.public.exhibitions " +
                             " set name = ?, price = ?, end_date = ?, start_date = ? where  id = ?")) {
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getPrice());
            statement.setTimestamp(3, Utilities.ldtToStamp(entity.getStartDate()));
            statement.setTimestamp(4, Utilities.ldtToStamp(entity.getEndDate()));
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
            deleteHalls(entity.getId());
            for (Integer hall : entity.getHalls())
                insertHall(getIdByNumber(hall), entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement statement =
                     con.prepareStatement("delete from exhibitions_server.public.exhibitions" +
                             " where id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("contraint");
        }
    }

    @Override
    public void close() {

    }
}
