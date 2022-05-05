package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.CategoriesAdapter2;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.CategoryDomain;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter; // to provide a binding from an app-specific data set to views that are displayed within a RecyclerView
    private RecyclerView recyclerView_CategoryList; // is a ViewGroup that contains the views corresponding to the data

    String mobile_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_categories);

        Intent intent = getIntent(); // get the mobile number logged in to the app from the login activity
        mobile_num = intent.getStringExtra("mobile_num");

        buttonNavigation();
        recyclerView_Category();
    }

    public void buttonNavigation(){
        LinearLayout homeTab = findViewById(R.id.homeTab);
        LinearLayout categoriesTab = findViewById(R.id.categoriesTab);
        LinearLayout cartTab = findViewById(R.id.cartTab);
        LinearLayout profileTab = findViewById(R.id.profileTab);

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });

        categoriesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, CategoriesActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });

        cartTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, CartListActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });

        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this, ProfileActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });
    }

    private void recyclerView_Category() {

        // Creates a horizontal LinearLayoutManager to enable to move between categories
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_CategoryList = findViewById(R.id.recyclerView);
        recyclerView_CategoryList.setLayoutManager(linearLayoutManager);

        // array list of the the category class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Fresh Food", "freshfood_icon"));
        category.add(new CategoryDomain("Frozen Food", "frozenfood_icon"));
        category.add(new CategoryDomain("Beverages", "beverages_icon"));
        category.add(new CategoryDomain("Electronics", "electronics_icon"));
        category.add(new CategoryDomain("Vegetables &\nFruits", "vegetables_icon"));

        adapter = new CategoriesAdapter2(category);
        recyclerView_CategoryList.setAdapter(adapter);
    }
}