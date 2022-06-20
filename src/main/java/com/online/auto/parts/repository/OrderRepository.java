package com.online.auto.parts.repository;

import com.online.auto.parts.entity.Order;
import com.online.auto.parts.entity.OrderStatus;
import com.online.auto.parts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByStatusAndUser(OrderStatus status, User user);
}
