package org.training.model.dto;

import org.training.util.Utilities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ticket {
    private Long userId;
    private Long showId;
    private Long id;
    private Exhibition show;
    private User user;
    private Double price;
    private LocalDateTime stamp;

    public Ticket(Long id, Exhibition show, User user, Double price, LocalDateTime stamp) {
        this.id = id;
        this.show = show;
        this.user = user;
        this.price = price;
        this.stamp = stamp;
    }

    public Ticket(Long showId, User user, Double price) {
        this.showId = showId;
        this.user = user;
        this.userId = user.getId();
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Ticket(Long id, Double price, Timestamp stamp, Exhibition show) {
        this.id = id;
        this.show = show;
        this.price = price;
        this.stamp = Utilities.timestampToLocalDateTime(stamp);
    }

    public Ticket(Long showId, Long userId, Double price) {
        this.userId = userId;
        this.showId = showId;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exhibition getShow() {
        return show;
    }

    public void setShow(Exhibition show) {
        this.show = show;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getStamp() {
        return stamp;
    }

    public void setStamp(LocalDateTime stamp) {
        this.stamp = stamp;
    }
}
