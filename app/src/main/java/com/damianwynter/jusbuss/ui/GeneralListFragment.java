package com.damianwynter.jusbuss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.adapters.GeneralListAdapter;


/**
 * Created by infinity on 5/12/2017.
 */

public class GeneralListFragment extends Fragment{

    public interface OnGeneralSelectedItem{
        void onListSelected(int aIndex);
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GeneralListFragment.OnGeneralSelectedItem listener = (GeneralListFragment.OnGeneralSelectedItem) getActivity();
        View view = inflater.inflate(R.layout.fragment_general_list, container, false);



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.general_listRecyclerView);
        GeneralListAdapter listAdapter = new GeneralListAdapter(listener);
        recyclerView.setAdapter(listAdapter);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
