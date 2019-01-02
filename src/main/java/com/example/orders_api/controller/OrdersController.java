package com.example.orders_api.controller;

import com.example.orders_api.entity.OrderDetail;
import com.example.orders_api.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping(path = "process", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean process(@RequestBody String request) throws IOException {
        return ordersService.processOrder(request);
    }

    @GetMapping(path = "order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetail getOrder(@PathVariable("id") Integer orderId) {
        return ordersService.getOrder(orderId);
    }

    @GetMapping(path = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetail> getAllOrders() {
        return ordersService.getAllOrders();
    }
}
