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
import com.sirmamobile.saltlux.http.model.RankCity;

public class GetRankCity extends SaltluxBaseHttp {
	
	private String index;

    public GetRankCity(String index) {
		this.index = index;
	}

	@Override
    protected String getUrlAddOn(Context context) {
    	return "rankCity";
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
    	// TODO Auto-generated method stub
    	return urlEncode(String.format("index=%s", urlEncode(index)));
    }
    
    @Override
    protected boolean useCache(Context context) {
    	if (TextUtils.isEmpty(readJsonFromAssets(context, "rankCity.json")))
    		return false;
    	Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(readJsonFromAssets(context, "rankCity.json"), RankCity.class));
    	return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, RankCity.class));
	}
}
