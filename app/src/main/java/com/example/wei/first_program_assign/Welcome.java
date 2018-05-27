package com.example.wei.first_program_assign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Welcome extends AppCompatActivity {

    EditText latitudeEntryField;
    EditText longitudeEntryField;
    Button submitButton;

    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference locationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        latitudeEntryField = (EditText) findViewById(R.id.latitudeEntryField);
        longitudeEntryField = (EditText) findViewById(R.id.longitudeEntryField);
        submitButton = (Button) findViewById(R.id.button_submit);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        locationRef = database.getReference("locations");
    }

    public void submitPoint(View view) {
        String latitude = latitudeEntryField.getText().toString();
        String longitude = longitudeEntryField.getText().toString();

        LocationPoint newPoint = new LocationPoint(latitude, longitude);
        locationRef.child(Integer.toString(newPoint.hashCode())).setValue(newPoint);

    }


}
