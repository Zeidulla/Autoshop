package com.online.auto.parts.service;

import com.online.auto.parts.entity.OrderPosition;
import com.online.auto.parts.repository.OrderPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPositionService {
    @Autowired
    private OrderPositionRepository repo;

    public void save(OrderPosition orderPosition) {
        repo.save(orderPosition);
    }

    public OrderPosition findById(Long id) {
        return repo.findById(id).get();
    }

    public void remove(Long id) {
        repo.deleteById(id);
    }

}
