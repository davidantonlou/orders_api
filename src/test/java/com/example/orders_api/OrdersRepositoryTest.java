package com.example.orders_api;


import com.example.orders_api.entity.Line;
import com.example.orders_api.entity.Order;
import com.example.orders_api.repository.LinesRepository;
import com.example.orders_api.repository.OrdersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class OrdersRepositoryTest {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    LinesRepository linesRepository;

    @Test
    public void testInsertOrder() {
        Order order = new Order(1, 1);
        ordersRepository.save(order);

        Optional<Order> findedOrder = ordersRepository.findById(1);
        assertTrue("Check finded Order", findedOrder.isPresent());
        assertNotNull(findedOrder.get());
        assertEquals("Assert Store Id", findedOrder.get().getStoreId(), order.getStoreId());
    }

    @Test
    public void testInsertLines() {
        Order order = new Order(1, 1);
        ordersRepository.save(order);

        Set<Line> lineList = new HashSet();
        lineList.add(new Line(2, "sku_1", order.getId()));
        lineList.add(new Line(3, "sku_2", order.getId()));
        linesRepository.saveAll(lineList);

        Optional<Order> findedOrder = ordersRepository.findById(1);
        assertTrue("Check finded Order", findedOrder.isPresent());
        assertNotNull(findedOrder.get());
        assertEquals("Assert Store Id", findedOrder.get().getStoreId(), order.getStoreId());

        Page<Line> findedLines = linesRepository.findByOrderId(1, Pageable.unpaged());
        assertNotNull(findedLines);
        assertEquals("Assert Number of Lines", findedLines.getTotalElements(), new Long(2));
    }
}