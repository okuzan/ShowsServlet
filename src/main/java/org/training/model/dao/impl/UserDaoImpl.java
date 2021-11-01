package org.training.model.dao.impl;

import org.training.model.dao.UserDao;
import org.training.model.dto.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Connection con;

    public UserDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public User save(User dto) {
        try (PreparedStatement statement = con.prepareStatement(
                "insert into users(name,username,email,password) values(?,?,?,?) returning user_id")
        ) {
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getUsername());
            statement.setString(3, dto.getEmail());
            statement.setString(4, User.hash(dto.getPassword()));
            try (ResultSet set = statement.executeQuery()) {
                set.next();
                dto.setId(set.getLong(1));
                return dto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't insert user", e);
        }

    }

    @Override
    public User findByUsername(String emailNeeded) {
        try (PreparedStatement statement = con.prepareStatement(
                "select user_id,enabled,password,username,email,users.name,balance,roles.name from users left join roles on users.role_id = roles.id where email = ?")) {
            statement.setString(1, emailNeeded);
            if (!statement.execute()) return null;
            try (ResultSet set = statement.getResultSet()) {
                if (set.next()) {
                    return getUserFromSet(set);
                } else return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find user", e);
        }
    }

    @Override
    public User findById(long id) {
        try (PreparedStatement statement = con.prepareStatement(
                "select user_id,enabled,password,username,email,users.name,balance,roles.name from users left join roles on users.role_id = roles.id where users.user_id = ?")) {
            statement.setLong(1, id);
            if (!statement.execute()) return null;
            try (ResultSet set = statement.getResultSet()) {
                if (set.next()) return getUserFromSet(set);
                else return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find user", e);
        }

    }

    public static User getUserFromSet(ResultSet set) throws SQLException {
        Long userId = set.getLong(1);
        boolean enabled = set.getBoolean(2);
        String password = set.getString(3);
        String username = set.getString(4);
        String email = set.getString(5);
        String name = set.getString(6);
        BigDecimal balance = set.getBigDecimal(7);
        String roleStr = set.getString(8);
        System.out.println(roleStr);
        return new User(userId, enabled, password, username, email, name, balance, roleStr);

    }

    @Override
    public List<User> findAll() {

        try (PreparedStatement statement = con.prepareStatement(
                "select user_id,enabled,password,username,email,users.name,balance,roles.name " +
                        "from users left join roles on users.role_id = roles.id ")) {
            return getUsers(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find users", e);
        }
    }

    @Override
    public List<User> findAll(int offset, int qRecords) {
        try (PreparedStatement statement = con.prepareStatement(
                "select user_id,enabled,password,username,email,users.name,balance,roles.name " +
                        "from users left join roles on users.role_id = roles.id limit ? offset ?")) {
            statement.setInt(1, qRecords);
            statement.setInt(2, offset);
            return getUsers(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find users", e);
        }
    }

    private List<User> getUsers(PreparedStatement statement) throws SQLException {
        if (!statement.execute()) return null;
        List<User> resultList = new ArrayList<>();
        try (ResultSet set = statement.getResultSet()) {
            while (set.next()) resultList.add(getUserFromSet(set));
        }
        return resultList;
    }

    public void changeStatus(Long id, boolean enabled) {
        try (PreparedStatement statement =
                     con.prepareStatement("update  exhibitions_server.public.users " +
                             " set enabled = ? where  user_id = ?")) {
            statement.setBoolean(1, enabled);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void replenish(String email, BigDecimal amount) {
        try (PreparedStatement statement =
                     con.prepareStatement("update  exhibitions_server.public.users " +
                             " set balance = balance + ? where  email = ?")) {
            statement.setBigDecimal(1, amount);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        return;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
