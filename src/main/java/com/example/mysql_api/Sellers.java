package com.example.mysql_api;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sellers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seller_id;
    private String seller_name;
    private String seller_feedback;
    private int number_of_items_sold;

    public Sellers(){}

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_feedback() {
        return seller_feedback;
    }

    public void setSeller_feedback(String seller_feedback) {
        this.seller_feedback = seller_feedback;
    }

    public int getNumber_of_items_sold() {
        return number_of_items_sold;
    }

    public void setNumber_of_items_sold(int number_of_items_sold) {
        this.number_of_items_sold = number_of_items_sold;
    }
}
