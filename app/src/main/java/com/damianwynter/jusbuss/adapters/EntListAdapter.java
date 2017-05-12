package com.damianwynter.jusbuss.adapters;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Entertainment;
import com.damianwynter.jusbuss.ui.EntListFragment;
import com.damianwynter.jusbuss.activity.EntertainmentActivity;

public class EntListAdapter extends RecyclerView.Adapter {
    private final EntListFragment.OnEntertainmentSelectedInterface mListener;
    private Entertainment[] mEnt = EntertainmentActivity.mEntertainments;


    public EntListAdapter(EntListFragment.OnEntertainmentSelectedInterface listener){
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ent_list_item, parent, false);

        return new EntListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EntListAdapter.ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mEnt.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mName;
        private TextView mCategory;
        private TextView mTel;
        private TextView mAddress;
        private TextView mTickedSoldAt;
        private TextView mTime;
        private TextView mDate;
        private TextView mAdmission;
        private int mIndex;
        private ImageButton dialer;

        public ListViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.nameTextView);
            mAddress = (TextView) itemView.findViewById(R.id.addressTextView);
            mTel = (TextView) itemView.findViewById(R.id.telTextView);
            mAdmission = (TextView) itemView.findViewById(R.id.admissionTextView);
            mDate = (TextView) itemView.findViewById(R.id.dateTextView);
            mTime = (TextView) itemView.findViewById(R.id.timeTextView);
            mTickedSoldAt = (TextView) itemView.findViewById(R.id.ticketTextView);
            dialer = (ImageButton) itemView.findViewById(R.id.telImageButton);
        }

        public void bindView(final int position){
            mName.setText(mEnt[position].getName());
            mDate.setText(mEnt[position].getDate());
            mTime.setText(mEnt[position].getTime());
            mAddress.setText(mEnt[position].getAddress());
            if(mEnt[position].getAdmission().equals("0")){
                mAdmission.setText("Admission: Free");
            } else {
                mAdmission.setText("Admission: $"+ mEnt[position].getAdmission());
            }
            mTel.setText(mEnt[position].getTel());
            mTickedSoldAt.setText("Tickets Sold At: " + mEnt[position].getTicket_sold_at());
            mIndex = position;

            dialer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+mEnt[position].getTel()));
                    v.getContext().startActivity(intent);
                }
            });



        }

        @Override
        public void onClick(View view) {
            mListener.OnListEntSelected(mIndex);
        }
    }
}
