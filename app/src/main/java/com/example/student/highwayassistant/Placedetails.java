package com.example.student.highwayassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Placedetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placedetails);

        //gets the information from another activity
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("placename");
        String vicinity = bundle.getString("vicinity");
        String formatted_address = bundle.getString("formatted_address");
        String formatted_phone = bundle.getString("formatted_phone");


        TextView mDisplayplacename = (TextView)findViewById(R.id.Name);
        TextView mDisplayvicinity = (TextView)findViewById(R.id.Vinicity);
        TextView mDisplayformatted_address = (TextView)findViewById(R.id.Address);
        TextView mDisplayformatted_phone = (TextView)findViewById(R.id.Phone);

        mDisplayplacename.setText(String.valueOf(name));
        mDisplayvicinity.setText(String.valueOf(vicinity));
        mDisplayformatted_address.setText(String.valueOf(formatted_address));
        mDisplayformatted_phone.setText(String.valueOf(formatted_phone));
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Placedetails.this, MapsActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();

    }
}
