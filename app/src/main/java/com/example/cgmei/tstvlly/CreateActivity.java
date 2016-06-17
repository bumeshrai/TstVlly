package com.example.cgmei.tstvlly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    public JSONObject parent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final TextView tvMessage = (TextView) findViewById(R.id.tvMessage);
        final TextView tvEngineer = (TextView) findViewById(R.id.tvEngineer);
        final TextView tvContractor = (TextView) findViewById(R.id.tvContractor);

        Intent intent = getIntent();
        String parentString = intent.getStringExtra("parent");

        try {
            this.parent = new JSONObject(parentString);
            parent.put("eng_id","6");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.i("value","At Create, Parent: "+parent);
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonResponse) {
                try {
                    //Log.i("value", "Response: "+jsonResponse.toString());
                    jsonResponse = jsonResponse.getJSONObject("data");
                    //Log.i("value", "Response: "+jsonResponse.toString());
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        String message = jsonResponse.getString("message");
                        String username = jsonResponse.getString("username");
                        String organisation = jsonResponse.getString("organisation");

                        tvMessage.setText(message);
                        tvEngineer.setText("Engineer: "+username);
                        tvContractor.setText("Contractor: "+organisation);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley","Error");
            }
        };

        // Volley Request
        WorksheetRequest worksheetRequest = new WorksheetRequest(parent, responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(CreateActivity.this);
        queue.add(worksheetRequest);
        //Log.i("here","Method Called");

    }
}
