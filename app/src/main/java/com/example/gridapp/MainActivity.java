package com.example.gridapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainResponse";
    ArrayList<ImageModel> data = new ArrayList<>();
    RecyclerView recyclerView;
    ImageAdapter adapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ImageAdapter(this, data);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading...");


        GetImages("https://pokeapi.co/api/v2/pokemon?limit=1050");
    }



    private void GetImages(String url) {
        pd.show();

        StringRequest request= new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();

                        Log.d(TAG,"onResponse:" +response);

                        try {
                            JSONObject object = new JSONObject(response);

                            JSONArray array=object.getJSONArray("results");

                            for(int i=0;i<array.length();i++){
                                JSONObject object1=array.getJSONObject(i);
                                data.add(
                                        new ImageModel(
                                                object1.getString("name"),
                                                object1.getString("url")
                                        )
                                );
                                Log.d(TAG,"onResponse:" +data.get(i).getAuthor());

                            }

                            adapter.notifyDataSetChanged();

                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.d(TAG,"onResponse:" +error);

                    }
                }
        );

         Volley.newRequestQueue(this).add(request);

    }
}
