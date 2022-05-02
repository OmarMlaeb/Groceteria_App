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

public class FrozenFoodActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_FrozenFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_frozen_food);

        recyclerView_FrozenFood();
    }

    private void recyclerView_FrozenFood() {

        // Creates a vertical LinearLayoutManager to enable to move between products
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_FrozenFoodList = findViewById(R.id.recyclerViewFrozenFoodList);
        recyclerView_FrozenFoodList.setLayoutManager(linearLayoutManager);

        // array list of the the products class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<ProductDomain> product = new ArrayList<>();
        product.add(new ProductDomain("Pizza", "pizza_img", "Rice (per 1 piece)", 30.0, 1));
        product.add(new ProductDomain("Fish", "fish_img", "Fish (per 500 g)", 25.0, 1));
        product.add(new ProductDomain("Fish Fillet", "fish_fillet_img", "Fish Fillet (per 500 g)", 29.0, 1));
        product.add(new ProductDomain("Meat", "meat_img", "Meat (per 500 g)", 30.0, 1));
        product.add(new ProductDomain("Chicken", "chicken_img", "Chicken (per 500 g)", 19.0, 1));

        adapter = new ProductsAdapter(product);
        recyclerView_FrozenFoodList.setAdapter(adapter);
    }
}