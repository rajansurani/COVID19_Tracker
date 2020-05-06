package com.rajansurani.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rajansurani.covid19tracker.Model.District;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DistrictDataAdapter extends RecyclerView.Adapter<DistrictDataAdapter.MyViewHolder> {

    Context mContext;
    List<District> mDistrictList;

    public DistrictDataAdapter(Context context, List<District> districtDataList) {
        mContext = context;
        mDistrictList = districtDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder (LayoutInflater.from (mContext).inflate (R.layout.card_district_data,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        District currentStatus = mDistrictList.get (position);
        holder.mDistrictName.setText (currentStatus.getDistrict ());
        holder.mConfirmedCount.setText (String.valueOf (currentStatus.getConfirmed ()));

    }


    @Override
    public int getItemCount() {
        return mDistrictList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mDistrictName, mConfirmedCount;

        public MyViewHolder(@NonNull View itemView) {
            super (itemView);
            mDistrictName = itemView.findViewById (R.id.card_district_name);
            mConfirmedCount = itemView.findViewById (R.id.card_district_count);
        }
    }
}
