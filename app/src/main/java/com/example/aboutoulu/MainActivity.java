package com.example.aboutoulu;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn1, btn2, btn3;
    String  URL1  = "http://lipas.cc.jyu.fi/api/sports-places?cityCode=564",
            URL2,
            URL3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view1);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAPI(URL1);
            }
        });

//        new ParseTask("http://lipas.cc.jyu.fi/api/sports-places?cityCode=564", textView).execute();
    }

    private class ParseTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

        private String url;
        private TextView textView;

        public ParseTask(String url, TextView textView){
            this.url = url;
            this.textView = textView;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> portsList) {
            /**
             * Updating parsed JSON data into ListView
             * */
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


            Log.d("From doInBackground", json+"");

            ArrayList<HashMap<String, String>> testArrayList = new ArrayList<HashMap<String, String>>();;

            return testArrayList;
        }
    }

    private void getAPI(String url){
        Toast.makeText(this, "Request sent! Loading....", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Log.d("from getAPI", response+"");
                         }
                        catch(NullPointerException e){
                            e.printStackTrace();
                        }
//                        catch(JSONException e){
//                            e.printStackTrace();
//                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Error, Whoops, looks like something went wrong.", Toast.LENGTH_LONG).show();
                    }
                });

// Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
