package com.online.auto.parts.entity;

public enum OrderStatus {
    NEW("новый"), CANCELED("отмененный"), PAID("оплаченный"), COMPLETED("завершенный");

    String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
