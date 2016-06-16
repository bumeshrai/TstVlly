package com.example.cgmei.tstvlly;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by umesh on 16/6/16.
 */
public class WorksheetRequest extends JsonObjectRequest {

    private static final String LOGIN_REQUEST_URL
            = "http://cmrlvent.co.in/assetMaint/api/web/lift/create";

    public WorksheetRequest (JSONObject parent,Response.Listener<JSONObject> responseListener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, (parent == null) ? null : parent, responseListener, null);
        Log.i("here","Reached WSRequest");
    }
}
