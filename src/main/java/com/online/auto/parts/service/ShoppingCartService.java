package com.online.auto.parts.service;

import com.online.auto.parts.entity.ShoppingCart;
import com.online.auto.parts.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository repo;

    public void save(ShoppingCart cart) {
        repo.save(cart);
    }


}
