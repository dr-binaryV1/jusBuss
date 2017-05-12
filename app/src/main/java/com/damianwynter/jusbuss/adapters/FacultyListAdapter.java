package com.damianwynter.jusbuss.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.ui.FacultyActivity;
import com.damianwynter.jusbuss.ui.FacultyListFragment;
import com.damianwynter.jusbuss.ui.MainActivity;
import com.damianwynter.jusbuss.models.Faculty;

public class FacultyListAdapter extends RecyclerView.Adapter{
    private final FacultyListFragment.OnFacultySelectedInterface mListener;
    private Faculty[] mFaculties = FacultyActivity.mFaculties;

    public FacultyListAdapter(FacultyListFragment.OnFacultySelectedInterface listener){
        mListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_list_item, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mFaculties.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameTextView;
        private TextView mDescTextView;
        private Double mLongitude;
        private Double mLatitude;
        private int mIndex;

        public ListViewHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            mDescTextView = (TextView) itemView.findViewById(R.id.descTextView);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            mIndex = position;
            mNameTextView.setText(mFaculties[position].getName());
            mDescTextView.setText(mFaculties[position].getDescription());
            mLongitude = mFaculties[position].getLongitude();
            mLatitude = mFaculties[position].getLatitude();
        }

        @Override
        public void onClick(View view) {
            mListener.onListFacultySelected(mIndex);
        }
    }
}
