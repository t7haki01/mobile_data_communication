package com.example.aboutoulu;

import org.json.JSONObject;

public class CouncilObjectHandler extends SportsPlaceObjectHandler {

    private JSONObject jsonObject = null;

    private String organization = null;
    private String name = null;
    private String description = null;
    private String dateTime = null;
    private String location = null;
    private String isActive = null;
    private String docLink = null;


    public CouncilObjectHandler(JSONObject jsonObject){
        this.jsonObject = jsonObject;

        organization = getValue(jsonObject, "morgan");
        name = getValue(jsonObject, "mname");
        description = getValue(jsonObject, "mdescription");
        dateTime = getValue(jsonObject, "mdatetime");
        location = getValue(jsonObject, "mlocation");
        isActive = getValue(jsonObject, "misactive");
        docLink = getValue(jsonObject, "doclink");

    }

    public String getValue(JSONObject jsonObject, String key){
        return super.getValue(jsonObject, key);
    }

    public String getText(){

        String text = "";

        String organization = this.organization != null ? this.organization:"unknown";
        text = text + "Organization: " + organization + "\n";

        String name = this.name != null ? this.name : "unknown";
        text = text + "Name: " + name + "\n";

        String description = this.description != null ? this.description : "unknown";
        text = text + "Description: " + description + "\n";

        String dateTime = this.dateTime != null ? this.dateTime : "unknown";
        text = text + "Date: " + dateTime + "\n";

        String location = this.location != null ? this.location: "unknown";
        text = text + "Location: " + location + "\n";

        String isActive = this.isActive != null ? this.isActive : "unknown";
        text = text + "Active: " + isActive + "\n";

        String docLink = this.docLink != null ? this.docLink : "unknown";
        text = text + "Document Link: " + docLink + "\n";

        return text;
    }
}
