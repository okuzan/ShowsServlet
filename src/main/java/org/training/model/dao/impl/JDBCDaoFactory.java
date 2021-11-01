package org.training.model.dao.impl;

import org.training.model.dao.DaoFactory;
import org.training.model.dao.ExhibitionDao;
import org.training.model.dao.TicketDao;
import org.training.model.dao.UserDao;
import org.training.model.dao.impl.ExhibitionDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/exhibitions_server",
                    "postgres",
                    "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExhibitionDao createExhibitionDao() {
        return new ExhibitionDaoImpl(getConnection());
    }

    @Override
    public TicketDao createTicketDao() {
        return new TicketDaoImpl(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }
}
