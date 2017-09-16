package com.mrhabibi.dephubapp;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

/**
 * Created by mrhabibi on 9/14/17.
 */
@EFragment(R.layout.fragment_history)
public class HistoryFragment extends Fragment {

    @ViewById(R.id.recyler_view)
    RecyclerView recyclerView;

    HistoryAdapter adapter;
    OnLongClickListener listener = new OnLongClickListener() {
        @Override
        public void onLongClick(final Pin pin, final int position) {
            new AlertDialog.Builder(getContext())
                    .setMessage("Hapus " + pin.getName() + "?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.delete(position);
                            BaseApplication.get().database.pinDao().delete(pin);
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .create().show();
        }
    };

    @AfterViews
    void initView() {
        adapter = new HistoryAdapter(listener);
        adapter.setItems(BaseApplication.get().database.pinDao().getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
