package com.example.orders_api.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="lines")
public class Line extends AbstractEntity implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="linenumber")
    private Integer lineNumber;

    @NotNull
    @Column(name="sku")
    private String sku;

    @NotNull
    @Column(name="orderid")
    private Integer orderId;

    public Line() {}

    public Line(Integer lineNumber, String sku, Integer orderId) {
        this.lineNumber = lineNumber;
        this.sku = sku;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public int compareTo(Object o) {
        return(lineNumber - ((Line) o).getLineNumber());
    }
}
