package com.mrhabibi.dephubapp;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by mrhabibi on 9/14/17.
 */
@EFragment(R.layout.fragment_pin)
public class PinFragment extends Fragment {

    @ViewById(R.id.recyler_view)
    RecyclerView recyclerView;

    @AfterViews
    void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Click(R.id.fab_add)
    void clickAdd() {

    }
}
