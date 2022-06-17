package com.online.auto.parts.entity;

import javax.persistence.*;

/**
 * Класс отвечающий за одну позицию в заказе
 *
 * @see Order
 */
@Entity
@Table(name = "order_position")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    private AutoPart autoPart;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    public OrderPosition() {
    }

    public OrderPosition(Integer quantity, AutoPart autoPart) {
        this.quantity = quantity;
        this.autoPart = autoPart;
        autoPart.addPosition(this);
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

}
