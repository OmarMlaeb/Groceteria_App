package com.lau.spring2022.groceteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    //Page 1

    EditText mobile_number;
    EditText password;
    CheckBox show_password;
    Button login;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        mobile_number = (EditText) findViewById(R.id.mobile_number);
        password = (EditText)  findViewById(R.id.password);
        show_password = (CheckBox) findViewById(R.id.checkbox);
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);

        // to show or hide password when the checkbox is clicked to show password
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

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

    public void login(View view) {
        String mobile_number_ = mobile_number.getText().toString();
        int pars_mobile_number = Integer.parseInt(mobile_number_);
        String password_ = password.getText().toString();

        if(TextUtils.isEmpty(mobile_number_) || TextUtils.isEmpty(password_)){
            Toast.makeText(LoginActivity.this, "All Fields Required!", Toast.LENGTH_LONG).show();
        } else {
            // url of the local host of the api to send data to the database
            String url = "http://192.168.56.1/Groceteria_Server/logins.php?mobile_number=" + pars_mobile_number
                    + "&password=" + password_;
            // concatenated the values to the api url to send the data to it

            DownloadTask task = new DownloadTask();
            task.execute(url);

            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    // when the TextView (Not a member yet? Sign up here.) is clicked, the signup page will appear
    public void goSignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}