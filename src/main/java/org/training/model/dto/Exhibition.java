package org.training.model.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.training.util.Utilities.parseDateTime;
import static org.training.util.Utilities.timestampToLocalDateTime;

public class Exhibition {
    private String endStr;
    private String startStr;
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime endDate;
    private LocalDateTime startDate;
    private List<Integer> halls;

    public Exhibition(Long id, String name, Double price, Timestamp startStamp, Timestamp endStamp, List<Integer> halls) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = timestampToLocalDateTime(startStamp);
        this.endDate = timestampToLocalDateTime(endStamp);
        this.halls = halls;
    }

    public Exhibition(String name, String price, String startStr, String endStr, String[] halls) {
        this.name = name;
        this.startStr = startStr;
        this.endStr = endStr;
        this.price = Double.valueOf(price);
        this.startDate = parseDateTime(startStr);
        this.endDate = parseDateTime(endStr);
        this.halls = Arrays.stream(halls).map(Integer::valueOf).collect(Collectors.toList());
    }

    public Exhibition(long id, String name, String price, String startStr, String endStr, String[] halls) {
        this.id = id;
        this.name = name;
        this.startStr = startStr;
        this.endStr = endStr;
        this.price = Double.valueOf(price);
        this.startDate = parseDateTime(startStr);
        this.endDate = parseDateTime(endStr);
        this.halls = Arrays.stream(halls).map(Integer::valueOf).collect(Collectors.toList());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                '}';
    }

    public List<Integer> getHalls() {
        return halls;
    }

    public void setHalls(List<Integer> halls) {
        this.halls = halls;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }
}
