package com.example.cgmei.tstvlly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        JSONObject parent = null;;

        Intent intent = getIntent();
        String parentString = intent.getStringExtra("parent");
        try {
            parent = new JSONObject(parentString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("value","At Create, Parent: "+parent);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    jsonResponse = jsonResponse.getJSONObject("data");
                    Log.i("value", "Response: "+jsonResponse.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        // Volley Request
        WorksheetRequest worksheetRequest = new WorksheetRequest(parent, responseListener);
        RequestQueue queue = Volley.newRequestQueue(CreateActivity.this);
        queue.add(worksheetRequest);
    }
}
