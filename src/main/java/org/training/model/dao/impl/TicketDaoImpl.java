package org.training.model.dao.impl;

import org.training.model.dao.TicketDao;
import org.training.model.dto.Exhibition;
import org.training.model.dto.Ticket;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private final Connection con;

    public TicketDaoImpl(Connection connection) {
        con = connection;
    }

    public boolean save(Ticket ticket) {
        try (PreparedStatement statement = con.prepareStatement(
                "insert into exhibitions_server.public.tickets(" +
                        "user_id, exhibition_id, price,stamp) values(?,?,?,?)")) {
            statement.setLong(1, ticket.getUserId());
            statement.setLong(2, ticket.getShowId());
            statement.setDouble(3, ticket.getPrice());
            statement.setTimestamp(4, new Timestamp(new Date().getTime()));
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot save ticket!");
        }
    }

    @Override
    public void buy(Ticket ticket) {
        try {
            con.setAutoCommit(false);
            save(ticket);
            UserDaoImpl dao = new UserDaoImpl(con);
            dao.replenish(ticket.getUser().getEmail(), BigDecimal.valueOf(ticket.getPrice()).negate());
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Cannot buy!");
            }
        }
    }

    @Override
    public List<Ticket> getAllOfUser(String email) {
        try (PreparedStatement statement = con.prepareStatement(
                "select id, exhibitions.name, start_date, end_date, tickets.price, stamp, ticket_id from exhibitions_server.public.tickets " +
                        "left join exhibitions_server.public.exhibitions on exhibitions.id  = exhibition_id " +
                        "left join exhibitions_server.public.users on users.user_id  = tickets.user_id " +
                        "where users.email = ?")) {
            statement.setString(1, email);
            if (!statement.execute()) return null;
            List<Ticket> resultList = new ArrayList<>();
            try (ResultSet set = statement.getResultSet()) {
                while (set.next()) {
                    Long id = set.getLong(1);
                    String name = set.getString(2);
                    Timestamp startStamp = set.getTimestamp(3);
                    Timestamp endStamp = set.getTimestamp(4);
                    Double price = set.getDouble(5);
                    Timestamp stamp = set.getTimestamp(6);
                    Long ticketId = set.getLong(7);
                    ExhibitionDaoImpl dao = new ExhibitionDaoImpl(con);
                    List<Integer> halls = dao.findHalls(id);
                    Exhibition show = new Exhibition(id, name, price, startStamp, endStamp, halls);
                    resultList.add(new Ticket(ticketId, price, stamp, show));
                }
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get tickets!", e);
        }
    }
}
