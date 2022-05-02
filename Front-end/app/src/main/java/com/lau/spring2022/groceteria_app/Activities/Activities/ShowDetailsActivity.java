package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.Activities.Activities.Helper.ManagementCart;
import com.lau.spring2022.groceteria_app.R;

public class ShowDetailsActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt;
    private TextView priceTxt;
    private TextView descriptionTxt;
    private TextView numberOrderTxt;
    private ImageView prodImg;
    private ImageView minusBtn;
    private ImageView plusBtn;

    private ProductDomain object;

    private ManagementCart managementCart;

    int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_show_details);

        managementCart = new ManagementCart(this);

        addToCartBtn = findViewById(R.id.addtoCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        prodImg = findViewById(R.id.prod_img);
        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.addBtn);

        getBundle();
    }

    public void getBundle(){
        object = (ProductDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(prodImg);

        titleTxt.setText(object.getName());
        priceTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder += 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder > 1){
                    numberOrder -= 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertProduct(object);

                startActivity(new Intent(ShowDetailsActivity.this, MainActivity.class));
            }
        });
    }
}