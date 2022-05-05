package com.lau.spring2022.groceteria_app.Activities.Activities.Domains;

import java.io.Serializable;

// serializable is to allow the user to pass the data between one activity to another activity
public class ProductDomain implements Serializable {

    // variables for our products name, image, description, price and number of products in the Cart
    private String name;
    private String pic;
    private String description;
    private Double price;
    private int numberInCart;

    // creating constructors for our variables
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

    // creating getter and setter methods
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
