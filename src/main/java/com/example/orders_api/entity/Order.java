package com.example.orders_api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="orders")
public class Order extends AbstractEntity {

    @Id
    @NotNull
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="storeid")
    private Integer storeId;

    public Order() {}

    public Order(Integer id, Integer storeId) {
        this.id = id;
        this.storeId = storeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
