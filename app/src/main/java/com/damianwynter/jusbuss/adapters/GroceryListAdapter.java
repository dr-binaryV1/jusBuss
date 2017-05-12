package com.damianwynter.jusbuss.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Grocery;
import com.damianwynter.jusbuss.activity.GroceryActivity;
import com.damianwynter.jusbuss.ui.GroceryListFragment;

/**
 * Created by infinity on 5/10/2017.
 */

public class GroceryListAdapter extends RecyclerView.Adapter {
    private final GroceryListFragment.OnListGrocerySelectedInterface mlistener;
    private Grocery[] mGrocery = GroceryActivity.mGrocery;

    public GroceryListAdapter(GroceryListFragment.OnListGrocerySelectedInterface mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_list_item,parent,false);
        return new GroceryListAdapter.ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroceryListAdapter.ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mGrocery.length;
    }



    /**
     * NEW CLASS
     */
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mShopName;
        private TextView mShopOpenHrs;
        private TextView mShopAddress;
        private int mIndex;

        public ListViewHolder(View itemView) {
            super(itemView);
            mShopName = (TextView) itemView.findViewById(R.id.general_name);
            mShopOpenHrs = (TextView) itemView.findViewById(R.id.general_open_closeTV);
            mShopAddress = (TextView) itemView.findViewById(R.id.shopAddressTV);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mIndex = position;
            mShopName.setText(mGrocery[position].getName());
            mShopOpenHrs.setText("From: "+mGrocery[position].getOpenHours()+"am"+" To "+mGrocery[position].getCloseHours());
            mShopAddress.setText(mGrocery[position].getAddress());
        }

        @Override
        public void onClick(View v) {
            mlistener.OnListGrocerySelected(mIndex);
        }
    }
}
