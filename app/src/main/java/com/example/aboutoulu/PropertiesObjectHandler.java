package com.example.aboutoulu;

import org.json.JSONObject;

public class PropertiesObjectHandler extends SportsPlaceObjectHandler {
    private JSONObject jsonObject = null;
    private String property_name = null;
    private String intended_use = null;
    private String district_key = null;
    private String district_name = null;
    private String property_address = null;
    private String postal_code = null;
    private String postal_area = null;
    private String grossarea = null ;
    private String totalgrossarea = null;
    private String volume = null;
    private String totalfloorarea = null;
    private String year_built = null;
    private String year_renovated = null;
    private String floorcount = null;
    private String attic_floorcount = null;

    public PropertiesObjectHandler(JSONObject jsonObject){
        this.jsonObject = jsonObject;

        property_name = getValue(jsonObject, "property_name");
        property_address = getValue(jsonObject, "property_address");
        intended_use = getValue(jsonObject, "intended_use");
        district_key = getValue(jsonObject, "district_key");
        district_name = getValue(jsonObject, "district_name");
        property_address = getValue(jsonObject, "property_address");
        postal_area = getValue(jsonObject, "postal_area");
        postal_code = getValue(jsonObject, "postal_code");
        grossarea = getValue(jsonObject, "grossarea");
        totalgrossarea = getValue(jsonObject, "totalgrossarea");
        year_built = getValue(jsonObject, "year_built");
        year_renovated = getValue(jsonObject, "year_renovated");
        floorcount = getValue(jsonObject, "floorcount");
        volume = getValue(jsonObject, "volume");
        floorcount = getValue(jsonObject, "floorcount");
        attic_floorcount = getValue(jsonObject, "attic_floorcount");

    }

    public String getValue(JSONObject jsonObject, String key){
        return super.getValue(jsonObject, key);
    }

    public String getText(){
        String text = "";

        String name = this.property_name!=null?this.property_name:"unknown";
        text = text + "Name: " + name + "\n";

        String intended_use = this.intended_use!=null ? this.intended_use:"unknown";
        text = text + "Intended Usage: " + intended_use + "\n";

        String district_key = this.district_key != null ? this.district_key : "?";
        String district_name = this.district_name != null ? this.district_name : "?";
        String property_address = this.property_address != null ? this.property_address : "?";
        String postal_code = this.postal_code != null ? this.postal_code : "?";
        String postal_area = this.postal_area != null ? this.postal_area : "?";
        text = text + "Address: " + district_key + " " + district_name + " " + property_address + "\n";
        text = text + postal_code + " " + postal_area + "\n";

        String year_built = this.year_built!= null ? this.year_built:"?";
        String year_renovated = this.year_renovated != null ? this.year_renovated:"?";

        text = text + "Built in: " + year_built + "\n";
        text = text + "Renovated in: " + year_renovated + "\n";

        String grossarea = this.grossarea !=null ? this.grossarea:"?";
        text = text + "Gross Area: " + grossarea + "\n";

        String totalgrossarea = this.totalgrossarea != null ? this.totalgrossarea:"?";
        text = text + "Total Gross Area: " + totalgrossarea + "\n";

        String volume = this.volume != null ? this.volume : "?";
        text = text + "Volume: " + volume + "\n";

        String floorcount = this.floorcount != null ? this.floorcount : "?";
        text = text + "Floor: " + floorcount + "\n";

        String attic_floorcount = this.attic_floorcount != null ? this.attic_floorcount:"?";
        text = text + "Attic Floor: " + attic_floorcount + "\n";

        return text;
    }
}
