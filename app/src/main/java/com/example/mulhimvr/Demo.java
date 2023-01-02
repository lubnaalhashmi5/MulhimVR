package com.example.mulhimvr;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Demo extends AppCompatActivity {

    //declare the components to the variable
    EditText ID, demoDay, NameOfCompany;
    Button delete, calculate, clear, close, update, read, back;
    TextView Price;
    RadioButton MRA, platform;
    MulhimVR_Database VRDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        //assign the values to the components
        VRDb = new MulhimVR_Database(this);
        delete = (Button) findViewById(R.id.DELET_Demo);
        calculate = (Button) findViewById(R.id.Cal_Demo);
        clear = (Button) findViewById(R.id.CLAER_Demo);
        close = (Button) findViewById(R.id.CLOSE_Demo);
        update = (Button) findViewById(R.id.UPDATE_Demo);
        read = (Button) findViewById(R.id.READ_Demo);
        back = (Button) findViewById(R.id.Back_Demo);

        Price = (TextView) findViewById(R.id.cost);

        ID = (EditText) findViewById(R.id.ID);
        demoDay = (EditText) findViewById(R.id.DAY);
        NameOfCompany = (EditText) findViewById(R.id.Clients);

        MRA = (RadioButton) findViewById(R.id.Product2);
        platform = (RadioButton) findViewById(R.id.Product1);

        //declare the function
        addData();
        updateData();
        deleteData();
        viewData();
        clearData();
        closeApp();

        // return the option page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackToOption = new Intent(Demo.this,Options.class);
                startActivity(BackToOption);

            }
        });

    }

    //this function will add the data to the database
    public void addData() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set the demo type and price based on the selected radio button
                String demo;
                int price , demoprice;

                if(platform.isChecked()==true)
                {
                    demo = "MulhimVR platform";
                    price=5;
                }

                else if (MRA.isChecked()==true);
                {
                    demo= "Mixed Reality App";
                    price= 3;
                }

                //calculate the demo price based on the number of days and product and display the price
                demoprice = price*Integer.parseInt(demoDay.getText().toString());
                Price.setText("The demo price is: "+ demoprice);

                //insert data to the data base and check if it is entered or not
                boolean
                        insert=VRDb.AddData(demo, Integer.parseInt(demoDay.getText().toString()),NameOfCompany.getText().toString());

                //display toast base on the results that come from insert value
                if(insert==true)
                    Toast.makeText(Demo.this, "Data Inserted",Toast.LENGTH_LONG).show();

                else

                    Toast.makeText(Demo.this,"Data not inserted",Toast.LENGTH_LONG).show();

            }
        });
    }

     //this function will update the data on the database based on the id of the demo
    public void updateData() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set the demo based on the radio button clicked
               String demo;

                if(platform.isChecked()==true)
                {
                    demo = "MulhimVR platform";
                }

                else if (MRA.isChecked()==true);
                {
                    demo= "Mixed Reality App";
                }

                //send the data to update function adn check the result
                boolean insert=VRDb.updateData(ID.getText().toString(),demo,Integer.parseInt(demoDay.getText().toString()),NameOfCompany.getText().toString());

                //display toast according to the result that comes from insert value
                if(insert==true)
                    Toast.makeText(Demo.this, "Data updated",Toast.LENGTH_LONG).show();

                else

                    Toast.makeText(Demo.this,"Data not updated",Toast.LENGTH_LONG).show();

            }
        });
    }

    //this function will delete a record based in its id
    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send id and check the result if the record is deleted or not
                Integer del=VRDb.deleteData(ID.getText().toString());
                if(del>0)
                    Toast.makeText(Demo.this,"Data deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Demo.this,"Dat not deleted",Toast.LENGTH_LONG).show();
            }
        });

    }

    //this function will return all data from database's table and display it
    public void viewData(){
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create cursor object
                Cursor Lu=VRDb.getAllData();

                //check if no records there
                if(Lu.getCount()==0)
                {
                    ShowMSG("Error", "Nothing found");
                    return;
                }

                //read data and display it
                StringBuffer BF=new StringBuffer();
                while(Lu.moveToNext())
                {
                    BF.append("ID:"+Lu.getString(0)+"\n");
                    BF.append("Type of Demo/Service:"+Lu.getString(1)+"\n");
                    BF.append("Number of day/phone NO.:"+Lu.getString(2)+"\n");
                    BF.append("Client Name:"+Lu.getString(3)+"\n");
                }
                ShowMSG("Student Details",BF.toString());
            }
        });
    }

    //this function will clear all the data enterd in the editText field and radio button selection
    public void clearData(){
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID.setText("");
                demoDay.setText("");
                NameOfCompany.setText("");
                platform.setChecked(false);
                MRA.setChecked(false);
            }
        });
    }

    //this function will be used to show the result in view data function
    public void ShowMSG(String heading,String Message)
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(heading);
        ad.setMessage(Message);
        ad.show();
    }


    //this function will close the applications
    public void closeApp(){
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });
    }


}
