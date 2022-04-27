package com.lau.spring2022.groceteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public void login(View view) {
        String mobile_number_ = mobile_number.getText().toString();
        String password_ = password.getText().toString();

        if(TextUtils.isEmpty(mobile_number_) || TextUtils.isEmpty(password_)){
            Toast.makeText(LoginActivity.this, "All Fields Required!", Toast.LENGTH_LONG).show();
        } else {
            //loginAccount(mobile_number_, password_);
        }
    }

    // when the TextView (Not a member yet? Sign up here.) is clicked, the signup page will appear
    public void goSignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }


}