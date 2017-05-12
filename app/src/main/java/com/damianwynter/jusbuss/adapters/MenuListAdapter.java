package com.damianwynter.jusbuss.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.activity.DiningActivity;
import com.damianwynter.jusbuss.ui.MenuFragment;

public class MenuListAdapter extends RecyclerView.Adapter {
    private final MenuFragment.OnMenuSelectedInterface mListener;
    private final int mIndex;

    public MenuListAdapter(MenuFragment.OnMenuSelectedInterface listener, int index) {
        mListener = listener;
        mIndex = index;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_menu, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int size = DiningActivity.mFoodMenus[mIndex].length;
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return DiningActivity.mFoodMenus[mIndex].length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mDescTextView;
        private TextView mRegPriceTextView;
        private TextView mMedPriceTextView;
        private TextView mLrgPriceTextView;

        public ListViewHolder(View view) {
            super(view);

            mNameTextView = (TextView) view.findViewById(R.id.nameTextView);
            mDescTextView = (TextView) view.findViewById(R.id.descTextView);
            mRegPriceTextView = (TextView) view.findViewById(R.id.regPriceTextView);
            mMedPriceTextView = (TextView) view.findViewById(R.id.medPriceTextView);
            mLrgPriceTextView = (TextView) view.findViewById(R.id.lrgPriceTextView);

            view.setOnClickListener(this);
        }

        public void bindView(int position){
            mNameTextView.setText(DiningActivity.mFoodMenus[mIndex][position].getName());
            mDescTextView.setText(DiningActivity.mFoodMenus[mIndex][position].getDescription());

            int size = DiningActivity.mFoodSizes[mIndex].length;

            mRegPriceTextView.setText("$" + DiningActivity.mFoodSizes[mIndex][0].getPrice() + "");
            if(size >= 2){
                mMedPriceTextView.setText("$" + DiningActivity.mFoodSizes[mIndex][1].getPrice() + "");
            } else {
                mMedPriceTextView.setText("N/A");
            }
            if(size >= 3){
                mLrgPriceTextView.setText("$" + DiningActivity.mFoodSizes[mIndex][2].getPrice() + "");
            } else {
                mLrgPriceTextView.setText("N/A");
            }
        }

        @Override
        public void onClick(View view) {
            mListener.onListMenuSelected(mIndex);
        }
    }
}
