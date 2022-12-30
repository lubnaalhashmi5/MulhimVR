package com.example.mulhimvr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Options extends AppCompatActivity {

    Button Demo, Service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Demo = (Button)findViewById(R.id.DEMO);
        Service = (Button)findViewById(R.id.SERVICE);

    }
}