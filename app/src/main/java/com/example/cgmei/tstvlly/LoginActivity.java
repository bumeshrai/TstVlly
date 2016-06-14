package com.example.cgmei.tstvlly;

import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private String latitude = "";
    private String longitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btLogin = (Button) findViewById(R.id.btLogin);



        // Start Login. JSON Request and Response
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store Username/Password
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //Log.i("value", jsonResponse.toString());
                            boolean success = jsonResponse.getJSONObject("data").getBoolean("success");
                            String userid = jsonResponse.getJSONObject("data").getString("id");
                            String organisation = jsonResponse.getJSONObject("data").getString("organisation");
                            String auth_key = jsonResponse.getJSONObject("data").getString("auth_key");
                            //Log.i("value",  "success: " + String.valueOf(success) + ", auth_key: "+ auth_key);

                            if (success) {
                                //Pass to successor program
                                Intent intent = new Intent(LoginActivity.this, AssetMaintActivity.class);
                                intent.putExtra("auth_key", auth_key);
                                LoginActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // Volley Request
                UserLoginRequest loginRequest = new UserLoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
                //Log.i("values", "username: "+username+"/"+password+" location: "+latitude+"/"+longitude );
            }
        });
    }
}