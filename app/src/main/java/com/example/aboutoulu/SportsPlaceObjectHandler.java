package com.example.aboutoulu;

import org.json.JSONException;
import org.json.JSONObject;


public class SportsPlaceObjectHandler {

    private JSONObject jsonObject;

    private JSONObject properties = null ;
    private String schoolUse = null;
    private String renovationYears = null;
    private String constructionYear = null;
    private String freeUse = null;
    private String locationId = null;
    private JSONObject city = null;
    private String postalOffice = null;
    private String postalCode = null;
    private String neighborhood = null;
    private JSONObject geometries = null;
    private String areaM2 = null;
    private String surfaceMaterial = null;
    private String typeCode = null;
    private String typeName = null;
    private String address = null;
    private JSONObject coordinate = null;
    private String wgs = null;
    private String tm35fin = null;
    private String cityName = null;
    private String cityCode = null;
    private String geoType = null;
    private JSONObject geoFeature = null;
    private String geoFeatureType = null;
    private JSONObject geometry = null;
    private String geometryCoordinate = null;
    private String geometryPointId = null;
    private String email = null;
    private String admin = null;
    private String www = null;
    private String name = null;
    private JSONObject type = null;
    private String lastModified = null;
    private String id = null;
    private String number = null;
    private JSONObject location = null;
    private String owner = null;
    private String fieldWidthM = null;
    private String fieldLengthM = null;
    private String innerLaneLengthM = null;
    private String sprintTrackLengthM = null;
    private String runningTrackSurfaceMaterial = null;
    private String trainingSpotSurfaceMaterial = null;
    private String toilet = null;
    private String loudSpeakers = null;
    private String scoreBoard = null;
    private String kioski = null;
    private String automatedScoring  = null;
    private String cosmicBowling = null;
    private String bowlingLanesCount = null;

    public SportsPlaceObjectHandler(){}

    public SportsPlaceObjectHandler(JSONObject jsonObject){
        this.jsonObject = jsonObject;

        properties = getJsonObject(jsonObject, "properties");
        location = getJsonObject(jsonObject, "location");


        schoolUse = getValue(jsonObject, "schoolUse");
        admin = getValue(jsonObject,"admin");
        www = getValue(jsonObject,"www");
        lastModified = getValue(jsonObject,"lastModified");
        id = getValue(jsonObject,"sportsPlaceId");
        owner = getValue(jsonObject,"owner");


        type = getJsonObject(jsonObject, "type");

        renovationYears = getValue(jsonObject, "renovationYears");
        constructionYear = getValue(jsonObject, "constructionYear");
        freeUse = getValue(jsonObject, "freeUse");

        email = getValue(jsonObject,"email");
        name = getValue(jsonObject,"name");
        number = getValue(jsonObject,"phoneNumber");

        if(properties!=null){
            areaM2 = getValue(properties, "areaM2");
            surfaceMaterial = getValue(properties, "surfaceMaterial");
            trainingSpotSurfaceMaterial = getValue(properties, "trainingSpotSurfaceMaterial");
            runningTrackSurfaceMaterial = getValue(properties, "runningTrackSurfaceMaterial");
            sprintTrackLengthM = getValue(properties, "sprintTrackLengthM");
            innerLaneLengthM = getValue(properties, "innerLaneLengthM");
            fieldLengthM = getValue(properties, "fieldLengthM");
            fieldWidthM = getValue(properties, "fieldWidthM");

            toilet = getValue(properties, "toilet");
            loudSpeakers = getValue(properties, "loudspeakers");
            scoreBoard = getValue(properties, "scoreboard");
            kioski = getValue(properties, "kiosk");
            automatedScoring = getValue(properties, "automatedScoring");
            cosmicBowling = getValue(properties, "cosmicBowling");
            bowlingLanesCount = getValue(properties, "bowlingLanesCount");
        }

        if(type != null){
            typeCode = getValue(type, "typeCode");
            typeName = getValue(type, "name");
        }

        if(location != null){
            address = getValue(location, "address");
            coordinate = getJsonObject(location, "coordinates");
            city = getJsonObject(location, "city");
            locationId = getValue(location, "locationId");
            postalOffice = getValue(location, "postalOffice");
            postalCode = getValue(location, "postalCode");
            neighborhood = getValue(location, "neighborhood");
            geometries = getJsonObject(location, "geometries");
        }

        if(city != null){
            cityName = getValue(city, "name");
            cityCode = getValue(city, "cityCode");
        }

        if(geometries != null){
            geoType = getValue(geometries, "type");
            geoFeature = getJsonArray(geometries, "features", 0);
        }

        if(coordinate!=null){
            wgs =  getValue(getJsonObject(coordinate, "wgs84"), "lon") + ", " +getValue(getJsonObject(coordinate, "wgs84"), "lat");
            tm35fin = getValue(getJsonObject(coordinate, "tm35fin"), "lon") + ", " +getValue(getJsonObject(coordinate, "tm35fin"), "lat");
        }

        if(geoFeature != null){
            geoFeatureType = getValue(geoFeature, "type");
            geometry = getJsonObject(geoFeature, "geometry");
            geometryPointId = getValue(getJsonObject(geoFeature, "properties"), "pointId");
        }

        if(geometry!=null){
            geometryCoordinate =  getJsonArrayValue(geometry, "coordinates",0) + ", " + getJsonArrayValue(geometry, "coordinates",1) +", type: " + getValue(geometry, "type");
        }


    }

