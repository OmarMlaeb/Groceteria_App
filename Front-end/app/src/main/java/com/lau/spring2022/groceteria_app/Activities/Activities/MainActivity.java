package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.CategoryAdapter;
import com.lau.spring2022.groceteria_app.Activities.Activities.Adapters.PopularAdapter;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.CategoryDomain;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter; // to provide a binding from an app-specific data set to views that are displayed within a RecyclerView
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_CategoryList; // is a ViewGroup that contains the views corresponding to the data
    private RecyclerView recyclerView_PopularList;

    TextView user;

    String mobile_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        user = findViewById(R.id.textView3);

        Intent intent = getIntent(); // get the mobile number logged in to the app from the login activity
        mobile_num = intent.getStringExtra("mobile_num");

        // url of the local host of the api to send data to the database
        String url = "http://192.168.56.1/Groceteria_Server/get_user.php?mobile_number=" + mobile_num; // send it to the api
        // concatenated the values to the api url to send the data to it

        DownloadTask task = new DownloadTask();
        task.execute(url);

        recyclerView_Category();
        recyclerView_Popular();

        buttonNavigation();
    }

    /* any function that will run in parallel with the application, as the app wont be loading or waiting for the execution of that function
       and to send the data to the database, and to retrieve the conversion calculation from the api and display it on the screen */
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) { // pre execute
            String result = "";
            URL url; // manages all the information present in the URL string
            HttpURLConnection http; // used to make a single request

            // to catch any error that may be executed when we run the code
            try{
                url = new URL(urls[0]); // sending the url
                http = (HttpURLConnection) url.openConnection(); // opening connection between the app and url (api)

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in); // to read the output of the api
                int data = reader.read(); // cursor to read the output of the api

                while( data != -1){ // didn't reach the end of the file
                    char current = (char) data; // take a character every time
                    result += current; // append the character read to the string result
                    data = reader.read(); // not to run to an infinite loop (move the cursor one character)

                }
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) { // when the api is executed
            super.onPostExecute(s);

            try{ // get the first name of the user and display it on the screen
                JSONObject json = new JSONObject(s); // object of JSONObject and assigned to json
                String response = json.getString("Response"); // convert the string to a json object

                user.setText("Hello " + response); // display user first name

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void buttonNavigation(){
        LinearLayout homeTab = findViewById(R.id.homeTab);
        LinearLayout categoriesTab = findViewById(R.id.categoriesTab);
        LinearLayout cartTab = findViewById(R.id.cartTab);
        LinearLayout profileTab = findViewById(R.id.profileTab);

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });

        categoriesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });

        cartTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartListActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });

        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("mobile_num", mobile_num);
                startActivity(intent);
            }
        });
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
        product.add(new ProductDomain("Rice", "rice_img", "Rice (per kg)", 29.0, 1));
        product.add(new ProductDomain("Fish", "fish_img", "Fish (per kg)", 25.0, 1));
        product.add(new ProductDomain("Eggs", "eggs_img", "Eggs (each 15)", 15.0, 1));
        product.add(new ProductDomain("Meat", "meat_img", "Meat (per kg)", 30.0, 1));
        product.add(new ProductDomain("Chicken", "chicken_img", "Chicken (per kg)", 19.0, 1));

        adapter2 = new PopularAdapter(product);
        recyclerView_PopularList.setAdapter(adapter2);
    }
}