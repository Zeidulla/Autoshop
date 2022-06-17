package com.online.auto.parts.entity;

import javax.persistence.*;


@Entity
@Table(name = "cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

    public ShoppingCart() {
    }

    public ShoppingCart(Order order) {
        this.order = order;
        order.setShoppingCart(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
