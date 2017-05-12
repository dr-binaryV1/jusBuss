package com.damianwynter.jusbuss.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.adapters.GroceryListAdapter;

/**
 * Created by infinity on 5/10/2017.
 */

public class GroceryListFragment extends Fragment {
    public interface OnListGrocerySelectedInterface {
        void OnListGrocerySelected(int index);
    }
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OnListGrocerySelectedInterface listener = (OnListGrocerySelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.grocery_fragment_list, container, false);

        //final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.housingFab);

        recyclerView = (RecyclerView) view.findViewById(R.id.grocery_listRecyclerView);
        GroceryListAdapter listAdapter = new GroceryListAdapter(listener);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
