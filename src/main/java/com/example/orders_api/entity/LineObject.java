package com.example.orders_api.entity;


public class LineObject extends AbstractEntity {
    private Integer line_number;
    private String sku;

    public LineObject() {}

    public Integer getLine_number() {
        return line_number;
    }

    public void setLine_number(Integer line_number) {
        this.line_number = line_number;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

}
