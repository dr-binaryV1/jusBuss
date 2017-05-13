package com.damianwynter.jusbuss.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Faculty;
import com.damianwynter.jusbuss.ui.FacultyListFragment;

public class FacultyActivity extends AppCompatActivity implements FacultyListFragment.OnFacultySelectedInterface {
    public static final String FACULTY_LIST_FRAGMENT = "faculty_list_fragment";
    private static final String TAG = FacultyActivity.class.getSimpleName();
    public static Faculty[] mFaculties;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        mFaculties = MainActivity.mUniversity.getFaculties();
        setTitle("JusBuss - Faculties and Schools");

        FacultyListFragment savedFragment = (FacultyListFragment) getSupportFragmentManager()
                .findFragmentByTag(FACULTY_LIST_FRAGMENT);
        if(savedFragment == null) {
            FacultyListFragment fragment = new FacultyListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.faculty_content, fragment, FACULTY_LIST_FRAGMENT);
            fragmentTransaction.commit();
        }
    }



    @Override
    public void onListFacultySelected(int index) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("longitude", mFaculties[index].getLongitude());
        intent.putExtra("latitude", mFaculties[index].getLatitude());
        startActivity(intent);
    }
}
