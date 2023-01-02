package com.example.mulhimvr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Services extends AppCompatActivity {
    Button delete,Enter,clear,close,update,read,back;
    RadioButton VR,AR,MR;
    EditText NameOfCompany,ServiceID, phone;
    MulhimVR_Database VRDb;
    TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services2);
        VRDb = new MulhimVR_Database(this);
        delete=(Button) findViewById(R.id.DELET_Service);
        Enter=(Button) findViewById(R.id.Ent_Service);
        clear=(Button) findViewById(R.id.CLAER_Service);
        close=(Button) findViewById(R.id.CLOSE_Service);
        update=(Button) findViewById(R.id.UPDATE_Service);
        read=(Button)findViewById(R.id.READ_Service);
        back=(Button)findViewById(R.id.Back_Service);

        VR=(RadioButton) findViewById(R.id.Service1);
        AR=(RadioButton) findViewById(R.id.Service2);
        MR=(RadioButton) findViewById(R.id.Service3);

        NameOfCompany=(EditText)findViewById(R.id.Clients);
        ServiceID=(EditText)findViewById(R.id.ID_service);
        phone=(EditText)findViewById(R.id.PNumber);

        Description=(TextView)findViewById(R.id.Des);


        addData();
        updateData();
        deleteData();
        viewData();
        clearData();
        closeApp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackToOption = new Intent(Services.this,Options.class);
                startActivity(BackToOption);

            }
        });

    }

    //this function will add the data to the database
    public void addData() {
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set the demo type and price based on the selected radio button
                String demo= "";


                if(VR.isChecked()==true)
                {
                    demo = "VR";
                    Description.setText("Virtual Reality, or VR, is the use of computer technology to create a simulated environment which can be explored in 360 degrees. Unlike traditional interfaces, VR places the user inside the virtual environment to give an immersive experience");
                }

                else if (MR.isChecked()==true)
                {
                    demo= "MR";
                    Description.setText("Mixed reality is a blend of physical and digital worlds, unlocking natural and intuitive 3D human, computer, and environmental interactions.");
                }

                else if (AR.isChecked()==true)
                {
                    demo= "AR";
                    Description.setText("Augmented reality is an interactive experience that enhances the real world with computer-generated perceptual information");
                }


                //insert data to the data base and check if it is entered or not
                boolean
                        insert=VRDb.AddData(demo, Integer.parseInt(phone.getText().toString()),NameOfCompany.getText().toString());

                //display toast base on the results that come from insert value
                if(insert==true)
                    Toast.makeText(Services.this, "Data Inserted",Toast.LENGTH_LONG).show();

                else

                    Toast.makeText(Services.this,"Data not inserted",Toast.LENGTH_LONG).show();

            }
        });
    }

    //this function will update the data on the database based on the id of the demo
    public void updateData() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set the demo based on the radio button clicked
                String demo= "";


                if(VR.isChecked()==true)
                {
                    demo = "VR";
                    Description.setText("Virtual Reality, or VR, is the use of computer technology to create a simulated environment which can be explored in 360 degrees. Unlike traditional interfaces, VR places the user inside the virtual environment to give an immersive experience");
                }

                else if (MR.isChecked()==true)
                {
                    demo= "MR";
                    Description.setText("Mixed reality is a blend of physical and digital worlds, unlocking natural and intuitive 3D human, computer, and environmental interactions.");
                }

                else if (AR.isChecked()==true)
                {
                    demo= "AR";
                    Description.setText("Augmented reality is an interactive experience that enhances the real world with computer-generated perceptual information");
                }

                //send the data to update function adn check the result
                boolean insert=VRDb.updateData(ServiceID.getText().toString(),demo,Integer.parseInt(phone.getText().toString()),NameOfCompany.getText().toString());

                //display toast according to the result that comes from insert value
                if(insert==true)
                    Toast.makeText(Services.this, "Data updated",Toast.LENGTH_LONG).show();

                else

                    Toast.makeText(Services.this,"Data not updated",Toast.LENGTH_LONG).show();

            }
        });
    }

    //this function will delete a record based in its id
    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send id and check the result if the record is deleted or not
                Integer del=VRDb.deleteData(ServiceID.getText().toString());
                if(del>0)
                    Toast.makeText(Services.this,"Data deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Services.this,"Dat not deleted",Toast.LENGTH_LONG).show();
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
                phone.setText("");
                ServiceID.setText("");
                NameOfCompany.setText("");
                VR.setChecked(false);
                MR.setChecked(false);
                AR.setChecked(false);
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