package com.example.json_request_demo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    // url to make request
    private static String url = "http://172.20.240.11:7003/";
    ListView listView;

    // JSON Node names
    private static final String TAG_PORTS = "ports";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_IP = "ip";
    private static final String TAG_PORT = "port";

    // contacts JSONArray
    JSONArray ports = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String address = ((TextView) view.findViewById(R.id.address)).getText().toString();
                String ip = ((TextView) view.findViewById(R.id.ip)).getText().toString();
                String port = ((TextView) view.findViewById(R.id.port)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_ADDRESS, address);
                in.putExtra(TAG_IP, ip);
                in.putExtra(TAG_PORT, port);
                startActivity(in);
            }
        });
        new ParseTask().execute();
    }

    private class ParseTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>>{
        @Override
        protected void onPreExecute() {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> portsList) {
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, portsList,
                    R.layout.list_item,
                    new String[] { TAG_NAME, TAG_ADDRESS, TAG_IP, TAG_PORT }, new int[] {
                    R.id.name, R.id.address, R.id.ip, R.id.port });

            setListAdapter(adapter);

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
            JSONObject json = jParser.getJSONFromUrl(url);

            try {
                // Getting Array of Contacts
                ports = json.getJSONArray(TAG_PORTS);
                for(int i = 0; i < ports.length(); i++){
                    JSONObject c = ports.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String address = c.getString(TAG_ADDRESS);
                    String ip = c.getString(TAG_IP);
                    String port = c.getString(TAG_PORT);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_ID, id);
                    map.put(TAG_NAME, name);
                    map.put(TAG_ADDRESS, address);
                    map.put(TAG_IP, ip);
                    map.put(TAG_PORT, port);

                    // adding HashList to ArrayList
                    portsList.add(map);
                }



                // looping through All Contacts

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return portsList;
        }
    }
}
