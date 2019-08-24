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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CouncilInformation extends MainActivity {
    private ListView listView;

    private ArrayAdapter<ArrayList> arrayAdapter;

    private ArrayList arrayList;

    private List<CouncilObjectHandler> list;

    private String URL = "https://api.ouka.fi/v1/city_council_meetings";

    ProgressBar progressBar;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.council_view);
        setTitle(getResources().getString(R.string.btn_council_meeting));
        constraintLayout = findViewById(R.id.council_main_view);
        progressBar = findViewById(R.id.progress_bar_council_view);
        progressBar.setVisibility(View.VISIBLE);

        listView = findViewById(R.id.council_list_view);
        arrayList = new ArrayList();
        list = new ArrayList<CouncilObjectHandler>();
        getAPI(URL);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = arrayList.get(position)+"";
                CouncilObjectHandler pickedOne = list.get(position);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CouncilInformation.this);
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

                            for(int i = 0 ; i<jsonArray.length(); i++){
                                CouncilObjectHandler councilObjectHandler = new CouncilObjectHandler(jsonArray.getJSONObject(i));
                                list.add(councilObjectHandler);
                                String name = response.getJSONObject(i).getString("mname");
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