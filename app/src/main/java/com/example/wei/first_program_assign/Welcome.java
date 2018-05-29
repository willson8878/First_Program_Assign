package com.example.wei.first_program_assign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Welcome extends AppCompatActivity implements NetworkController.ResultListener {

    EditText latitudeEntryField;
    EditText longitudeEntryField;
    Button submitButton;
    EditText responseBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        latitudeEntryField = (EditText) findViewById(R.id.latitudeEntryField);
        longitudeEntryField = (EditText) findViewById(R.id.longitudeEntryField);
        submitButton = (Button) findViewById(R.id.button_submit);
        responseBox = (EditText) findViewById(R.id.responseBox);
    }

    public void submitPoint(View view) {
        String latitude = latitudeEntryField.getText().toString();
        String longitude = longitudeEntryField.getText().toString();

        HashMap<String, String> stringParams = new HashMap<>();
        stringParams.put("latitude", latitude);
        stringParams.put("longitude", longitude);

        NetworkController.getInstance().connect(this, URLConstants.POST_REQUEST_CODE, Request.Method.POST, stringParams, this);
    }

    public void refreshData(View view) {
        NetworkController.getInstance().connect(this, URLConstants.GET_REQUEST_CODE, Request.Method.GET, null, this);
    }


    @Override
    public void onResult(int requestCode, boolean isSuccess, JSONObject jsonObject, VolleyError volleyError) {
        responseBox.setText(jsonObject.toString());
    }
}
