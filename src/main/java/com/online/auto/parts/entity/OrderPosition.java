package com.online.auto.parts.entity;

import javax.persistence.*;
import java.util.Objects;


 //Класс отвечающий за одну позицию в заказе

@Entity
@Table(name = "order_positions")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    private AutoPart autoPart;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Order order;

    public OrderPosition() {
    }

    public OrderPosition(Integer quantity, AutoPart autoPart) {
        this.quantity = quantity;
        this.autoPart = autoPart;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AutoPart getAutoPart() {
        return autoPart;
    }

    public void setAutoPart(AutoPart autoPart) {
        this.autoPart = autoPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return Objects.equals(autoPart, that.autoPart) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoPart, order);
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "quantity=" + quantity +
                ", autoPart=" + autoPart +
                '}';
    }
}
