package com.example.xml_request_demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItemActivity  extends Activity {

    static final String KEY_MATCH = "match"; // parent node
    static final String KEY_HOME_TEAM = "home_team";
    static final String KEY_AWAY_TEAM = "visitor_team";
    static final String KEY_HOME_SCORE = "home_goals";
    static final String KEY_AWAY_SCORE = "visitor_goals";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_menu_item);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous intent

        String homeTeam = in.getStringExtra(KEY_HOME_TEAM);
        String awayTeam = in.getStringExtra(KEY_AWAY_TEAM);
        String homeScore = in.getStringExtra(KEY_HOME_SCORE);
        String awayScore = in.getStringExtra(KEY_AWAY_SCORE);

        // Displaying all values on the screen
        TextView lblHomeTeam = (TextView) findViewById(R.id.home_team);
        TextView lblHomeScore = (TextView) findViewById(R.id.home_score);
        TextView lblAwayTeam = (TextView) findViewById(R.id.away_team);
        TextView lblAwayScore = (TextView) findViewById(R.id.away_score);

        lblHomeTeam.setText(homeTeam);
        lblHomeScore.setText(homeScore);
        lblAwayTeam.setText(awayTeam);
        lblAwayScore.setText(awayScore);
    }
}

