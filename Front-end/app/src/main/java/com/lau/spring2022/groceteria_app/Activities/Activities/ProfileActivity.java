package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lau.spring2022.groceteria_app.R;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {

    TextView addressBtn;
    TextView fullname;
    TextView mobileNumber;
    TextView email;
    TextView yearofbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile);

        addressBtn = findViewById(R.id.addressBtn);
        fullname = findViewById(R.id.fullname);
        mobileNumber = findViewById(R.id.mobileNumber);
        email = findViewById(R.id.email);
        yearofbirth = findViewById(R.id.yearofbirth);

        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, AddressActivity.class));
            }
        });

        Intent intent = getIntent(); // get the mobile number logged in to the app from the login activity
        String mobile_num = intent.getStringExtra("mobile_num");

        // url of the local host of the api to send data to the database
        String url = "http://192.168.56.1/Groceteria_Server/profiles.php?mobile_number=" + mobile_num; // send it to the api
        // concatenated the values to the api url to send the data to it

        DownloadTask task = new DownloadTask();
        task.execute(url);

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
                String response1 = json.getString("First_name"); // convert the string to a json object
                String response2 = json.getString("Last_name"); // convert the string to a json object
                fullname.setText(response1 + " " + response2);

                String response3 = json.getString("Mobile_Number"); // convert the string to a json object
                mobileNumber.setText(response3);

                String response4 = json.getString("Email"); // convert the string to a json object
                email.setText(response4);

                String response5 = json.getString("Year_Of_Birth"); // convert the string to a json object
                yearofbirth.setText(response5);

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