package com.sirmamobile.saltlux.http.api;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

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
    	String tmp = "{\"data\":{\"results\":[{\"topics\":[{\"node\":\"Pusan\"},{\"node\":\"Gangwon-do\"},{\"node\":\"Sejong\"},{\"node\":\"Ulsan\"},{\"node\":\"Kyeonggi-do\"},{\"node\":\"Jeju-do\"},{\"node\":\"Seoul\"},{\"node\":\"Daejeon\"},{\"node\":\"Incheon\"},{\"node\":\"Daegu\"},{\"node\":\"Gwangju\"},{\"node\":\"Chungcheongnamdo\"},{\"node\":\"Jeollanamdo\"},{\"node\":\"Jeollabukdo\"},{\"node\":\"Gyeongsangbukdo\"},{\"node\":\"Chungcheongbuko\"},{\"node\":\"Gyeongsangnamdo\"}],\"end_date\":\"20140921\",\"start_date\":\"20140915\"},{\"topics\":[{\"node\":\"Seoul\"},{\"node\":\"Jeollanamdo\"},{\"node\":\"Jeollabukdo\"},{\"node\":\"Gyeongsangbukdo\"},{\"node\":\"Chungcheongbuko\"},{\"node\":\"Daejeon\"},{\"node\":\"Incheon\"},{\"node\":\"Daegu\"},{\"node\":\"Gwangju\"},{\"node\":\"Pusan\"},{\"node\":\"Gangwon-do\"},{\"node\":\"Sejong\"},{\"node\":\"Ulsan\"},{\"node\":\"Kyeonggi-do\"},{\"node\":\"Jeju-do\"},{\"node\":\"Chungcheongnamdo\"},{\"node\":\"Gyeongsangnamdo\"}],\"end_date\":\"20140928\",\"start_date\":\"20140922\"},{\"topics\":[{\"node\":\"Seoul\"},{\"node\":\"Daejeon\"},{\"node\":\"Gwangju\"},{\"node\":\"Pusan\"},{\"node\":\"Gangwon-do\"},{\"node\":\"Incheon\"},{\"node\":\"Daegu\"},{\"node\":\"Sejong\"},{\"node\":\"Jeju-do\"},{\"node\":\"Chungcheongnamdo\"},{\"node\":\"Jeollanamdo\"},{\"node\":\"Ulsan\"},{\"node\":\"Kyeonggi-do\"},{\"node\":\"Jeollabukdo\"},{\"node\":\"Gyeongsangbukdo\"},{\"node\":\"Chungcheongbuko\"},{\"node\":\"Gyeongsangnamdo\"}],\"end_date\":\"20141005\",\"start_date\":\"20140929\"},{\"topics\":[{\"node\":\"Gwangju\"},{\"node\":\"Pusan\"},{\"node\":\"Gangwon-do\"},{\"node\":\"Seoul\"},{\"node\":\"Daejeon\"},{\"node\":\"Incheon\"},{\"node\":\"Daegu\"},{\"node\":\"Sejong\"},{\"node\":\"Ulsan\"},{\"node\":\"Kyeonggi-do\"},{\"node\":\"Jeju-do\"},{\"node\":\"Gyeongsangbukdo\"},{\"node\":\"Chungcheongbuko\"},{\"node\":\"Gyeongsangnamdo\"},{\"node\":\"Chungcheongnamdo\"},{\"node\":\"Jeollanamdo\"},{\"node\":\"Jeollabukdo\"}],\"end_date\":\"20141012\",\"start_date\":\"20141006\"},{\"topics\":[{\"node\":\"Seoul\"},{\"node\":\"Daejeon\"},{\"node\":\"Pusan\"},{\"node\":\"Gangwon-do\"},{\"node\":\"Sejong\"},{\"node\":\"Ulsan\"},{\"node\":\"Kyeonggi-do\"},{\"node\":\"Jeju-do\"},{\"node\":\"Incheon\"},{\"node\":\"Daegu\"},{\"node\":\"Gwangju\"},{\"node\":\"Chungcheongnamdo\"},{\"node\":\"Jeollanamdo\"},{\"node\":\"Jeollabukdo\"},{\"node\":\"Gyeongsangbukdo\"},{\"node\":\"Chungcheongbuko\"},{\"node\":\"Gyeongsangnamdo\"}],\"end_date\":\"20141015\",\"start_date\":\"20141013\"}],\"source\":\"NEWS\"},\"rankOfCitiesData\":\"success\"}";
    	Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(tmp, RankCity.class));
    	return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, RankCity.class));
	}
}
