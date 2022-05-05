package com.lau.spring2022.groceteria_app.Activities.Activities.Helper;

import android.content.Context;
import android.widget.Toast;

import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.Activities.Activities.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {

    // creating a variable for context and helper database
    private Context context;
    private HelperDB helperDB;

    // creating a constructor for our variables
    public ManagementCart(Context context) {
        this.context = context;
        this.helperDB = new HelperDB(context);
    }

    /* method for inserting a product to the cart list when the user adds it to the cart it checks before if the product is already
       added if yes it just increments the counter and if it's not, it adds it to the cart as a new product
     */
    public void insertProduct(ProductDomain item) {
        ArrayList<ProductDomain> listProduct = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getName().equals(item.getName())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listProduct.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listProduct.add(item);
        }
        helperDB.putListObject("CartList", listProduct);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    // list of all the products in the cart
    public ArrayList<ProductDomain> getListCart() {
        return helperDB.getListObject("CartList");
    }

    // method for the adding of a product as it increments the counter of the number of products in the cart when added
    public void plusNumberProduct(ArrayList<ProductDomain> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() + 1);
        helperDB.putListObject("CartList", listProduct);
        changeNumberItemsListener.changed();
    }
    // method for the removing a product as it decrements the counter of the number of product in the cart when removed
    public void minusNumberProduct(ArrayList<ProductDomain> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listProduct.get(position).getNumberInCart() == 1) {
            listProduct.remove(position);
        } else {
            listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() - 1);
        }
        helperDB.putListObject("CartList", listProduct);
        changeNumberItemsListener.changed();
    }

    // method for adding all the prices of all products in the cart
    public Double getTotalPrice() {
        ArrayList<ProductDomain> listProduct = getListCart();
        double price = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            price += (listProduct.get(i).getPrice() * listProduct.get(i).getNumberInCart());
        }
        return price;
    }
}
