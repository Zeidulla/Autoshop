package com.online.auto.parts.service;

import com.online.auto.parts.entity.Order;
import com.online.auto.parts.entity.OrderStatus;
import com.online.auto.parts.entity.User;
import com.online.auto.parts.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public void save(Order order) {
        repo.save(order);
    }

    public List<Order> findOrderByOrderStatusAndUser(OrderStatus status, User user) {
        return repo.findOrdersByStatusAndUser(status, user);
    }

}
