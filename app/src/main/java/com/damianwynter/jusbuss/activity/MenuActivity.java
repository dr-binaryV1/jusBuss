package com.damianwynter.jusbuss.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.ui.MenuFragment;
import com.damianwynter.jusbuss.ui.ViewPagerFragment;

public class MenuActivity extends AppCompatActivity implements MenuFragment.OnMenuSelectedInterface {
    public static final String DINING_VIEWPAGER_FRAGMENT = "dining_viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        int index = intent.getIntExtra("Index", 0);

        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_DINING_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.menu_content, fragment, DINING_VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onListMenuSelected(int index) {

    }
}
