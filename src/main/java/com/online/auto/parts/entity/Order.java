package com.online.auto.parts.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "creation")
    private LocalDateTime creationDate;

    @Column(name = "updating")
    private LocalDateTime updatingDate;

    @OneToMany(mappedBy = "order")
    private Set<OrderPosition> positions;

    @OneToOne(mappedBy = "order")
    private ShoppingCart shoppingCart;

    public Order() {
    }


    public Order(OrderStatus status, LocalDateTime creationDate, LocalDateTime updatingDate) {
        this.status = status;
        this.creationDate = creationDate;
        this.updatingDate = updatingDate;
        this.positions = new HashSet<>();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addOrderPosition(OrderPosition orderPosition) {
        positions.add(orderPosition);
    }

    public Set<OrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(Set<OrderPosition> positions) {
        this.positions = positions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatingDate() {
        return updatingDate;
    }

    public void setUpdatingDate(LocalDateTime updatingDate) {
        this.updatingDate = updatingDate;
    }
}
