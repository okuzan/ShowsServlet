package org.training.model.dao;

import org.training.model.dto.User;

import java.math.BigDecimal;

public interface UserDao extends GenericDao<User> {
    User findByUsername(String email);
    void changeStatus(Long id, boolean status);
    void replenish(String email, BigDecimal amount);
}
