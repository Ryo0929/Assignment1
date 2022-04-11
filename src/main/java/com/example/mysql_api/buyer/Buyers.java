package com.example.mysql_api.buyer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="buyers")
public class Buyers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyer_id;
    private String buyer_name;
    private int number_of_item_purchased;
    private String buyer_password;

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

    public String getBuyer_password() {
        return buyer_password;
    }

    public void setBuyer_password(String buyer_password) {
        this.buyer_password = buyer_password;
    }
}
