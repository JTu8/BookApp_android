package com.example.htmjs.bookapp;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetByIdAndUpdate {

    private ArrayList<Books> _books;

    private void getByID(int id) {

        final String getBookByIDUrl = "http://localhost:50645/api/Books/" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getBookByIDUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Get By ID", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        
                    }

                } catch (JSONException ex) {
                    ex.printStackTrace();
                    ex.getMessage();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
    }
}
