package com.example.bloodbank.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bloodbank.R;

public class SearchBlood extends Activity {

    private Button[] btn = new Button[4];
    private Button[] btnSign=new Button[2];
    private Button btn_unfocus,btn_sign_unfocus;
    private int[] btn_id = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3};
    private int[] btn_id2={R.id.btnPlus,R.id.btnMinus};
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setBackgroundResource(R.drawable.gray_box_round);
            btn_unfocus = btn[0];

            btn[i].setOnClickListener(view -> {
                switch (view.getId()){
                    case R.id.btn0 :
                        setFocus(btn_unfocus, btn[0]);
                        break;

                    case R.id.btn1 :
                        setFocus(btn_unfocus, btn[1]);
                        break;

                    case R.id.btn2 :
                        setFocus(btn_unfocus, btn[2]);
                        break;

                    case R.id.btn3 :
                        setFocus(btn_unfocus, btn[3]);
                        break;
                }
            });
        }
        for(int i = 0; i < btnSign.length; i++){
            btnSign[i] = (Button) findViewById(btn_id2[i]);
            btnSign[i].setBackgroundResource(R.drawable.gray_box_round);
            btn_sign_unfocus=btnSign[0];
            btnSign[i].setOnClickListener(view -> {
                switch (view.getId()){
                    case R.id.btnPlus:
                        setSignFocus(btn_sign_unfocus,btnSign[0]);
                        break;
                    case R.id.btnMinus:
                        setSignFocus(btn_sign_unfocus,btnSign[1]);
                        break;
                }
            });
        }
        nextBtn=(Button) findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(view -> {
            if(true){
                String text=btn_unfocus.getText().toString()+btn_sign_unfocus.getText().toString();
                Bundle b= new Bundle();
                b.putString("bldgrp",text);
                Intent i = new Intent(SearchBlood.this,FindDonor.class);
                i.putExtra("data",b);

                //Toast.makeText(getBaseContext(),text,Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
            else{
                Toast.makeText(getBaseContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundResource(R.drawable.gray_box_round);
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundResource(R.drawable.button_bg);
        this.btn_unfocus = btn_focus;
    }
    private void setSignFocus(Button btn_sign_unfocus, Button btn_focus){
        btn_sign_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_sign_unfocus.setBackgroundResource(R.drawable.gray_box_round);
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundResource(R.drawable.button_bg);
        this.btn_sign_unfocus=btn_focus;
    }

}