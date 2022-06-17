package com.online.auto.parts.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auto_parts")
public class AutoPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // строка длиной от 1 до 20 символов
    @Column(name = "name")
    private String name;
    // числа больше 0 и длиной до 20 символов
    @Column(name = "price")
    private Double price;
    // категория товаров
    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int totalQuantity;

    @OneToMany(mappedBy = "autoPart", cascade = CascadeType.ALL)
    private Set<OrderPosition> orderPositions;

    public AutoPart(String name, Double price, String category, int totalQuantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.totalQuantity = totalQuantity;
        orderPositions = new HashSet<>();
    }

    public AutoPart() {
        orderPositions = new HashSet<>();
    }

    public void addPosition(OrderPosition orderPosition) {
        orderPositions.add(orderPosition);
    }

    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
