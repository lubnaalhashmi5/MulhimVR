package com.example.mulhimvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText Login, Password;
    Button Submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = (EditText)findViewById(R.id.UserN);
        Password =(EditText) findViewById(R.id.Pass);

        Submit = (Button) findViewById(R.id.LOGIN);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Login.getText().toString().isEmpty()||Password.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Please enter the data", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Options.class);
                    startActivity(intent);
                }
            }
        });
    }
}