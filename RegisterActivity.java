package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {
    private EditText nameEt,cityEt,bloodGroupET,passwordEt,mobileEt;
    private Button submitButton;
    private TextView txt;
    String phPattern="^\\d{10}$";
    String bgPattern="^[ABO][+-]$|^AB[+-]$";
    Matcher m1,m2;
    DBConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEt= findViewById(R.id.name);
        cityEt=findViewById(R.id.city);
        bloodGroupET=findViewById(R.id.blood_group);
        //passwordEt=findViewById(R.id.password);
        mobileEt=findViewById(R.id.number);
        submitButton=findViewById(R.id.btn_continue);
        //mapButton=findViewById(R.id.map_button);
        txt=findViewById(R.id.txt);
        db =new DBConnection(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,city,blood_group="-",password,mobile="-";
                name=nameEt.getText().toString();
                city=cityEt.getText().toString();
                //blood_group=bloodGroupET.getText().toString();

                //password=passwordEt.getText().toString();
                //mobile=mobileEt.getText().toString();
                Pattern mbNo=Pattern.compile(phPattern);
                m1=mbNo.matcher(mobileEt.getText().toString());
                if(m1.matches()==true) {
                    mobile=mobileEt.getText().toString();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Please Enter Valid Phone Number.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,RegisterActivity.class));
                }
                Pattern bGrp=Pattern.compile(bgPattern);
                m2=bGrp.matcher(bloodGroupET.getText().toString());
                if(m2.matches()==true) {
                    blood_group=bloodGroupET.getText().toString();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Please Enter Valid Blood Group.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,RegisterActivity.class));

                }

//                showMessage(name+"\n"+city+"\n"+blood_group+"\n"+password+"\n"+mobile);
                if(mobile=="-"||blood_group=="-") {
                    startActivity(new Intent(RegisterActivity.this,RegisterActivity.class));
                }
                else {
                    boolean result = db.register(name, city, mobile, blood_group);
                    if (result == true) {
                        Cursor res = db.getData();
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name:" + res.getString(1) + "\n");
                            buffer.append("City" + res.getString(2) + "\n");
                            buffer.append("PhNo:" + res.getString(3) + "\n");
                            buffer.append("Blood Group" + res.getString(4) + "\n");
                        }
//                    Toast.makeText(RegisterActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegisterActivity.this, "Thank you for registering!Your details have been saved.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, Dashboard.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "There was an error with the data provided", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void showMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
