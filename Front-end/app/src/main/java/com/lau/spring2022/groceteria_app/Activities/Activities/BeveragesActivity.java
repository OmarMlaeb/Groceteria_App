package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.ProductsAdapter;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

public class BeveragesActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_BeveragesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_beverages);

        recyclerView_Beverages();
    }

    private void recyclerView_Beverages() {

        // Creates a vertical LinearLayoutManager to enable to move between products
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_BeveragesList = findViewById(R.id.recyclerViewBeveragesList);
        recyclerView_BeveragesList.setLayoutManager(linearLayoutManager);

        // array list of the the products class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<ProductDomain> product = new ArrayList<>();
        product.add(new ProductDomain("Water", "water_img", "Water (per 1 bottle (0.5 Liters))", 2.0, 1));
        product.add(new ProductDomain("Apple Juice", "apple_juice_img", "Apple Juice (per 1 bottle (0.5 Liters))", 3.0, 1));
        product.add(new ProductDomain("Pepsi Can", "pepsi_can_img", "Pepsi Can (per 1 can (330 ml))", 5.0, 1));
        product.add(new ProductDomain("Milk", "milk_img", "Milk (per 1 Liter)", 13.0, 1));
        product.add(new ProductDomain("Energy Drink", "energy_drink_img", "Energy Drink (per 1 can (330 ml))", 10.0, 1));

        adapter = new ProductsAdapter(product);
        recyclerView_BeveragesList.setAdapter(adapter);
    }
}