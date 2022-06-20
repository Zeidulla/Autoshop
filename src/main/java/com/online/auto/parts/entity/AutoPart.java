package com.online.auto.parts.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "auto_parts")
public class AutoPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "price")
    private Double price = 0d;


    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int totalQuantity;

    @Column(name = "path")
    private String pathToPicture = "not_found.png";

    @OneToMany(mappedBy = "autoPart", fetch = FetchType.EAGER)
    private Set<OrderPosition> orderPositions;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User owner;


    @Transient
    private int initialQuantity;

    public AutoPart(String name, Double price, String category, int totalQuantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.totalQuantity = totalQuantity;
        this.orderPositions = new HashSet<>();
        this.initialQuantity = totalQuantity;
    }

    public AutoPart() {
        this.orderPositions = new HashSet<>();
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getPathToPicture() {
        return pathToPicture;
    }

    public void setPathToPicture(String pathToPicture) {
        this.pathToPicture = pathToPicture;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoPart autoPart = (AutoPart) o;
        return name.equals(autoPart.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AutoPart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", initialQuantity=" + initialQuantity +
                '}';
    }
}
