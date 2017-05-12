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
import com.damianwynter.jusbuss.adapters.HousingListAdapter;

/**
 * Created by infinity on 5/9/2017.
 */

public class HousingListFragment extends Fragment{
    public interface OnHousingSelectedInterface {
        void OnListHousingSelected(int index);
    }
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HousingListFragment.OnHousingSelectedInterface listener = (OnHousingSelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.housing_fragment_list, container, false);

        //final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.housingFab);
        recyclerView = (RecyclerView) view.findViewById(R.id.housing_listRecyclerView);
        HousingListAdapter housingListAdapter = new HousingListAdapter(listener);
        recyclerView.setAdapter(housingListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
