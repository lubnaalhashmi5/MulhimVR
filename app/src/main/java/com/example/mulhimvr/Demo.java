package com.example.mulhimvr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Demo extends AppCompatActivity {

    EditText Day;
    Button Calculate,Submit,Clear,Update,Page;
    TextView  Price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Day =(EditText) findViewById(R.id.DAY);
        Calculate =(Button) findViewById(R.id.Cal);
        Submit =(Button) findViewById(R.id.SUBMIT_Demo);
        Clear =(Button) findViewById(R.id.CLEAR_Demo);
        Update =(Button) findViewById(R.id.UPDATE_Demo);
        Page =(Button) findViewById(R.id.NEXTPage);
        Price=(TextView) findViewById(R.id.cost);





    }
}