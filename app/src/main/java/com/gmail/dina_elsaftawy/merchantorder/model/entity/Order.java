package com.gmail.dina_elsaftawy.merchantorder.model.entity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Order {
    public String title;
    public String description;
    public String userName;
    public String orderId;

    public String getOrderId() {
        return orderId;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public Order() {

    }

    public Order(String title, String description, String userName, String orderId) {
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.orderId = orderId;
    }
}
