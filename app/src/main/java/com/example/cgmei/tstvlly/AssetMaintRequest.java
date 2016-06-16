package com.example.cgmei.tstvlly;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class AssetMaintRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL
            = "http://cmrlvent.co.in/assetMaint/api/web/maintenance-next-dues/?assetCode=0200260018000101&token=";

    public AssetMaintRequest (String auth_key, Response.Listener<String> listener){
        super(Request.Method.GET, LOGIN_REQUEST_URL + auth_key, listener, null);
    }

}
