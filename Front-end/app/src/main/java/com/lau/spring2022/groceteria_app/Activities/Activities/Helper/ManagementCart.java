package com.lau.spring2022.groceteria_app.Activities.Activities.Helper;

import android.content.Context;
import android.widget.Toast;

import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.Activities.Activities.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private HelperDB helperDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.helperDB = new HelperDB(context);
    }

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

    public ArrayList<ProductDomain> getListCart() {
        return helperDB.getListObject("CartList");
    }

    public void plusNumberProduct(ArrayList<ProductDomain> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() + 1);
        helperDB.putListObject("CartList", listProduct);
        changeNumberItemsListener.changed();
    }

    public void minusNumberProduct(ArrayList<ProductDomain> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listProduct.get(position).getNumberInCart() == 1) {
            listProduct.remove(position);
        } else {
            listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() - 1);
        }
        helperDB.putListObject("CartList", listProduct);
        changeNumberItemsListener.changed();
    }

    public Double getTotalPrice() {
        ArrayList<ProductDomain> listProduct = getListCart();
        double price = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            price += (listProduct.get(i).getPrice() * listProduct.get(i).getNumberInCart());
        }
        return price;
    }
}
