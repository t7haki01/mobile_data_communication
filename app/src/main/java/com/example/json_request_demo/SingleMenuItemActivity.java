package com.example.json_request_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_IP = "ip";
    private static final String TAG_PORT = "port";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        String address = in.getStringExtra(TAG_ADDRESS);
        String ip = in.getStringExtra(TAG_IP);
        String port = in.getStringExtra(TAG_PORT);

        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblAddress = (TextView) findViewById(R.id.address_label);
        TextView lblIp = (TextView) findViewById(R.id.ip_label);
        TextView lblPort = (TextView) findViewById(R.id.port_label);

        lblName.setText(name);
        lblAddress.setText(address);
        lblIp.setText(ip);
        lblPort.setText(port);

    }
}
