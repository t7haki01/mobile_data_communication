package com.example.xml_request_demo;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MainActivity extends ListActivity {

    // All static variables
    static final String URL = "http://172.20.240.11:7002";
    // XML node keys
    static final String KEY_MATCH = "match"; // parent node
    static final String KEY_HOME_TEAM = "home_team";
    static final String KEY_AWAY_TEAM = "visitor_team";
    static final String KEY_HOME_SCORE = "home_goals";
    static final String KEY_AWAY_SCORE = "visitor_goals";

    final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;

    XMLParser parser;
    String xml; // getting XML

    Document doc; // getting DOM element

    NodeList nl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new XmlAsyncTask().execute();

//        permissionHandler();
    }

    private class XmlAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            XMLrequest();

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            updateUI();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    // constructor
    private void XMLrequest(){

        parser = new XMLParser();
        xml = parser.getXmlFromUrl(URL); // getting XML

        doc = parser.getDomElement(xml); // getting DOM element

        nl = doc.getElementsByTagName(KEY_MATCH);

    }
    /**
     * Getting XML from URL making HTTP request
     * @param url string
     * */
    public String getXmlFromUrl (String url)  {
        String xml = null;

        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
    }

    void updateUI(){

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();


        for (int i = 0; i < nl.getLength(); i++) {

            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);

            String homeTeam = parser.getValue(e, KEY_HOME_TEAM);
            String awayTeam = parser.getValue(e, KEY_AWAY_TEAM);
            String homeScore = parser.getValue(e, KEY_HOME_SCORE);
            String awayScore = parser.getValue(e, KEY_AWAY_SCORE);

            map.put(KEY_HOME_TEAM, homeTeam);
            map.put(KEY_HOME_SCORE, homeScore);
            map.put(KEY_AWAY_TEAM, awayTeam);
            map.put(KEY_AWAY_SCORE, awayScore);

            menuItems.add(map);

            ListAdapter adapter = new SimpleAdapter(getApplicationContext(), menuItems, R.layout.single_menu_item,
                    new String[]{KEY_HOME_TEAM, KEY_HOME_SCORE, KEY_AWAY_TEAM, KEY_AWAY_SCORE},
                    new int[]{R.id.home_team, R.id.home_score, R.id.away_team, R.id.away_score});

            setListAdapter(adapter);

            ListView lv = getListView();

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // getting values from selected ListItem
                    String homeTeam = ((TextView) view.findViewById(R.id.home_team)).getText().toString();
                    String awayTeam = ((TextView) view.findViewById(R.id.away_team)).getText().toString();
                    String homeScore = ((TextView) view.findViewById(R.id.home_score)).getText().toString();
                    String awayScore = ((TextView) view.findViewById(R.id.away_score)).getText().toString();


                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                    in.putExtra(KEY_HOME_TEAM, homeTeam);
                    in.putExtra(KEY_HOME_SCORE, homeScore);
                    in.putExtra(KEY_AWAY_TEAM, awayTeam);
                    in.putExtra(KEY_AWAY_SCORE, awayScore);
                    startActivity(in);
                }
            });
        }
    }

    /**
     * Getting XML DOM element
     * */
    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }

        return doc;
    }

    /** Getting node value
     * @param elem element
     */
    public final String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    /**
     * Getting node value
     * */
    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    private void permissionHandler() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.BIND_VPN_SERVICE))
            permissionsNeeded.add("To Make Sure School VPN");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "App need access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

        Toast.makeText(MainActivity.this, "No new Permission Required - Everything Good to go!", Toast.LENGTH_SHORT).show();
    }

    private boolean addPermission(List<String> permissionsList, String permission) {

        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);

            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}


