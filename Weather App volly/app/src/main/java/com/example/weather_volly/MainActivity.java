package com.example.weather_volly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.weather_volly.Modals.Modal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    TextView cityname, region, country, localtime, temp_c, textW, tz_id,last_update;
    ImageView img;
    SwipeRefreshLayout refreshLayout;
    private String URL = "http://api.weatherapi.com/v1/current.json?key=d4d31304f1c344e6b8755029222812&q=Gujarat&aqi=yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityname = findViewById(R.id.city_name_id);
        region = findViewById(R.id.region_id);
        country = findViewById(R.id.country_id);
        localtime = findViewById(R.id.localtime_id);
        temp_c = findViewById(R.id.temp_c_id);
        textW = findViewById(R.id.textW_id);
        tz_id = findViewById(R.id.tzidid);
        img = findViewById(R.id.imag_id);
        last_update = findViewById(R.id.lastupdate_id);
        refreshLayout = findViewById(R.id.refresh_id);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                refreshLayout.setRefreshing(false);
            }
        });


    }
    public void refresh(){

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Modal modal =gson.fromJson(response,Modal.class);

                System.out.println(modal);
                cityname.setText(modal.getLocation().getName());
                region.setText(modal.getLocation().getRegion()+",");
                country.setText(modal.getLocation().getCountry());
                localtime.setText(modal.getLocation().getLocaltime());
                temp_c.setText(String.valueOf(modal.getCurrent().getTempC())+"\u2103");
                textW.setText(modal.getCurrent().getCondition().getText());
                tz_id.setText(modal.getLocation().getTzId());
//                Glide.with(img.getContext()).load(modal.getCurrent().getCondition().getIcon()).into(img);
                img.setImageResource(R.drawable.weather_icon);
                last_update.setText("Last Update : "+modal.getCurrent().getLastUpdated());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","log"+error);
                Toast.makeText(MainActivity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}