package org.training.model.dao;

import org.training.model.dto.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> getAllOfUser(String email);
    boolean save(Ticket ticket);
    void buy(Ticket ticket);
}
