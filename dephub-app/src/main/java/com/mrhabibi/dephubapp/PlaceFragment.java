package com.mrhabibi.dephubapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mrhabibi on 9/14/17.
 */
public class PlaceFragment extends Fragment {

    private final static int UPDATE_REQUEST_CODE = 234;

    PlaceAdapter adapter;
    AppDatabase db = BaseApplication.get().database;

    PlaceClickListener clicListener = new PlaceClickListener() {
        @Override
        public void placeClick(Place place, int position) {
            Intent intent = new Intent(getContext(), PlaceActivity.class);
            intent.putExtra("place", place);
            intent.putExtra("position", position);
            startActivityForResult(intent, UPDATE_REQUEST_CODE);
        }
    };

    PlaceClickListener longClickListener = new PlaceClickListener() {
        @Override
        public void placeClick(final Place place, final int position) {
            new AlertDialog.Builder(getContext())
                    .setMessage("Hapus " + place.getName() + "?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.delete(position);
                            db.pinDao().delete(place);
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .create().show();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyler_view);
        final SearchView searchViewPlace = view.findViewById(R.id.searchview_place);

        adapter = new PlaceAdapter(clicListener, longClickListener);
        adapter.setItems(db.pinDao().getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        searchViewPlace.setIconifiedByDefault(false);
        searchViewPlace.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    adapter.setItems(db.pinDao().getAll());
                } else {
                    adapter.setItems(db.pinDao().getWithQuery("%" + newText + "%"));
                }
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Place place = (Place) data.getSerializableExtra("place");
            int position = data.getIntExtra("position", -1);
            adapter.updateItem(place, position);
            BaseApplication.get().database.pinDao().update(place);
        }
    }
}
