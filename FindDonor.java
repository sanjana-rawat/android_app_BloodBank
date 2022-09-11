package com.example.bloodbank.Activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.bloodbank.databinding.ActivityFindDonorBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindDonor extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private ActivityFindDonorBinding binding;
    private Geocoder geocoder;
    private  LatLng addressll;
    private LatLng addressll2;
    private ArrayList<LatLng> locations;
    DBConnection db = new DBConnection(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println(locNo);
        binding = ActivityFindDonorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder=new Geocoder(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<LatLng> locations =new ArrayList<LatLng>();
        ArrayList<String> phnos =new ArrayList<String>();

        //Database Attempt
        String bldgrp=getIntent().getBundleExtra("data").getString("bldgrp").toString();
        //Toast.makeText(FindDonor.this,"lol"+bldgrp,Toast.LENGTH_SHORT).show();
        List<Address> addresses;
//        List<String> phones;
        Address  address;
//        String phone;
        Cursor res= db.getData();
        Cursor res1= db.getData();
        Cursor res2=db.getData();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {

            buffer.append(res.getString(2));
            if(!res.isLast()){
                buffer.append(",");
            }
            else{
                break;
            }

        }
        StringBuffer buffer1 = new StringBuffer();
        while(res1.moveToNext())
        {
            buffer1.append(res1.getString(4));
            if(!res1.isLast()){
                buffer1.append(",");
            }
            else{
                break;
            }

        }
        StringBuffer buffer2 = new StringBuffer();
        while(res2.moveToNext())
        {
            buffer2.append(res2.getString(3));
            if(!res2.isLast()){
                buffer2.append(",");
            }
            else{
                break;
            }

        }

        String str= buffer.toString();
        String[] addressList = str.split(",");

        String str1= buffer1.toString();
        String[] bloodList= str1.split(",");

        String str2= buffer2.toString();
        String[] phnList= str2.split(",");
//        Toast.makeText(FindDonor.this,bloodList[0],Toast.LENGTH_SHORT).show();
        for(int i= 0;i<addressList.length;i++){
            try{
                if(bloodList[i].equals(bldgrp))
                {
                    addresses = geocoder.getFromLocationName(addressList[i], 1);
                    address = addresses.get(0);
                    addressll2 = new LatLng(address.getLatitude(), address.getLongitude());
                    locations.add(addressll2);
                    phnos.add(phnList[i]);

                }
                if(locations.isEmpty())
                {
                    Toast.makeText(FindDonor.this,bldgrp+" is not available",Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        for(int i=0;i<locations.size();i++)
        {
            mMap.addMarker(new MarkerOptions().position(locations.get(i)).title("Blood Group :"+bldgrp).snippet("PhoneNo:"+phnos.get(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locations.get(i)));
        }
    }
}