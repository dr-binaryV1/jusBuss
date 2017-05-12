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
import com.damianwynter.jusbuss.adapters.FacultyListAdapter;
import com.damianwynter.jusbuss.activity.AddFacultyActivity;

public class FacultyListFragment extends Fragment{
    public interface OnFacultySelectedInterface {
        void onListFacultySelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OnFacultySelectedInterface listener = (OnFacultySelectedInterface) getActivity();
        View view = inflater.inflate(R.layout.faculty_fragment_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.facultyFab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),AddFacultyActivity.class));
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.faculty_listRecyclerView);
        FacultyListAdapter facultyListAdapter = new FacultyListAdapter(listener);
        recyclerView.setAdapter(facultyListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
