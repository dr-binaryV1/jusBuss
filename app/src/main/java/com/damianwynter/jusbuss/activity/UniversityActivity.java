package com.damianwynter.jusbuss.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.University;

public class UniversityActivity extends AppCompatActivity {
    TextView mName;
    TextView mDescription;
    TextView mAddress;
    TextView mOpenHours;
    ImageView mFaculty,mBuilding,mFoodShop, mClub, mAtm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        setTitle("JusBuss - University");

        University university = MainActivity.mUniversity;

        mName = (TextView) findViewById(R.id.nameTextView);
        mDescription = (TextView) findViewById(R.id.descTextView);
        mOpenHours = (TextView) findViewById(R.id.openHoursTextView);
        mAddress = (TextView) findViewById(R.id.addressTextView);
        mFaculty = (ImageView) findViewById(R.id.facultyImageView);
        mFoodShop = (ImageView) findViewById(R.id.foodImageButton);
        mBuilding = (ImageView) findViewById(R.id.buildingImageButton);
        mClub = (ImageView) findViewById(R.id.clubsImageButton);
        mAtm = (ImageView) findViewById(R.id.atmImageButton);

        mName.setText(university.getName());
        mDescription.setText(university.getDescription());
        mOpenHours.setText(university.getOpenTime() + " AM" + " - " + university.getCloseTime() + " PM");
        mAddress.setText(university.getAddress());

        mFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FacultyActivity.class);
                startActivity(intent);
            }
        });
        mFoodShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DiningActivity.class);
                startActivity(intent);
            }
        });

        mBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GeneralActivity.class);
                i.putExtra("data","buildings");
                startActivity(i);
            }
        });

        mAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GeneralActivity.class);
                i.putExtra("data","atms");
                startActivity(i);
            }
        });

        mClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GeneralActivity.class);
                i.putExtra("data","clubs");
                startActivity(i);
            }
        });

    }
}
