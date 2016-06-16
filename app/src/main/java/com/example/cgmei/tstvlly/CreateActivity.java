package com.example.cgmei.tstvlly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
    }
}
