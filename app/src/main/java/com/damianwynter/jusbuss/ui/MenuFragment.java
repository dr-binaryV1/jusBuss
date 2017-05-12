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
import com.damianwynter.jusbuss.adapters.MenuListAdapter;

public class MenuFragment extends Fragment {
    public interface OnMenuSelectedInterface {
        void onListMenuSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_MENU_INDEX);
        MenuFragment.OnMenuSelectedInterface listener = (OnMenuSelectedInterface) getContext();
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.menu_listRecyclerView);
        MenuListAdapter menuListAdapter = new MenuListAdapter(listener, index);
        recyclerView.setAdapter(menuListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
