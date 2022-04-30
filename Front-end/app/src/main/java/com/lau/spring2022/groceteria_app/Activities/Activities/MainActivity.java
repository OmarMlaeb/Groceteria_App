package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.CategoryAdapter;
import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.PopularAdapter;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.CategoryDomain;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter; // to provide a binding from an app-specific data set to views that are displayed within a RecyclerView
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_CategoryList; // is a ViewGroup that contains the views corresponding to the data
    private RecyclerView recyclerView_PopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        recyclerView_Category();
        recyclerView_Popular();
    }

    private void recyclerView_Category() {

        // Creates a horizontal LinearLayoutManager to enable to move between categories
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_CategoryList = findViewById(R.id.recyclerView);
        recyclerView_CategoryList.setLayoutManager(linearLayoutManager);

        // array list of the the category class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Fresh Food", "freshfood_icon"));
        category.add(new CategoryDomain("Frozen Food", "frozenfood_icon"));
        category.add(new CategoryDomain("Beverages", "beverages_icon"));
        category.add(new CategoryDomain("Electronics", "electronics_icon"));

        adapter = new CategoryAdapter(category);
        recyclerView_CategoryList.setAdapter(adapter);
    }

    private void recyclerView_Popular() {

        // Creates a horizontal LinearLayoutManager to enable to move between categories
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_PopularList = findViewById(R.id.recyclerView2);
        recyclerView_PopularList.setLayoutManager(linearLayoutManager);

        // array list of the the category class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<ProductDomain> product = new ArrayList<>();
        product.add(new ProductDomain("Rice", "rice_img", "Rice (per kg)", 20.0, 1));
        product.add(new ProductDomain("Fish", "fish_img", "Fish (per kg)", 20.0, 1));
        product.add(new ProductDomain("Eggs", "eggs_img", "Eggs (each 15)", 20.0, 1));
        product.add(new ProductDomain("Meat", "meat_img", "Meat (per kg)", 20.0, 1));
        product.add(new ProductDomain("Chicken", "chicken_img", "Chicken (per kg)", 20.0, 1));

        adapter2 = new PopularAdapter(product);
        recyclerView_PopularList.setAdapter(adapter2);
    }
}