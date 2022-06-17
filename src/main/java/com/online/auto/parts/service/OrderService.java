package com.online.auto.parts.service;

import com.online.auto.parts.entity.Order;
import com.online.auto.parts.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository repo;

    @Autowired
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }


    public void save(Order order) {
        repo.save(order);
    }

}
