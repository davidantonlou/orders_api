package com.example.orders_api.repository;

import com.example.orders_api.entity.Line;
import com.example.orders_api.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinesRepository extends JpaRepository<Line, Integer> {
    Page<Line> findByOrderId(Integer orderId, Pageable pageable);
}
