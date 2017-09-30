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

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<Place> places;
    private PlaceClickListener clickListener;
    private PlaceClickListener longClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textviewName;
        public TextView textviewDescription;
        public TextView textviewLatlng;
        public TextView textviewDate;

        public ViewHolder(View v) {
            super(v);
            textviewName = v.findViewById(R.id.textview_name);
            textviewDescription = v.findViewById(R.id.textview_description);
            textviewLatlng = v.findViewById(R.id.textview_latlng);
            textviewDate = v.findViewById(R.id.textview_date);
        }
    }

    public PlaceAdapter(PlaceClickListener clickListener, PlaceClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void delete(int position) {
        this.places.remove(position);
        notifyDataSetChanged();
    }

    public void setItems(List<Place> histories) {
        this.places = histories;
        notifyDataSetChanged();
    }

    public void updateItem(Place place, int position) {
        this.places.remove(position);
        this.places.add(position, place);
        notifyDataSetChanged();
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Place place = places.get(position);
        holder.textviewName.setText(place.getName());
        holder.textviewDescription.setText(place.getDescription());
        holder.textviewLatlng.setText(place.getLongitude() + ", " + place.getLatitude());
        holder.textviewDate.setText(new Date(place.getTimestamp()).toString());
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.placeClick(place, pos);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClickListener.placeClick(place, pos);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

}
