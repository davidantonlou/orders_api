package com.example.orders_api.service;

import com.example.orders_api.entity.Line;
import com.example.orders_api.entity.Order;
import com.example.orders_api.entity.OrderDetail;
import com.example.orders_api.repository.LinesRepository;
import com.example.orders_api.repository.OrdersRepository;
import com.example.orders_api.utils.Constants;
import com.example.orders_api.utils.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private RequestParser requestParser;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private LinesRepository linesRepository;

    @PostConstruct
    public void init() {
        this.requestParser = new RequestParser();
    }

    public boolean processOrder(String request) throws ResponseStatusException, IOException {
        Order order = this.requestParser.processOrder(request);
        List<Line> lines = this.requestParser.processLine(request, order.getId());

        if (existOrder(order)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.ORDER_EXIST, new Exception());
        } else if (!validLines(lines)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.LINES_NOT_VALID, new Exception());
        } else {
            ordersRepository.save(order);
            linesRepository.saveAll(lines);

            return true;
        }
    }

    private boolean existOrder(Order order) {
        Optional<Order> findedOrder = ordersRepository.findById(order.getId());
        return findedOrder.isPresent() && findedOrder.get().getStoreId().equals(order.getStoreId());
    }

    private boolean validLines(List<Line> lines) {
        if (lines != null && lines.size() > 0) {
            Collections.sort(lines);
            for (int i = 0; i < lines.size(); i++) {
                if (!lines.get(i).getLineNumber().equals(i+1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public OrderDetail getOrder(Integer orderId) {
        Optional<Order> order = ordersRepository.findById(orderId);

        if (order.isPresent()) {
            Page<Line> lines = linesRepository.findByOrderId(orderId, Pageable.unpaged());
            return new OrderDetail(order.get(), lines.getContent());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.ORDER_NOT_EXIST, new Exception());
        }
    }

    public List<OrderDetail> getAllOrders() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<Order> orders = ordersRepository.findAll();
        if (orders.size() > 0){
            for (int i=0; i<orders.size(); i++) {
                orderDetailList.add(new OrderDetail(orders.get(i), linesRepository.findByOrderId(orders.get(i).getId(), Pageable.unpaged()).getContent()));
            }
        }
        return orderDetailList;
    }
}