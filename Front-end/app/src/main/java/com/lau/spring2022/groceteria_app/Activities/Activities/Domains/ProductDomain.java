package com.lau.spring2022.groceteria_app.Activities.Activities.Domains;

import java.io.Serializable;

public class ProductDomain implements Serializable {

    private String name;
    private String pic;
    private String description;
    private Double price;
    private int numberInCart;

    public ProductDomain(String name, String pic, String description, Double price) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.price = price;
    }

    public ProductDomain(String name, String pic, String description, Double price, int numberInCart) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.numberInCart = numberInCart;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getNumberInCart() {
        return numberInCart;
    }
}