    public String getValue(JSONObject jsonObject, String key){
        String value = null;
        try{
            value = jsonObject.getString(key);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return value;
    }

    public JSONObject getJsonObject(JSONObject jsonObject, String key){

        JSONObject resultJsonObject = null;

        try{
            resultJsonObject = jsonObject.getJSONObject(key);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return resultJsonObject;
    }

    public JSONObject getJsonArray(JSONObject jsonObject, String key, int i){
        JSONObject resultJsonObject = null;

        try{
            resultJsonObject = jsonObject.getJSONArray(key).getJSONObject(i);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return resultJsonObject;
    }

    public Double getJsonArrayValue(JSONObject jsonObject, String key, int i){
        Double result = null;

        try{
            result = jsonObject.getJSONArray(key).getDouble(i);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return result;
    }

    public String getText(){
        String text = "";

        text = text + getBasicInfo() + "\n";
        text = text + getProperties() + "\n";
        text = text + getLocation() + "\n";
        text = text + getGeoInfo() + "\n";
        text = text + getOthers() + "\n";

        return text;
    }

    private String getOthers(){
        String text = "Other information" + "\n";

        String typeName = this.typeName!=null?this.typeName:"unknown";
        text = text + "Type Name: " + typeName + "\n";

        String typeCode = this.typeCode!=null?this.typeCode:"unknown";
        text = text + "Type Code: " + typeCode + "\n";

        String id = this.id != null ? this.id:"unknown";
        text = text + "Sports place ID: " + id + "\n";

        return text;
    }

    private String getGeoInfo(){
        String text = "*Geometry Information" + "\n";

        String geoType = this.geoType!=null?this.geoType:"unknown";
        String geoFeatureType = this.geoFeatureType!=null?this.geoFeatureType:"unknown";
        String geometryCoordinate = this.geometryCoordinate!=null?this.geometryCoordinate:"unknown";
        String geometryPointId = this.geometryPointId != null? this.geometryPointId:"unknown";

        text = text + "Geometries type: " + geoType + "\n";
        text = text + "Geometries feature type: " + geoFeatureType + "\n";
        text = text + "Geometries coordinate: " + geometryCoordinate + "\n";
        text = text + "Geometries point ID: " + geometryPointId + "\n";

        return text;
    }

    private String getLocation(){
        String text = "*Location Information" + "\n";

        String wgs84 = this.wgs!=null?this.wgs:"unknown";
        String tm35fin = this.tm35fin!=null?this.tm35fin:"unknown";
        String locationId = this.locationId!=null?this.locationId:"unknown";

        text = text + "Coordinates in wgs84: " + wgs84 + "\n";
        text = text + "Coordinates in tm35fin: " + tm35fin + "\n";
        text = text + "Location ID: " + locationId + "\n";

        return text;

    }

    private String getBasicInfo(){
        String text = "*Basic Information" + "\n";

        String name = this.name!=null?this.name:"unknown";
        text = text + "Name: "+ name + "\n";

        String city = this.cityName!=null?this.cityName:"unknown";
        text = text + "City: " + city + "\n";

        String cityCode = this.cityCode!=null?this.cityCode:"unknown";
        text = text + "City Code: " + cityCode + "\n";

        String address = this.address!=null?this.address:"unknown";
        text = text + "Address: "+ address + "\n";

        String email = this.email!=null?this.email:"unknown";
        text = text + "Email: " + email + "\n";

        String schoolUse = this.schoolUse!=null?this.schoolUse:"unknown";
        text = text + "Used by School nearby: "+ schoolUse + "\n";

        String admin = this.admin!=null?this.admin:"unknown";
        text = text + "Admin: " + admin + "\n";

        String www = this.www!=null?this.www:"unknown";
        text = text + "Web: "+ www + "\n";

        String constructionYear = this.constructionYear!=null?this.constructionYear:"unknown";
        text = text + "Constructed in: " + constructionYear + "\n";

        String renovationYears = this.renovationYears!=null?this.renovationYears:"unknown";
        text = text + "Renovated in: " + renovationYears + "\n";

        String freeUse = this.freeUse!=null?this.freeUse:"unknown";
        text = text + "Free to use: "+ freeUse + "\n";

        String lastModified = this.lastModified!=null?this.lastModified:"unknown";
        text = text + "Last modified date: " + lastModified + "\n";

        String number = this.number!=null?this.number:"unknown";
        text = text + "Phone Number: "+ number + "\n";

        String owner = this.owner!=null?this.owner:"unknown";
        text = text + "Owner: " + owner + "\n";

        String neighborhood = this.neighborhood!=null?this.neighborhood:"unknown";
        text = text + "Neighborhood: " + neighborhood + "\n";

        String postalOffice = this.postalOffice!=null?this.postalOffice:"unknown";
        text = text + "Postal Office: " + postalOffice + "\n";

        String postalCode = this.postalCode!=null?this.postalCode:"unknown";
        text = text + "Postal Code: " + postalCode + "\n";

        return text;
    }

    private String getProperties(){
        String text = "";

        text = text + "*Properties" + "\n";

        String toilet = this.toilet != null ? this.toilet: "unknown";
        text = text + "Toilet: " + toilet + "\n";

        String loudSpeakers = this.loudSpeakers != null ? this.loudSpeakers:"unknown";
        text = text + "Loud Speakers: " + loudSpeakers + "\n";

        String scoreBoard = this.scoreBoard != null ? this.scoreBoard:"unknown";
        text = text + "Score Board: " + scoreBoard + "\n";

        String kiosk = this.kioski != null ? this.kioski : "unknown";
        text = text + "Kiosk: " + kiosk + "\n";

        String automatedScoring = this.automatedScoring!=null ? this.automatedScoring:"unknown";
        text = text + "Automated Scoring: " + automatedScoring + "\n";

        String cosmicBowling = this.cosmicBowling!=null?this.cosmicBowling:"unknown";
        text = text + "Cosmic Bowling: " + cosmicBowling + "\n";

        String bowlingLanesCount = this.bowlingLanesCount!=null ? this.bowlingLanesCount:"unknown";
        text = text + "Bowling Lanes Count: " + bowlingLanesCount + "\n";

        String areaM2 = this.areaM2 != null ? this.areaM2:"unknown";
        String surfaceMaterial = this.surfaceMaterial != null ? this.surfaceMaterial:"unknown";
        String runningTrackSurfaceMaterial = this.runningTrackSurfaceMaterial != null ? this.runningTrackSurfaceMaterial:"unknown";
        String sprintTrackLengthM = this.sprintTrackLengthM != null ? this.sprintTrackLengthM:"unknown";
        String innerLaneLengthM = this.innerLaneLengthM != null ? this.innerLaneLengthM:"unknown";
        String fieldLengthM = this.fieldLengthM != null ? this.fieldLengthM:"unknown";
        String fieldWidthM = this.fieldWidthM != null ? this.fieldWidthM:"unknown";
        String trainingSpotSurfaceMaterial = this.trainingSpotSurfaceMaterial!=null?this.trainingSpotSurfaceMaterial:"unknown";

        text = text + "Area(m^2): " + areaM2 +"\n";
        text = text + "Surface material: " + surfaceMaterial +"\n";
        text = text + "Training spot surface material: " + trainingSpotSurfaceMaterial +"\n";
        text = text + "Running Track material: " + runningTrackSurfaceMaterial +"\n";
        text = text + "Sprint Track Length (m) : " + sprintTrackLengthM +"\n";
        text = text + "Inner Lane Length (m): " + innerLaneLengthM +"\n";
        text = text + "Field Length (m): " + fieldLengthM +"\n";
        text = text + "Field Width (m): " + fieldWidthM +"\n";

        return text;
    }


}
