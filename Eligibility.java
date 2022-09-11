package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bloodbank.R;

public class Eligibility extends AppCompatActivity implements View.OnClickListener {
    Button continueBtn;
    CheckBox confirmChk;
    CheckBox ageBtn,ailmentBtn,timeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);
        ageBtn=(CheckBox) findViewById(R.id.age_chk);
        ailmentBtn=(CheckBox) findViewById(R.id.ailmnt_chk);
        timeBtn=(CheckBox) findViewById(R.id.time_chk);
        confirmChk=(CheckBox) findViewById(R.id.confirm_chk);
        continueBtn=(Button) findViewById(R.id.btn_continue);
        continueBtn.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {

        if(ageBtn.isChecked() && ailmentBtn.isChecked()==false && timeBtn.isChecked()){
            if(confirmChk.isChecked()) {
                continueBtn.setEnabled(true);
                startActivity(new Intent(Eligibility.this, RegisterActivity.class));
            }
            else{
                Toast.makeText(getBaseContext(),"Please confirm the validity of the information!",Toast.LENGTH_LONG).show();
            }
        }
        else if(confirmChk.isChecked()){
            continueBtn.setEnabled(true);
            Toast.makeText(getBaseContext(),"Sorry. You are not eligible to donate blood.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Eligibility.this, SplashScreen.class));
        }
        else{
            continueBtn.setEnabled(false);
        }
    }
}