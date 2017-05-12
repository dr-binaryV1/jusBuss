package com.damianwynter.jusbuss.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.activity.GeneralActivity;
import com.damianwynter.jusbuss.models.GeneralBAF;
import com.damianwynter.jusbuss.ui.GeneralListFragment;

/**
 * Created by infinity on 5/12/2017.
 */

public class GeneralListAdapter extends RecyclerView.Adapter {
    private GeneralBAF[] aGeneral = GeneralActivity.aData;
    private final GeneralListFragment.OnGeneralSelectedItem aListener;
    public GeneralListAdapter(GeneralListFragment.OnGeneralSelectedItem l){
        aListener = l;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.general_list_item, parent, false);
        return new GeneralListAdapter.ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GeneralListAdapter.ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return aGeneral.length;
    }


    /**
     * NEW CLASS
     */
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView aName;
        private TextView aOpenClose;
        private TextView aLocation;
        private int mIndex;

        public ListViewHolder(View itemView){
            super(itemView);
            aName = (TextView) itemView.findViewById(R.id.general_name);
            aOpenClose = (TextView) itemView.findViewById(R.id.general_open_closeTV);
            aLocation = (TextView) itemView.findViewById(R.id.general_location);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            mIndex = position;
            aName.setText(aGeneral[position].getaName());
            aOpenClose.setText(aGeneral[position].getaDescription());
            aLocation.setText(aGeneral[position].getaOpenTime()+"am - "+aGeneral[position].getaCloseTime()+"pm");
        }
        @Override
        public void onClick(View view) {
            aListener.onListSelected(mIndex);
        }
    }
}
