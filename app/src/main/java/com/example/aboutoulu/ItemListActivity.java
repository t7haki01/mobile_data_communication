package com.example.aboutoulu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemListActivity extends android.app.ListActivity {

    private String URL;
    private ListView listView;

//    public ItemListActivity(String url){
//        URL = url;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        Intent intent = getIntent();

        String sportId = intent.getStringExtra("sportId");

        URL = "http://lipas.cc.jyu.fi/api/sports-places/" + sportId;

//        listView = getListView();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            }
//        });
        new ParseTask(sportId).execute();
    }

    private class ParseTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

        String URL;

        public ParseTask(String sportId){
            URL = sportId;
        }

        @Override
        protected void onPreExecute() {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> portsList) {

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... strings) {

            // Hashmap for ListView
            ArrayList<HashMap<String, String>> portsList = new ArrayList<HashMap<String, String>>();

            // Creating JSON Parser instance
            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            JSONObject json = jParser.getJSONFromUrl(URL);

            try {
                String value = json.getString("city");
//                    JSONObject c = json.getJSONObject(i);

//                    String id = c.getString(TAG_ID);
//                    String name = c.getString(TAG_NAME);
//                    String address = c.getString(TAG_ADDRESS);
//                    String ip = c.getString(TAG_IP);
//                    String port = c.getString(TAG_PORT);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
//                    map.put(TAG_ID, id);
//                    map.put(TAG_NAME, name);
//                    map.put(TAG_ADDRESS, address);
//                    map.put(TAG_IP, ip);
//                    map.put(TAG_PORT, port);

                    // adding HashList to ArrayList
                    portsList.add(map);




                // looping through All Contacts

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return portsList;
        }
    }
}
