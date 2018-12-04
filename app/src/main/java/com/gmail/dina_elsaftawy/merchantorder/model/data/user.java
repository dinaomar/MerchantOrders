package com.gmail.dina_elsaftawy.merchantorder.model.data;

import java.util.ArrayList;

public class user {
    private String name, email,password;
    private ArrayList<order> orders;

    public user(String name, String email, ArrayList<order> orders) {
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<order> getOrders() {
        return orders;
    }
}
