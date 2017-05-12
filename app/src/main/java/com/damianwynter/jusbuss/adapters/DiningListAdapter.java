package com.damianwynter.jusbuss.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Dining;
import com.damianwynter.jusbuss.ui.DiningActivity;
import com.damianwynter.jusbuss.ui.DiningListFragment;

public class DiningListAdapter extends RecyclerView.Adapter {
    private final DiningListFragment.OnFoodShopSelectedInterface mListener;
    private Dining[] mDinings = DiningActivity.mDinings;

    public DiningListAdapter(DiningListFragment.OnFoodShopSelectedInterface listener){
        mListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dining_list_item, parent, false);

        return new DiningListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DiningListAdapter.ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mDinings.length;
    }


    /**
     * NEW CLASS
     */
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameTextView;
        private TextView mDescTextView;
        private TextView mAddressTextView;
        private int mIndex;

        public ListViewHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            mDescTextView = (TextView) itemView.findViewById(R.id.descTextView);
            mAddressTextView = (TextView) itemView.findViewById(R.id.addressTextView);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            mIndex = position;
            mNameTextView.setText(mDinings[position].getName());
            mDescTextView.setText(mDinings[position].getDescription());
            mAddressTextView.setText(mDinings[position].getAddress());
        }

        @Override
        public void onClick(View view) {
            mListener.onListFoodShopSelected(mIndex);
        }
    }
}
