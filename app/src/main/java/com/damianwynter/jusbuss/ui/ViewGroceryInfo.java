package com.damianwynter.jusbuss.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Grocery;

public class ViewGroceryInfo extends AppCompatActivity {
    private TextView tvShopName, tvOpenHrs, tvAddress, tvLatLong, tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grocery_info);
        setTitle("JusBuss - Grocery");
        getViewID();
        populateView((Grocery) getIntent().getSerializableExtra("grocery"));
    }

    private void getViewID(){
        tvAddress = (TextView) findViewById(R.id.grocery_address);
        tvShopName = (TextView) findViewById(R.id.grocery_name);
        tvOpenHrs = (TextView) findViewById(R.id.grocery_open_close);
        tvLatLong = (TextView) findViewById(R.id.grocery_long_lat);
        tvDescription  = (TextView) findViewById(R.id.grocery_desc);
    }
    private void populateView(Grocery grocery){
        tvShopName.setText(grocery.getName());
        tvAddress.setText(grocery.getAddress());
        tvDescription.setText(grocery.getDescription());
        tvLatLong.setText(grocery.getLongitude()+" : "+grocery.getLatitude());
        tvOpenHrs.setText(grocery.getOpenHours()+"am to "+grocery.getCloseHours()+"pm");
    }
}
