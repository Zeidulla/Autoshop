package com.online.auto.parts.repository;


import com.online.auto.parts.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {


}
