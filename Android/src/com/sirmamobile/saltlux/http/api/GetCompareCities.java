package com.sirmamobile.saltlux.http.api;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sirmamobile.base.http.HTTPType;
import com.sirmamobile.saltlux.http.model.CompareCities;

public class GetCompareCities extends SaltluxBaseHttp {

	private String cities;
	
    public GetCompareCities(String cities) {
    	this.cities = cities;
    }

    @Override
    protected String getUrlAddOn(Context context) {
    	return "compareCities";
    }
    
    @Override
    protected Map<String, String> getUrlParams(Context context) {
    	return new HashMap<String, String>(){{
    		put("cities", cities);
    	}};
    }
    
    @Override
    protected HTTPType getHttpType() {
    	return HTTPType.POST;
    }
    
    @Override
    protected String getPostData(Context context) {
    	return super.getPostData(context);
    }
    
    @Override
    protected boolean useCache(Context context) {
    	String tmp = "{\"arrCity\":[\"Seoul\",\"Daejeon\",\"Incheon\",\"Daegu\",\"Gwangju\",\"Pusan\",\"Gangwon-do\",\"Sejong\",\"Ulsan\",\"Kyeonggi-do\",\"Jeju-do\",\"Chungcheongnamdo\",\"Jeollanamdo\",\"Jeollabukdo\",\"Gyeongsangbukdo\",\"Chungcheongbuko\",\"Gyeongsangnamdo\"],\"compareCitiesData\":\"success\",\"data\":{\"response\":[{\"city\":\"Seoul\",\"values\":[{\"index\":\"PLUQI\",\"value\":10},{\"index\":\"Education\",\"value\":5},{\"index\":\"Environment\",\"value\":8},{\"index\":\"Healthcare\",\"value\":7},{\"index\":\"Cultural Satisfaction\",\"value\":9},{\"index\":\"Traffic Satisfaction\",\"value\":4}]},{\"city\":\"Daejeon\",\"values\":[{\"index\":\"PLUQI\",\"value\":9},{\"index\":\"Education\",\"value\":6},{\"index\":\"Environment\",\"value\":9},{\"index\":\"Healthcare\",\"value\":7},{\"index\":\"Cultural Satisfaction\",\"value\":9},{\"index\":\"Traffic Satisfaction\",\"value\":4}]},{\"city\":\"Daegu\",\"values\":[{\"index\":\"PLUQI\",\"value\":8},{\"index\":\"Education\",\"value\":6},{\"index\":\"Environment\",\"value\":5},{\"index\":\"Healthcare\",\"value\":7},{\"index\":\"Cultural Satisfaction\",\"value\":9},{\"index\":\"Traffic Satisfaction\",\"value\":4}]}]}}";
    	Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(tmp, CompareCities.class));
		return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, CompareCities.class));
	}
}
