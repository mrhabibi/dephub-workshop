package com.mrhabibi.dephubapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mrhabibi on 9/30/17.
 */

public class PlaceActivity extends AppCompatActivity {

    Place place;
    int position = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        final EditText editTextName = findViewById(R.id.edittext_name);
        final EditText editTextDescription = findViewById(R.id.edittext_description);
        TextView textViewLat = findViewById(R.id.textview_lat);
        TextView textViewLong = findViewById(R.id.textview_lng);
        Button buttonAction = findViewById(R.id.button_action);

        Bundle extras = getIntent().getExtras();
        place = (Place) extras.getSerializable("place");
        if (extras.containsKey("position")) position = extras.getInt("position");

        editTextName.setText(place.getName());
        editTextDescription.setText(place.getDescription());
        textViewLat.setText("Latitude: " + place.getLatitude());
        textViewLong.setText("Longitude: " + place.getLongitude());
        buttonAction.setText(position == -1 ? "Tambahkan" : "Perbarui");

        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                place.setName(editTextName.getText().toString());
                place.setDescription(editTextDescription.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("place", place);
                intent.putExtra("position", position);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
