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
import android.widget.ScrollView;
import android.widget.TextView;

import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.CartAdapter;
import com.lau.spring2022.groceteria_app.Activities.Activities.Helper.ManagementCart;
import com.lau.spring2022.groceteria_app.Activities.Activities.Interface.ChangeNumberItemsListener;
import com.lau.spring2022.groceteria_app.R;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCart;
    private ManagementCart managementCart;
    TextView totalPriceTxt;
    TextView deliveryTxt;
    TextView taxTxt;
    TextView totalTxt;
    TextView checkoutBtn;
    TextView emptyTxt;

    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        deliveryTxt = findViewById(R.id.deliveryPriceTxt);
        taxTxt = findViewById(R.id.taxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
        recyclerViewCart = findViewById(R.id.recyclerViewCart);

        initList();

        calculateCart();

        buttonNavigation();
    }

    public void buttonNavigation(){
        LinearLayout homeTab = findViewById(R.id.homeTab);
        LinearLayout categoriesTab = findViewById(R.id.categoriesTab);
        LinearLayout cartTab = findViewById(R.id.cartTab);

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });

        categoriesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CategoriesActivity.class));
            }
        });

        cartTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
    }

    private void initList(){
        // Creates a horizontal LinearLayoutManager to enable to move between categories
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCart.setLayoutManager(linearLayoutManager);

        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewCart.setAdapter(adapter);

        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart(){
        double percentTax = 0.02;
        double delivery = 20;

        tax = Math.round((managementCart.getTotalPrice() * percentTax) * 100) / 100;

        double total = Math.round((managementCart.getTotalPrice() + tax + delivery) * 100) / 100;

        double itemTotal = Math.round((managementCart.getTotalPrice()) * 100) / 100;

        totalPriceTxt.setText("$ " + itemTotal);
        taxTxt.setText("$ " + tax);
        deliveryTxt.setText("$ " + delivery);
        totalTxt.setText("$ " + total);
    }
}