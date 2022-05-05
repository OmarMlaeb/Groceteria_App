package com.lau.spring2022.groceteria_app.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lau.spring2022.groceteria_app.R;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressActivity extends AppCompatActivity {

    EditText mobile_numberTxt;
    EditText building_nameTxt;
    EditText apartmentTxt;
    EditText instructionsTxt;
    EditText receiver_nameTxt;
    EditText receiver_mobile_numberTxt;

    String mobile_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_address);

        mobile_numberTxt = (EditText) findViewById(R.id.mobile_numberTxt);
        building_nameTxt = (EditText)  findViewById(R.id.building_nameTxt);
        apartmentTxt = (EditText) findViewById(R.id.apartmentTxt);
        instructionsTxt = (EditText)  findViewById(R.id.instructionsTxt);
        receiver_nameTxt = (EditText)  findViewById(R.id.receiver_nameTxt);
        receiver_mobile_numberTxt = (EditText) findViewById(R.id.receiver_mobile_numberTxt);

        Intent intent = getIntent(); // get the mobile number logged in to the app from the login activity
        mobile_num = intent.getStringExtra("mobile_num");

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

            try{
                JSONObject json = new JSONObject(s); // object of JSONObject and assigned to json
                String response = json.getString("Response"); // convert the string to a json object

                if(response.equals("Added Address Successfully!")){
                    Toast.makeText(AddressActivity.this, "Added Address Successfully!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddressActivity.this, ProfileActivity.class);
                    intent.putExtra("mobile_num", mobile_num);
                    startActivity(intent);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addAddress(View view) {
        String mobile_number = mobile_numberTxt.getText().toString();
        int pars_mobile_number = Integer.parseInt(mobile_number);
        String building_name = building_nameTxt.getText().toString();
        String apartment = apartmentTxt.getText().toString();
        String instructions = instructionsTxt.getText().toString();
        String receiver_name = receiver_nameTxt.getText().toString();
        String receiver_mobile_number = receiver_mobile_numberTxt.getText().toString();
        int pars_receiver_mobile_number  = Integer.parseInt(receiver_mobile_number);

        if (TextUtils.isEmpty(mobile_number) || TextUtils.isEmpty(building_name) || TextUtils.isEmpty(apartment) || TextUtils.isEmpty(instructions)
                || TextUtils.isEmpty(receiver_name) || TextUtils.isEmpty(receiver_mobile_number)) {

            Toast.makeText(AddressActivity.this, "All Fields Required!", Toast.LENGTH_LONG).show();
        }  else {
            // url of the local host of the api to send data to the database
            String url = "http://192.168.56.1/Groceteria_Server/addresses.php?user_mobile_number=" + pars_mobile_number +
                    "&building_name=" + building_name + "&apartment=" + apartment + "&delivery_instructions=" + instructions + "&receiver_name=" + receiver_name
                    + "&receiver_mobile_number=" + pars_receiver_mobile_number;
            // concatenated the values to the api url to send the data to it

            DownloadTask task = new DownloadTask();
            task.execute(url);
        }
    }
}