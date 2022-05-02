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

public class FreshFoodActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_FreshFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_fresh_food);

        recyclerView_FreshFood();
    }

    private void recyclerView_FreshFood() {

        // Creates a horizontal LinearLayoutManager to enable to move between categories
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_FreshFoodList = findViewById(R.id.recyclerViewFreshFoodList);
        recyclerView_FreshFoodList.setLayoutManager(linearLayoutManager);

        // array list of the the category class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<ProductDomain> product = new ArrayList<>();
        product.add(new ProductDomain("Bread", "bread_img", "Bread (per 6 pieces)", 10.0, 1));
        product.add(new ProductDomain("Pasta", "pasta_img", "Pasta (per 500 g)", 27.0, 1));
        product.add(new ProductDomain("Eggs", "eggs_img", "Eggs (each 15)", 15.0, 1));
        product.add(new ProductDomain("Oil", "oil_img", "Oil (per 1 Liter)", 33.0, 1));
        product.add(new ProductDomain("Rice", "rice_img", "Rice (per 3 kg)", 29.0, 1));

        adapter = new ProductsAdapter(product);
        recyclerView_FreshFoodList.setAdapter(adapter);
    }
}