package com.mrhabibi.dephubapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.List;

/**
 * Created by mrhabibi on 9/14/17.
 */
@EFragment(R.layout.fragment_maps)
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    @ViewById(R.id.searchview_location)
    SearchView searchViewLocation;

    GoogleMap gmap;

    @AfterViews
    void initView() {
        SupportMapFragment fragment = SupportMapFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.framelayout_map, fragment).commit();
        fragment.getMapAsync(this);
        searchViewLocation.setIconifiedByDefault(false);
        searchViewLocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                gmap.clear();
                LatLng location = getLocationFromAddress(query);
                if (location != null) {
                    gmap.addMarker(new MarkerOptions().position(location));
                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10.0f));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(getContext());
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            if (address.size() == 0) {
                Toast.makeText(getContext(), "Alamat tidak ditemukan", Toast.LENGTH_SHORT).show();
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    123);
            return;
        }
        gmap.setMyLocationEnabled(true);
    }
}
