package com.example.mysql_api.buyer;

import javax.persistence.*;

@Entity
@Table(name="buyers")
public class Buyers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyer_id;
    private String buyer_name;
    private int number_of_item_purchased;
    private String password;

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public int getNumber_of_item_purchased() {
        return number_of_item_purchased;
    }

    public void setNumber_of_item_purchased(int number_of_item_purchased) {
        this.number_of_item_purchased = number_of_item_purchased;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
