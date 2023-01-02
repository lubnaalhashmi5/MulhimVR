package com.example.mulhimvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    Button Demo, Service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Demo = (Button)findViewById(R.id.DEMO);
        Service = (Button)findViewById(R.id.SERVICE);

        Demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DEMO= new Intent(getApplicationContext(),Demo.class);
                startActivity(DEMO);


            }
        });

        Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent services= new Intent(getApplicationContext(),Demo.class);
                startActivity(services);


            }
        });

    }
}