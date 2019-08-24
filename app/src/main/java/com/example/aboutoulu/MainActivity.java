package com.example.aboutoulu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5;
    ConstraintLayout constraintLayout = null;



    @Override
    public void onResume() {
        super.onResume();
        btnColorChange(btn1, R.color.redish4);
        btnColorChange(btn2, R.color.redish3);
        btnColorChange(btn3, R.color.redish2);
        btnColorChange(btn4, R.color.redish1);
        btnColorChange(btn5, R.color.redish5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.main_view);
        setTitle(getResources().getString(R.string.btn_about));

        btn1 = findViewById(R.id.btn_sports_place);
        btnColorChange(btn1, R.color.redish4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnColorChange(btn1, R.color.colorWhitish);
                Intent intent = new Intent(getApplicationContext(), SportsPlaceInformation.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.btn_properties);
        btnColorChange(btn2, R.color.redish3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnColorChange(btn2, R.color.colorWhitish);
                Intent intent = new Intent(getApplicationContext(), PropertiesInformation.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.btn_symphony);
        btnColorChange(btn3, R.color.redish2);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnColorChange(btn3, R.color.colorWhitish);
                Intent intent = new Intent(getApplicationContext(), SymphonyInformation.class);
                startActivity(intent);
            }
        });

        btn4 = findViewById(R.id.btn_council_meeting);
        btnColorChange(btn4, R.color.redish1);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnColorChange(btn4, R.color.colorWhitish);
                Intent intent = new Intent(getApplicationContext(), CouncilInformation.class);
                startActivity(intent);
            }
        });

        btn5 = findViewById(R.id.btn_about);
        btnColorChange(btn5, R.color.redish5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnColorChange(btn5, R.color.colorWhitish);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle(getResources().getString(R.string.btn_about));
                alertDialogBuilder.setMessage(getResources().getString(R.string.description_about_oulu));
                alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        btnColorChange(btn5, R.color.redish5);
                    }
                });
                alertDialogBuilder.setPositiveButton("OK", null);
                alertDialogBuilder.show();
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

    private void btnColorChange(Button btn, int targetColor){
        btn.setBackgroundColor(getResources().getColor(targetColor, getTheme()));
    }
}
