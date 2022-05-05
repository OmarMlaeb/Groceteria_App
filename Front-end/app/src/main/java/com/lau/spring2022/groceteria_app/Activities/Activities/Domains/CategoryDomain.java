package com.lau.spring2022.groceteria_app.Activities.Activities.Domains;

// category class having attributes that define a category the name and the image

public class CategoryDomain {

    // variables for our category name and image
    private String category_name;
    private String category_pic;

    // creating constructor for our variables
    public CategoryDomain(String category_name, String category_pic) {
        this.category_name = category_name;
        this.category_pic = category_pic;
    }

    // creating getter and setter methods
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_pic() {
        return category_pic;
    }

    public void setCategory_pic(String category_pic) {
        this.category_pic = category_pic;
    }
}
