package com.sirmamobile.saltlux.http.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sirmamobile.base.http.HTTPType;
import com.sirmamobile.saltlux.http.model.CityData;

public class GetCityData extends SaltluxBaseHttp {

	private int education;
	private int environment;
	private int healthcare;
	private int culturalSatisfaction;
	private int trafficSatisfaction;

	

    public GetCityData(int education, int environment, int healthcare,
			int culturalSatisfaction, int trafficSatisfaction) {
		this.education = education;
		this.environment = environment;
		this.healthcare = healthcare;
		this.culturalSatisfaction = culturalSatisfaction;
		this.trafficSatisfaction = trafficSatisfaction;
	}

	@Override
    protected String getUrlAddOn(Context context) {
    	return "cityData";
    }
    
    @Override
    protected Map<String, String> getUrlParams(Context context) {
    	return new HashMap<String, String>(){{
    	}};
    }
    
    @Override
    protected HTTPType getHttpType() {
    	return HTTPType.POST;
    }
    
    @Override
    protected String getPostData(Context context) {
    	return String.format("education=%s&environment=%s&healthcare=%s&cultural_satisfaction=%s&traffic_satisfaction=%s", urlEncode(String.valueOf(education)),
				   urlEncode(String.valueOf(environment)),
				   urlEncode(String.valueOf(healthcare)),
				   urlEncode(String.valueOf(culturalSatisfaction)),
				   urlEncode(String.valueOf(trafficSatisfaction)));
    }
    
    @Override
    protected boolean useCache(Context context) {
    	if (TextUtils.isEmpty(readJsonFromAssets(context, "cityData.json")))
    		return false;
    	Gson gson = new GsonBuilder().create();
    	reportResponse(gson.fromJson(readJsonFromAssets(context, "cityData.json"), CityData.class));
    	return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, CityData.class));
	}
}
