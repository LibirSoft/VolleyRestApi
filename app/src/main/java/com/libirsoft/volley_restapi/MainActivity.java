package com.libirsoft.volley_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button get_data_btn;
    TextView response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_data_btn = findViewById(R.id.get_data_btn);
        response_text = findViewById(R.id.response_text);

        get_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendGetrequest();

            }
        });


    }

    private void sendGetrequest() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://www.metaweather.com/api/location/search/?query=istanbul";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // we are getting only 0 index cuz we have only one jsonobject
                    JSONObject object = response.getJSONObject(0);
                    String city_name = object.getString("title");
                    Log.d("city name :",city_name);
                    response_text.setText(city_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Eror is : "+error,Toast.LENGTH_LONG).show();

            }
        });

        queue.add(arrayRequest);
    }
}