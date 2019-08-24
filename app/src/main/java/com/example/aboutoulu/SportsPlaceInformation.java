package com.example.aboutoulu;

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
import java.util.List;


public class SportsPlaceInformation extends MainActivity {
    private ListView listView;

    private ArrayAdapter<ArrayList> arrayAdapter;

    private ArrayList arrayList;

    private List<SportsPlaceObjectHandler> list;

    private String URL = "http://lipas.cc.jyu.fi/api/sports-places?searchString=oulu";

    ProgressBar progressBar;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_view);
        setTitle(getResources().getString(R.string.btn_sprots_place));
        constraintLayout = findViewById(R.id.sports_main_view);
        progressBar = findViewById(R.id.progress_bar_sports_view);
        progressBar.setVisibility(View.VISIBLE);

        listView = findViewById(R.id.sports_list_view);
        arrayList = new ArrayList();
        list = new ArrayList<SportsPlaceObjectHandler>();
        getAPI(URL);
    }

    private void getAPI(String url){
        Toast.makeText(this, "Request sent!", Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i=0; i<response.length();i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                String sportId = jsonObject.getString("sportsPlaceId");
                                String url = "http://lipas.cc.jyu.fi/api/sports-places/" ;
                                getAPI(url, sportId);
                            }
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

    private void getAPI(String url, String id){
        Toast.makeText(this, "Request sent!", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url+id, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONObject jsonObject = response;
                            String name = response.getString("name");

                            SportsPlaceObjectHandler sportsPlaceObjectHandler = new SportsPlaceObjectHandler(jsonObject);
                            list.add(sportsPlaceObjectHandler);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    String title = arrayList.get(position)+"";
                                    SportsPlaceObjectHandler pickedOne = list.get(position);

                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SportsPlaceInformation.this);
                                    alertDialogBuilder.setTitle(title);
                                    alertDialogBuilder.setMessage(pickedOne.getText());
                                    alertDialogBuilder.setPositiveButton("OK", null);
                                    alertDialogBuilder.show();
                                }
                            });

                            arrayList.add(name);
                            arrayAdapter = new ArrayAdapter<ArrayList>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                            listView.setAdapter(arrayAdapter);
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
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
