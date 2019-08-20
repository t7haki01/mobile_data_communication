package com.example.aboutoulu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity {

    private String[] tagNames;
    private LinearLayout linearLayout;

//    public SingleMenuItemActivity(String[] tags){
//        this.tagNames = tags;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item);
        this.linearLayout = findViewById(R.id.main_linear_in_single_item);
        Intent intent = getIntent();

        TextView textView = new TextView(getApplicationContext());
        textView.setText(intent.getStringExtra("info"));
        this.linearLayout.addView(textView);


//        for(int i=0; i<this.tagNames.length; i++){
//            TextView textView = new TextView(getApplicationContext());
//            textView.setText(intent.getStringExtra(this.tagNames[i]));
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            this.linearLayout.setLayoutParams(layoutParams);
//            this.linearLayout.addView(textView);
//        }


    }
}
