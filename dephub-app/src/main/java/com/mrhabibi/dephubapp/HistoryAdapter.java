package com.mrhabibi.dephubapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by mrhabibi on 9/16/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<Pin> pins;
    private OnLongClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textviewName;
        public TextView textviewLatlng;
        public TextView textviewDate;

        public ViewHolder(View v) {
            super(v);
            textviewName = v.findViewById(R.id.textview_name);
            textviewLatlng = v.findViewById(R.id.textview_latlng);
            textviewDate = v.findViewById(R.id.textview_date);
        }
    }

    public HistoryAdapter(OnLongClickListener listener) {
        this.listener = listener;
    }

    public void delete(int position) {
        this.pins.remove(position);
        notifyDataSetChanged();
    }

    public void setItems(List<Pin> pins) {
        this.pins = pins;
        notifyDataSetChanged();
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pin, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pin pin = pins.get(position);
        holder.textviewName.setText(pin.getName());
        holder.textviewLatlng.setText(pin.getLongitude() + ", " + pin.getLatitude());
        holder.textviewDate.setText(new Date(pin.getTimestamp()).toString());
        final int pos = position;
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(pin, pos);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }

}
