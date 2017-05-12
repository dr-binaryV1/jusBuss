package com.damianwynter.jusbuss.ui;

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
    ImageView mFaculty;
    ImageView mFoodshop;


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
        mFoodshop = (ImageView) findViewById(R.id.foodImageButton);

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
        mFoodshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DiningActivity.class);
                startActivity(intent);
            }
        });

    }
}
