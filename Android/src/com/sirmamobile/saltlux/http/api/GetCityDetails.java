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

public class GetCityDetails extends SaltluxBaseHttp {

	private String city;

	

    public GetCityDetails(String city) {
		this.city = city;
	}

	@Override
    protected String getUrlAddOn(Context context) {
    	return "cityDataDetails";
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
    	return String.format("city=%s", urlEncode(city));
    }
    
    @Override
    protected boolean useCache(Context context) {
    	
    	if (TextUtils.isEmpty(readJsonFromAssets(context, "cityDataDetails.json")))
    		return false;
    	Gson gson = new GsonBuilder().create();
    	reportResponse(gson.fromJson(readJsonFromAssets(context, "cityDataDetails.json"), CityData.class));
    	return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, CityData.class));
	}
}
