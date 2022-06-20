package com.online.auto.parts.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
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

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderPosition> positions;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Transient
    private double totalPrice;

    public Order() {
        creationDate = LocalDateTime.now();
        updatingDate = LocalDateTime.now();
        positions = new HashSet<>();
        status = OrderStatus.NEW;
    }

    public Order(OrderStatus status, LocalDateTime creationDate, LocalDateTime updatingDate) {
        this.status = status;
        this.creationDate = creationDate;
        this.updatingDate = updatingDate;
        this.positions = new HashSet<>();
    }

    public void addOrderPosition(OrderPosition orderPosition) {
        positions.add(orderPosition);
        orderPosition.setOrder(this);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.addOrder(this);
    }

    public double getTotalPrice() {
        this.totalPrice = positions.stream().map(s -> s.getQuantity() * s.getAutoPart().getPrice()).reduce(0.0, (a, b) -> a + b);
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return status == order.status && creationDate.equals(order.creationDate) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, creationDate, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                ", creationDate=" + creationDate +
                ", updatingDate=" + updatingDate +
                ", positions=" + positions +
                '}';
    }
}