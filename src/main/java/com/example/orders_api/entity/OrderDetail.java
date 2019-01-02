package com.example.orders_api.entity;

import java.util.List;

public class OrderDetail extends AbstractEntity {
    private Order order;
    private List<Line> lines;

    public OrderDetail(Order order, List<Line> lines) {
        this.order = order;
        this.lines = lines;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
