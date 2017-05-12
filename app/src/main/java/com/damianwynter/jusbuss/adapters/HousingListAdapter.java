package com.damianwynter.jusbuss.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Housing;
import com.damianwynter.jusbuss.activity.HousingActivity;
import com.damianwynter.jusbuss.ui.HousingListFragment;


/**
 * Created by infinity on 5/8/2017.
 */

public class HousingListAdapter extends RecyclerView.Adapter {
    private final HousingListFragment.OnHousingSelectedInterface mListener;
    private Housing[] mHousings = HousingActivity.mHousings;
    public HousingListAdapter(HousingListFragment.OnHousingSelectedInterface l) {
        mListener = l;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.housing_list_item,parent, false);
        return new HousingListAdapter.ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HousingListAdapter.ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mHousings.length;
    }

    /**
     * NEW CLASS
     */
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mLandLord;
        private TextView mPhone;
        private TextView mAddress;
        private int mIndex;

        public ListViewHolder(View itemView) {
            super(itemView);
            mLandLord = (TextView) itemView.findViewById(R.id.general_name);
            mPhone = (TextView) itemView.findViewById(R.id.general_open_closeTV);
            mAddress = (TextView) itemView.findViewById(R.id.rentalAddressTV);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mIndex = position;
            mLandLord.setText(mHousings[position].getFname()+" "+mHousings[position].getLname());
            mPhone.setText(mHousings[position].getPhone());
            mAddress.setText(mHousings[position].getAddress());
        }

        @Override
        public void onClick(View v) {
            mListener.OnListHousingSelected(mIndex);
        }
    }
}
