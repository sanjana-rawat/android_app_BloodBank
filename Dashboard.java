package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bloodbank.R;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    Button donateBlood,searchBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        donateBlood=(Button) findViewById(R.id.btn_donate);
        donateBlood.setOnClickListener(this);
        searchBlood=(Button) findViewById(R.id.btn_receive);
        searchBlood.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(donateBlood)){
            startActivity(new Intent(Dashboard.this, Eligibility.class));
        }
        if(view.equals(searchBlood)){
            startActivity(new Intent(Dashboard.this, SearchBlood.class));
        }
    }
}