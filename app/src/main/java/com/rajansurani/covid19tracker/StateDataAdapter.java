package com.rajansurani.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rajansurani.covid19tracker.Model.StateData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StateDataAdapter extends RecyclerView.Adapter<StateDataAdapter.MyViewHolder> {

    Context mContext;
    List<StateData> mStateDataList;

    private OnItemClickListener onItemClickListener;


    public StateDataAdapter(Context context, List<StateData> stateDataList) {
        mContext = context;
        mStateDataList = stateDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from (mContext).inflate (R.layout.card_data,parent,false);
        return new MyViewHolder (view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StateData currentStatus = mStateDataList.get (position);
        holder.mStateName.setText (currentStatus.getState ());
        holder.mLastUpdate.setText (currentStatus.getLastupdatedtime ());
        holder.mStatusConfirmedCount.setText (String.valueOf (currentStatus.getConfirmed ()));
        holder.mStatusActiveCount.setText (String.valueOf (currentStatus.getActive ()));
        holder. mStatusRecoveredCount.setText (String.valueOf (currentStatus.getRecovered ()));
        holder.mStatusDeceasedCount.setText (String.valueOf (currentStatus.getDeaths ()));

        holder.mStatusActiveCountInc.setText ("+"+(currentStatus.getDeltaconfirmed ()-currentStatus.getDeltadeaths ()-currentStatus.getDeltarecovered ()));
        holder.mStatusConfirmedCountInc.setText ("+"+currentStatus.getDeltaconfirmed ());
        holder.mStatusDeceasedCountInc.setText ("+"+currentStatus.getDeltadeaths ());
        holder.mStatusRecoveredCountInc.setText ("+"+currentStatus.getDeltarecovered ());

    }

    @Override
    public int getItemCount() {
        return mStateDataList.size ();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView mStateName, mLastUpdate, mStatusConfirmedCount, mStatusActiveCount, mStatusRecoveredCount, mStatusDeceasedCount;
        TextView mStatusConfirmedCountInc, mStatusActiveCountInc, mStatusRecoveredCountInc, mStatusDeceasedCountInc;

        OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super (itemView);
            itemView.setOnClickListener (this);
            mStateName = itemView.findViewById (R.id.card_state_name);
            mLastUpdate = itemView.findViewById (R.id.card_last_update_time);
            mStatusConfirmedCount = itemView.findViewById (R.id.card_status_confirmed_count);
            mStatusActiveCount = itemView.findViewById (R.id.card_status_active_count);
            mStatusRecoveredCount = itemView.findViewById (R.id.card_status_recovered_count);
            mStatusDeceasedCount = itemView.findViewById (R.id.card_status_deceased_count);
            mStatusConfirmedCountInc = itemView.findViewById (R.id.card_status_confirmed_increase_count);
            mStatusActiveCountInc = itemView.findViewById (R.id.card_status_active_increase_count);
            mStatusRecoveredCountInc = itemView.findViewById (R.id.card_status_recovered_increase_count);
            mStatusDeceasedCountInc = itemView.findViewById (R.id.card_status_deceased_increase_count);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v){
            onItemClickListener.onItemClick (v,getAdapterPosition ());
        }
    }
}
