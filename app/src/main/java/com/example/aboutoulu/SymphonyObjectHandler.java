package com.example.aboutoulu;

import org.json.JSONException;
import org.json.JSONObject;

public class SymphonyObjectHandler extends SportsPlaceObjectHandler{
    private JSONObject jsonObject = null;

    private String year = null;
    private String month = null;
    private String date = null;
    private String pointOfTime = null;
    private String concertClass = null;
    private String concert = null;
    private String listenesrs = null;


    public SymphonyObjectHandler(JSONObject jsonObject){
        this.jsonObject = jsonObject;

        year = getValue(jsonObject, "year");
        month = getValue(jsonObject, "month");
        date = getValue(jsonObject, "date");
        pointOfTime = getValue(jsonObject, "point_of_time");
        concertClass = getValue(jsonObject, "concert_class");
        concert = getValue(jsonObject, "concert");
        listenesrs = getValue(jsonObject, "listeners");

    }

    public String getValue(JSONObject jsonObject, String key){
        return super.getValue(jsonObject, key);
    }

    public String getText(){
        String text = "";

        String date = this.date != null ? this.date:"unknown";
        text = text + "Date: " + date + "\n";

        String pointOfTime = this.pointOfTime != null ? this.pointOfTime : "unknown";
        text = text + "Status: " + pointOfTime + "\n";

        String concert = this.concert != null ? this.concert : "unknown";
        text = text + "Concert: " + concert + "\n";

        String concertClass = this.concertClass != null ? this.concertClass : "unknown";
        text = text + "Concert Class: " + concertClass + "\n";

        String listeners = this.listenesrs != null ? this.listenesrs: "unknown";
        text = text + "Listeners: " + listeners + "\n";

        return text;
    }

}
