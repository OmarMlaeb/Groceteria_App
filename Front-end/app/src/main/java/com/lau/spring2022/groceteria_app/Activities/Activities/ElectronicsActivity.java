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

public class ElectronicsActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_ElectronicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_electronics);

        recyclerView_Electronics();
    }

    private void recyclerView_Electronics() {

        // Creates a vertical LinearLayoutManager to enable to move between products
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_ElectronicsList = findViewById(R.id.recyclerViewElectronicsList);
        recyclerView_ElectronicsList.setLayoutManager(linearLayoutManager);

        // array list of the the products class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<ProductDomain> product = new ArrayList<>();
        product.add(new ProductDomain("Television", "tv_img", "Samsung Smart Tv Full HD (55 inches)", 700.0, 1));
        product.add(new ProductDomain("Radio", "radio_img", "Radio", 50.0, 1));
        product.add(new ProductDomain("Hp Laptop", "laptop_img", "Hp Laptop (Screen HD 14.3 inches, 1 Tera)", 1200.0, 1));
        product.add(new ProductDomain("Iphone 12", "iphone_img", "Iphone 12 (256 GB)", 890.0, 1));
        product.add(new ProductDomain("Ipad 3", "ipad_img", "Ipad 3 (16 GB)", 990.0, 1));

        adapter = new ProductsAdapter(product);
        recyclerView_ElectronicsList.setAdapter(adapter);
    }
}