package com.example.cgmei.tstvlly;

import android.content.Context;
import android.util.Log;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class FetchData {

    private Map<String, String> params;
    Context context;
    LinearLayout linearLayout;
    String createdViews[][] = new String [20][2];
    static List<CheckedTextView> checkedList = new ArrayList<CheckedTextView>();
    static List<EditText> editTextList = new ArrayList<EditText>();

    public FetchData(Context context){
        this.context = context;
    }

    public Map<String, String> getApiParam(JSONObject jsonResponse) {

        Iterator<String> keys = jsonResponse.keys();
        params = new HashMap<>();
        String parameter = "";
        String infoRead ="";


        //Iterate through the keys
        while(keys.hasNext()){
            //Add key's name to List of Parameters
            parameter = keys.next();
            infoRead ="";
            try {
            infoRead = jsonResponse.getString(parameter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Parse the key name & replace '_' with SPACE and Replace lowercase letter following it with Uppercase letter
            String label = parameter.replace("_"," ");
            String[] tokens = label.split(" ");
            label = "";
            for(String str: tokens)
                label += Character.toUpperCase(str.charAt(0)) + str.substring(1) + " ";

            params.put(label, infoRead);
            //Log.i("showArray","label: " + label + "  infoRead: " + infoRead);
        }
        return params;
    }

    //Function for Dynamic Creation of Views --> Checkboxes, EditTexts and Buttons
    public void createViews(Map<String, String> params, LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
        int viewCount=0;
        Iterator entries = params.entrySet().iterator();


        while(entries.hasNext()){
            Map.Entry thisEntry = (Map.Entry) entries.next();
            //Create EditText whenever there's a "yes" and add it to the 2D array of created Views
            if (thisEntry.getValue().equals("1")) {
                createdViews[viewCount][0] = thisEntry.getValue().toString();
                createdViews[viewCount++][1] ="Check";
                Log.i("value","Text: "+ thisEntry.getKey());
                new CreateLayout(context, linearLayout).createCheckedTextView(thisEntry.getKey().toString());
            }
            //Create EditText whenever there's a "yes" and add it to the 2D array of created Views
            else if (thisEntry.getValue().equals("yes")) {
                createdViews[viewCount][0] = thisEntry.getValue().toString();
                createdViews[viewCount++][1] ="Edit";
                new CreateLayout(context, linearLayout).createEditText();
            }
        }
        List<String> tunnelParameters = new ArrayList<String>();
        entries = params.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            tunnelParameters.add(thisEntry.getKey().toString());
        }

        new CreateLayout(context,linearLayout).createSubmitButton("", createdViews, tunnelParameters);
    }
}
