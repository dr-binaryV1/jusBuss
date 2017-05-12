package com.damianwynter.jusbuss.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.activity.DiningActivity;

public class ViewPagerFragment extends Fragment{
    public static final String KEY_DINING_INDEX = "dining_index";
    public static final String KEY_MENU_INDEX = "menu_index";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_DINING_INDEX);
        getActivity().setTitle(DiningActivity.mDinings[index].getName());
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        final RestaurantFragment restaurantFragment = new RestaurantFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DINING_INDEX, index);
        restaurantFragment.setArguments(bundle);

        final MenuFragment menuFragment = new MenuFragment();
        bundle = new Bundle();
        bundle.putInt(KEY_MENU_INDEX, index);
        menuFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0? restaurantFragment : menuFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0? "Restaurant Profile" : "Menu";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle("JusBuss - Navigation App");
    }
}
