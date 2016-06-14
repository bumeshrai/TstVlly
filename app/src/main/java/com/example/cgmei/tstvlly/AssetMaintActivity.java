package com.example.cgmei.tstvlly;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class AssetMaintActivity extends AppCompatActivity {

    public LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_maint);
        linearLayout = (LinearLayout) findViewById(R.id.ll);

        Intent intent = getIntent();
        String auth_key = intent.getStringExtra("auth_key");

        Log.i("value", "Key: "+auth_key);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    Log.i("value", "Response: "+jsonResponse.toString());
                    jsonResponse = jsonResponse.getJSONObject("data");

                    FetchData fetchData = new FetchData();
                    Map<String, String> param = fetchData.getApiParam(jsonResponse);
                    Log.i("value", "Response: "+param);
                    fetchData.createViews(param, linearLayout);
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        // Volley Request
        AssetMaintRequest maintRequest = new AssetMaintRequest(auth_key, responseListener);
        RequestQueue queue = Volley.newRequestQueue(AssetMaintActivity.this);
        queue.add(maintRequest);
    }
}
