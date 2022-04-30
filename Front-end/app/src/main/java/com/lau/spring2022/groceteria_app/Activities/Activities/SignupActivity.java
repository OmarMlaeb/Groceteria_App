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

public class SignupActivity extends AppCompatActivity {

    //Page 2

    EditText first_name;
    EditText last_name;
    EditText mobile_number;
    EditText email;
    EditText year_of_birth;
    EditText password;
    EditText confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_signup);

        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText)  findViewById(R.id.last_name);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        email = (EditText)  findViewById(R.id.email);
        year_of_birth = (EditText)  findViewById(R.id.yearofbirth);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText)  findViewById(R.id.confirm_password);
    }

    // when the TextView (Not a member yet? Sign up here.) is clicked, the signup page will appear
    public void goLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    String response;

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
                response = json.getString("Response"); // convert the string to a json object

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void signup(View view) {
        String first_name_ = first_name.getText().toString();
        String last_name_ = last_name.getText().toString();
        String mobile_number_ = mobile_number.getText().toString();
        int pars_mobile_number = Integer.parseInt(mobile_number_);
        String email_ = email.getText().toString();
        String year_of_birth_ = year_of_birth.getText().toString();
        int pars_year_birth = Integer.parseInt(year_of_birth_);
        String password_ = password.getText().toString();
        String confirm_password_ = confirm_password.getText().toString();

        if (TextUtils.isEmpty(first_name_) || TextUtils.isEmpty(last_name_) || TextUtils.isEmpty(mobile_number_) || TextUtils.isEmpty(email_)
                || TextUtils.isEmpty(year_of_birth_) || TextUtils.isEmpty(password_) || TextUtils.isEmpty(confirm_password_)) {

            Toast.makeText(SignupActivity.this, "All Fields Required!", Toast.LENGTH_LONG).show();
        } else if(!password_.equals(confirm_password_)) {
            Toast.makeText(SignupActivity.this, "Password and Confirm Password doesn't match", Toast.LENGTH_LONG).show();
        } else if(password_.length() < 8) {
            Toast.makeText(SignupActivity.this, "Password must contain at least 8 characters", Toast.LENGTH_LONG).show();
        } else {
            // url of the local host of the api to send data to the database
            String url = "http://192.168.56.1/Groceteria_Server/signup_accounts.php?first_name=" + first_name_ +
                    "&last_name=" + last_name_ + "&mobile_number=" + pars_mobile_number + "&email=" + email_ + "&year_of_birth=" + pars_year_birth
                    + "&password=" + password_ + "&confirm_password=" + confirm_password_;
            // concatenated the values to the api url to send the data to it

            DownloadTask task = new DownloadTask();
            task.execute(url);

            Toast.makeText(SignupActivity.this, "Created Account Successfully!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}