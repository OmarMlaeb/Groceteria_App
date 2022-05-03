package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lau.spring2022.groceteria_app.R;

public class ProfileActivity extends AppCompatActivity {

    TextView addressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile);

        addressBtn = findViewById(R.id.addressBtn);

        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, AddressActivity.class));
            }
        });

        buttonNavigation();
    }

    public void buttonNavigation(){
        LinearLayout homeTab = findViewById(R.id.homeTab);
        LinearLayout categoriesTab = findViewById(R.id.categoriesTab);
        LinearLayout cartTab = findViewById(R.id.cartTab);
        LinearLayout profileTab = findViewById(R.id.profileTab);

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        categoriesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CategoriesActivity.class));
            }
        });

        cartTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CartListActivity.class));
            }
        });

        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });
    }
}