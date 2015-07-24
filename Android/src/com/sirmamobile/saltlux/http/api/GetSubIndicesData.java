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
import com.sirmamobile.saltlux.fragments.CityFragment.Indices;
import com.sirmamobile.saltlux.http.model.CityData;

public class GetSubIndicesData extends SaltluxBaseHttp {

	private Indices index;
	private String city;

	

    public GetSubIndicesData(Indices index, String city) {
    	this.index = index;
    	this.city = city;
	}

	@Override
    protected String getUrlAddOn(Context context) {
    	return "subIndicesData";
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
    	return String.format("index=%s&city=%s", urlEncode(index.name()), urlEncode(String.valueOf(city)));
    }
    
    @Override
    protected boolean useCache(Context context) {
    	if (TextUtils.isEmpty(readJsonFromAssets(context, "subIndicesData.json")))
    		return false;
    	Gson gson = new GsonBuilder().create();
    	reportResponse(gson.fromJson(readJsonFromAssets(context, "subIndicesData.json"), CityData.class));
    	return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, CityData.class));
	}
}
