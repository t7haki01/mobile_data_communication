package com.example.aboutoulu;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    ConstraintLayout constraintLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.main_view);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            constraintLayout.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.oululogo_youtube2014) );
        }
        else {
//            constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.oululogo_youtube2014));
        }

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SportsPlaceInformation.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PropertiesInformation.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SymphonyInformation.class);
                startActivity(intent);
            }
        });

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CouncilInformation.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.option_home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.option_sports_place){
            Intent intent = new Intent(this, SportsPlaceInformation.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.option_properties){
            Intent intent = new Intent(this, PropertiesInformation.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.option_symphony){
            Intent intent = new Intent(this, SymphonyInformation.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.option_council_meeting){
            Intent intent = new Intent(this, CouncilInformation.class);
            startActivity(intent);
        }
        return true;
    }
}
