package com.example.android_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> carList = new ArrayList<String>();

    private static void getCars(){
        carList.add("AlfaRomeo");
        carList.add("BMW");
        carList.add("Corvette");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCars();

        final ListView myListView = (ListView) findViewById(R.id.myListView);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carList);
        myListView.setAdapter(aa);

        //for add button
        Button addCar = (Button) findViewById(R.id.add_btn);
        final EditText line = (EditText) findViewById(R.id.edit_text);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mark = line.getText().toString();
                carList.add(mark);
                myListView.setAdapter(aa);
            }
        });

        //for edit button
        Button editCar = (Button) findViewById(R.id.edit_btn);
        editCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // register onClickListener to handle click events on each item
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    // argument position gives the index of item which is clicked
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedCar = carList.get(position);
                        Toast.makeText(getApplicationContext(), "Car selected : " + selectedCar, Toast.LENGTH_LONG).show();
                        line.setText(selectedCar);
                    }
                });
            }
        });

        Button removeCar = (Button) findViewById(R.id.remove_btn);
        removeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedCar = carList.get(position);
                        Toast.makeText(getApplicationContext(), "Car selected : " + selectedCar, Toast.LENGTH_LONG).show();
                        line.setText(selectedCar);
                    }
                });
            }
        });

        //Second activity button
        Button secondActivity = (Button)findViewById(R.id.second_activity);
        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Second Activity Selected!", Toast.LENGTH_LONG).show();
                //--intent
                Intent intent  =new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);

            }
        });
    }
}
