package com.gmail.dina_elsaftawy.merchantorder.model.data;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Order {
    public String title;
    public String description;
    public String userName;


    public Order() {

    }

    public Order(String title, String description, String userName) {
        this.title = title;
        this.description = description;
        this.userName = userName;
    }
}
