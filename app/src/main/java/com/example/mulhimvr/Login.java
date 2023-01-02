package com.example.mulhimvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText Username, Password;
    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.UserN);
        Password =(EditText) findViewById(R.id.Pass);

        Login = (Button) findViewById(R.id.LOGIN);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Username.getText().toString().isEmpty()||Password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the data", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent Login = new Intent(getApplicationContext(), Options.class);
                    startActivity(Login);
                }
            }
        });
    }
}