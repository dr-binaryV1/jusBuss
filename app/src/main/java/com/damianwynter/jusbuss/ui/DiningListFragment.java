package com.damianwynter.jusbuss.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.adapters.DiningListAdapter;
import com.damianwynter.jusbuss.activity.AddDiningActivity;

public class DiningListFragment extends Fragment{
    public interface OnFoodShopSelectedInterface {
        void onListFoodShopSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DiningListFragment.OnFoodShopSelectedInterface listener = (DiningListFragment.OnFoodShopSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.fragment_dining_list, container, false);

        FloatingActionButton mFab = (FloatingActionButton) view.findViewById(R.id.diningFab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getContext(), AddDiningActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dining_listRecyclerView);
        DiningListAdapter diningListAdapter = new DiningListAdapter(listener);
        recyclerView.setAdapter(diningListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
