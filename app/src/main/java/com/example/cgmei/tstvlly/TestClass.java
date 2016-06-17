package com.example.cgmei.tstvlly;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by umesh on 17/6/16.
 */
public class TestClass extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://cmrlvent.co.in/assetMaint/api/web/lift/create";
    private Map<String, String> params;

    public TestClass(String username ,Response.Listener<String> listener) {
        // Request Post and pass on URL
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        //POST Array
        params = new HashMap<>();
        params.put("eng_id", "6");
        params.put("freq_id", "0");
    }

    @Override
    public Map<String, String> getParams() { //Overide method
        return params;
    }
}
