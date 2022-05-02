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

public class Vegetables_FruitsActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_VegetablesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_vegetables_fruits);

        recyclerView_FrozenFood();
    }

    private void recyclerView_FrozenFood() {

        // Creates a vertical LinearLayoutManager to enable to move between products
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_VegetablesList = findViewById(R.id.recyclerViewVegetablesList);
        recyclerView_VegetablesList.setLayoutManager(linearLayoutManager);

        // array list of the the products class to connect the attributes of the class to the adapter of the the recycler view
        ArrayList<ProductDomain> product = new ArrayList<>();
        product.add(new ProductDomain("Lettuce", "lettuce_img", "Lettuce (per 1 piece)", 5.0, 1));
        product.add(new ProductDomain("Corn", "corn_img", "Corn (per 1 kg)", 10.0, 1));
        product.add(new ProductDomain("Apples", "apple_img", "Apples (per 1 kg)", 13.0, 1));
        product.add(new ProductDomain("Tomato", "tomato_img", "Tomato (per 1 kg)", 7.0, 1));
        product.add(new ProductDomain("Banana", "banana_img", "Banana (per 1 kg)", 9.0, 1));

        adapter = new ProductsAdapter(product);
        recyclerView_VegetablesList.setAdapter(adapter);
    }
}