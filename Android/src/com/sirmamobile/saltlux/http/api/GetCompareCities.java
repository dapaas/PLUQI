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
    	if (TextUtils.isEmpty(readJsonFromAssets(context, "compareCities.json")))
    		return false;
    	Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(readJsonFromAssets(context, "compareCities.json"), CompareCities.class));
		return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, CompareCities.class));
	}
}
