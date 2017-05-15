package com.damianwynter.jusbuss.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.activity.MapsActivity;
import com.damianwynter.jusbuss.models.Dining;
import com.damianwynter.jusbuss.activity.DiningActivity;

public class RestaurantFragment extends Fragment{
    private TextView mName;
    private TextView mDescription;
    private TextView mOpenHours;
    private TextView mAddress;
    private ImageView mMap;
    private ImageView mDialer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_DINING_INDEX);
        View view = inflater.inflate(R.layout.fragment_dining_profile, container, false);

        final Dining restaurant = DiningActivity.mDinings[index];

        mName = (TextView) view.findViewById(R.id.nameTextView);
        mDescription = (TextView) view.findViewById(R.id.descTextView);
        mOpenHours = (TextView) view.findViewById(R.id.openHoursTextView);
        mAddress = (TextView) view.findViewById(R.id.addressTextView);
        mDialer = (ImageView) view.findViewById(R.id.dialerImageView);
        mMap = (ImageView) view.findViewById(R.id.mapImageButton);

        mName.setText(restaurant.getName());
        mDescription.setText(restaurant.getDescription());
        mOpenHours.setText(restaurant.getOpenTime() + " AM" + " - " + restaurant.getCloseTime() + " PM");
        mAddress.setText(restaurant.getAddress());

        mDialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+restaurant.getTel()));
                v.getContext().startActivity(intent);
            }
        });

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra("longitude", restaurant.getLongitude());
                intent.putExtra("latitude", restaurant.getLatitude());
                intent.putExtra("title", restaurant.getName());
                startActivity(intent);
            }
        });

        return view;
    }
}
