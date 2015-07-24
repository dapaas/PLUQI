package com.sirmamobile.saltlux.http.api;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sirmamobile.base.http.DataTarnsferType;
import com.sirmamobile.base.http.HTTPTask;
import com.sirmamobile.base.http.HTTPType;
import com.sirmamobile.base.http.NormalRequest;
import com.sirmamobile.base.utils.DebugUtil;
import com.sirmamobile.base.utils.LogUtil;
import com.sirmamobile.saltlux.R;

public abstract class SaltluxBaseHttp extends HTTPTask{

	@Override
	protected final void manageResponse(int statusCode, InputStream is) {
		try {
			String response = getDataTransferType().prepareResponse(is);
			LogUtil.log(this, "Response", response);
			JSONObject jo = new JSONObject(response);
			if(jo.has("error")){
	            String r = jo.getString("error");
				Gson gson = new GsonBuilder().create();
				reportError(gson.fromJson(r, Error.class));
				return;
			} else {
				manageStringResponse(statusCode, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportError(e);
			return;
		}
	}

	protected abstract void manageStringResponse(int status, String response);
	
	@Override
	protected final String getUrl(Context context) {
		if(DebugUtil.isDebug())
			return context.getResources().getString(R.string.debug_url);
		else
			return context.getResources().getString(R.string.release_url);
	}

	@Override
	protected String getUrlAddOn(Context context) {

		return null;
	}
	
	@Override
	protected Map<String, String> getUrlParams(Context context) {
		return null;
	}

	@Override
	protected String getPostData(Context context) {
		return null;
	}

	@Override
	protected HTTPType getHttpType() {
		return null;
	}

	@Override
	protected DataTarnsferType getDataTransferType() {
		return new NormalRequest();
	}
    
    public static String urlEncode(String value){
    	if(value == null)
    		return null;
		else
			try {
				return URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	return null;
    }
}
