package com.example.mysql_api;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "item_table")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;
    private String item_name;
    private int item_category;
    private String condition;
    private double sale_price;
    private int quantity;


    public Item() {
    }

    public int getId() {
        return item_id;
    }

    public void setId(int id) {
        this.item_id = id;
    }

    public String getName() {
        return item_name;
    }

    public void setName(String name) {
        this.item_name = name;
    }

    public int getCategory() {
        return item_category;
    }

    public void setCategory(int category) {
        this.item_category = category;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return sale_price;
    }

    public void setPrice(double price) {
        this.sale_price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
