package com.damianwynter.jusbuss.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Housing;

public class ViewHousingInfo extends AppCompatActivity {
    private TextView tvFname,tvLname,tvPhone, tvAddress,tvGender, tvUtility,
            tvDescription, tvStatus;
    private TextView tvPrice, tvLongitude,tvLatitude,
            tvOccupancy, tvVacancy;
    private Housing house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_housing_info);
        getViewIDs();
         house = (Housing) getIntent().getSerializableExtra("housing");
        populateView();
    }
    private void populateView(){
        tvFname.setText(house.getFname()+" "+house.getLname());
        //tvLname.setText(house.getLname());
        tvAddress.setText(house.getAddress());
        tvPhone.setText(house.getPhone());
        tvDescription.setText(house.getDescription());
        tvVacancy.setText(String.valueOf(house.getVacancy()));
        tvPrice.setText("$"+String.valueOf(house.getPrice()));
       // tvLatitude.setText(String.valueOf(house.getLatitude()));
        tvLongitude.setText(String.valueOf(house.getLongitude())+" : "+String.valueOf(house.getLatitude()));
        tvOccupancy.setText(String.valueOf(house.getNumOccupancy()));
        //String temp = house.getStatus().toLowerCase();
        tvStatus.setText(house.getStatus());

    }
    private void getViewIDs(){
        tvFname = (TextView) findViewById(R.id.housing_fname);
        //tvLname = (TextView) findViewById(R.id.housing_lname);
        tvPhone = (TextView) findViewById(R.id.housing_phone);
        tvAddress = (TextView) findViewById(R.id.housing_address);
        tvDescription = (TextView) findViewById(R.id.housing_desc);
        tvStatus = (TextView) findViewById(R.id.housing_status);
        tvPrice = (TextView) findViewById(R.id.housing_price);
        tvLongitude = (TextView) findViewById(R.id.housing_long);
       // tvLatitude = (TextView) findViewById(R.id.housing_lat);
        tvOccupancy = (TextView) findViewById(R.id.housing_numOccupancy);
        tvVacancy = (TextView) findViewById(R.id.housing_vacancy);
    }
}
