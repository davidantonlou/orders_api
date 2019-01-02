package com.example.orders_api.utils;

import com.example.orders_api.entity.*;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import java.io.IOException;
import java.util.*;

public class RequestParser {

    public RequestParser() { }

    public Order processOrder(String request) {
        Order order = new Order();
        Any jsonDeserialize = JsonIterator.deserialize(request);
        order.setId(jsonDeserialize.get("order").get("id").toInt());
        order.setStoreId(Integer.parseInt(jsonDeserialize.get("order").get("store_id").toString()));

        return order;
    }

    public List<Line> processLine(String request, Integer order_id) throws IOException {
        Any jsonDeserialize = JsonIterator.deserialize(request);
        return getLines(jsonDeserialize.get("order").get("lines").toString(), order_id);
    }

    private List<Line> getLines(String request, Integer order_id) throws IOException {
        List<Line> lines = new ArrayList<>();
        LineObject lineObject;
        JsonIterator iterator = JsonIterator.parse(request);
        while (iterator.readArray()) {
            lineObject = iterator.read(LineObject.class);
            lines.add(new Line(lineObject.getLine_number(), lineObject.getSku(), order_id));
        }
        return lines;
    }
}
