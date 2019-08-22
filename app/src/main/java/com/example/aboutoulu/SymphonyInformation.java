package com.example.aboutoulu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SymphonyInformation extends MainActivity {
    private ListView listView;

    private ArrayAdapter<ArrayList> arrayAdapter;

    private ArrayList arrayList;

    private List<SymphonyObjectHandler> list;

    private String URL = "https://api.ouka.fi/v1/orchestra_visitor_stats";

    ProgressBar progressBar;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symphony_view);
        constraintLayout = findViewById(R.id.sports_main_view);
        progressBar = findViewById(R.id.progress_bar_symphony_view);
        progressBar.setVisibility(View.VISIBLE);

        listView = findViewById(R.id.symphony_list_view);
        arrayList = new ArrayList();
        list = new ArrayList<SymphonyObjectHandler>();
        getAPI(URL);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = arrayList.get(position)+"";
                SymphonyObjectHandler pickedOne = list.get(position);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SymphonyInformation.this);
                alertDialogBuilder.setTitle(title);
                alertDialogBuilder.setMessage(pickedOne.getText());
                alertDialogBuilder.setPositiveButton("OK", null);
                alertDialogBuilder.show();
            }
        });
    }

    private void getAPI(String url){
        Toast.makeText(this, "Request sent!", Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            JSONArray jsonArray = response;

                            for(int i = jsonArray.length() - 1 ; i>=0; i--){
                                SymphonyObjectHandler symphonyObjectHandler = new SymphonyObjectHandler(jsonArray.getJSONObject(i));
                                list.add(symphonyObjectHandler);
                                String name = response.getJSONObject(i).getString("concert");
                                arrayList.add(name);
                                arrayAdapter = new ArrayAdapter<ArrayList>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                                listView.setAdapter(arrayAdapter);
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Error, Whoops, looks like something went wrong.", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}
