package com.sirmamobile.saltlux.http.api;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

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
    	String tmp = "{\"JSONDataCityDetails\":\"success\",\"cityData\":\"success\",\"data\":[{\"City\":\"Seoul\",\"Education\":\"9.5\",\"Environment\":\"9.0\",\"Healthcare\":\"8.9\",\"Cultural Satisfaction\":\"9.35\",\"Traffic Satisfaction\":\"8.75\",\"PLUQI\":\"9.1\",\"lon\":\"126.978888888888\",\"lat\":\"37.5658333333333\"},{\"City\":\"Daejeon\",\"Education\":\"9.5\",\"Environment\":\"9.6\",\"Healthcare\":\"8.5\",\"Cultural Satisfaction\":\"7.0\",\"Traffic Satisfaction\":\"7.8\",\"PLUQI\":\"8.48\",\"lon\":\"127.385\",\"lat\":\"36.35\"},{\"City\":\"Incheon\",\"Education\":\"6.9\",\"Environment\":\"7.0\",\"Healthcare\":\"8.0\",\"Cultural Satisfaction\":\"8.0\",\"Traffic Satisfaction\":\"9.8\",\"PLUQI\":\"7.94\",\"lon\":\"126.701666666666\",\"lat\":\"37.455\"},{\"City\":\"Daegu\",\"Education\":\"7.5\",\"Environment\":\"8.9\",\"Healthcare\":\"8.0\",\"Cultural Satisfaction\":\"7.3\",\"Traffic Satisfaction\":\"8.6\",\"PLUQI\":\"8.06\",\"lon\":\"128.6\",\"lat\":\"35.8666666666666\"},{\"City\":\"Gwangju\",\"Education\":\"8.5\",\"Environment\":\"7.2\",\"Healthcare\":\"6.8\",\"Cultural Satisfaction\":\"8.7\",\"Traffic Satisfaction\":\"6.7\",\"PLUQI\":\"7.58\",\"lon\":\"126.916666666666\",\"lat\":\"35.1666666666666\"},{\"City\":\"Busan\",\"Education\":\"7.8\",\"Environment\":\"6.5\",\"Healthcare\":\"7.4\",\"Cultural Satisfaction\":\"7.9\",\"Traffic Satisfaction\":\"9.1\",\"PLUQI\":\"7.74\",\"lon\":\"129.075555555555\",\"lat\":\"35.1794444444444\"},{\"City\":\"Gangwon-do\",\"Education\":\"7.4\",\"Environment\":\"9.005\",\"Healthcare\":\"9.5\",\"Cultural Satisfaction\":\"8.6\",\"Traffic Satisfaction\":\"7.1\",\"PLUQI\":\"8.321\",\"lon\":\"128.155499999999\",\"lat\":\"37.8228\"},{\"City\":\"Ulsan\",\"Education\":\"8.6\",\"Environment\":\"8.76\",\"Healthcare\":\"8.7\",\"Cultural Satisfaction\":\"7.8\",\"Traffic Satisfaction\":\"6.3\",\"PLUQI\":\"8.032\",\"lon\":\"129.3113596\",\"lat\":\"35.5383773\"},{\"City\":\"Kyeonggi-do\",\"Education\":\"9.2\",\"Environment\":\"5.9\",\"Healthcare\":\"7.0\",\"Cultural Satisfaction\":\"8.9\",\"Traffic Satisfaction\":\"8.2\",\"PLUQI\":\"7.84\",\"lon\":\"127.518299999999\",\"lat\":\"37.4137999999999\"},{\"City\":\"Jeju-do\",\"Education\":\"8.135\",\"Environment\":\"7.3\",\"Healthcare\":\"8.1\",\"Cultural Satisfaction\":\"6.5\",\"Traffic Satisfaction\":\"5.9\",\"PLUQI\":\"7.187\",\"lon\":\"126.531188\",\"lat\":\"33.4996213\"},{\"City\":\"Chungcheongnamdo\",\"Education\":\"7.35\",\"Environment\":\"6.5\",\"Healthcare\":\"9.3\",\"Cultural Satisfaction\":\"7.2\",\"Traffic Satisfaction\":\"7.8\",\"PLUQI\":\"7.63\",\"lon\":\"126.9\",\"lat\":\"36.6184\"},{\"City\":\"Chungcheongbukdo\",\"Education\":\"6.246\",\"Environment\":\"8.3\",\"Healthcare\":\"8.6\",\"Cultural Satisfaction\":\"8.9\",\"Traffic Satisfaction\":\"6.9\",\"PLUQI\":\"7.7892\",\"lon\":\"126.799999999999\",\"lat\":\"36.5184\"},{\"City\":\"Jeollanamdo\",\"Education\":\"7.531\",\"Environment\":\"7.12\",\"Healthcare\":\"6.9\",\"Cultural Satisfaction\":\"5.0\",\"Traffic Satisfaction\":\"9.3\",\"PLUQI\":\"7.1702\",\"lon\":\"126.990999999999\",\"lat\":\"34.8679\"},{\"City\":\"Jeollabukdo\",\"Education\":\"7.88\",\"Environment\":\"9.08\",\"Healthcare\":\"8.9\",\"Cultural Satisfaction\":\"6.9\",\"Traffic Satisfaction\":\"8.2\",\"PLUQI\":\"8.192\",\"lon\":\"127.153\",\"lat\":\"35.7175\"},{\"City\":\"Gyeongsangbukdo\",\"Education\":\"9.322\",\"Environment\":\"8.5\",\"Healthcare\":\"7.7\",\"Cultural Satisfaction\":\"8.3\",\"Traffic Satisfaction\":\"6.6\",\"PLUQI\":\"8.0844\",\"lon\":\"128.8889\",\"lat\":\"36.4919\"},{\"City\":\"Gyeongsangnamdo\",\"Education\":\"8.99\",\"Environment\":\"6.9\",\"Healthcare\":\"8.7\",\"Cultural Satisfaction\":\"7.9\",\"Traffic Satisfaction\":\"7.5\",\"PLUQI\":\"7.998\",\"lon\":\"128.2132\",\"lat\":\"35.4606\"}],\"dataCityDetails\":{\"results\":[{\"topics\":[{\"node\":\"Environment\"},{\"node\":\"Healthcare\"},{\"node\":\"Cultural Satisfaction\"},{\"node\":\"Traffic Satisfaction\"},{\"node\":\"Education\"}],\"end_date\":\"20140921\",\"start_date\":\"20140915\"},{\"topics\":[{\"node\":\"Education\"},{\"node\":\"Healthcare\"},{\"node\":\"Environment\"},{\"node\":\"Cultural Satisfaction\"},{\"node\":\"Traffic Satisfaction\"}],\"end_date\":\"20140928\",\"start_date\":\"20140922\"},{\"topics\":[{\"node\":\"Education\"},{\"node\":\"Environment\"},{\"node\":\"Traffic Satisfaction\"},{\"node\":\"Healthcare\"},{\"node\":\"Cultural Satisfaction\"}],\"end_date\":\"20141005\",\"start_date\":\"20140929\"},{\"topics\":[{\"node\":\"Healthcare\"},{\"node\":\"Cultural Satisfaction\"},{\"node\":\"Education\"},{\"node\":\"Environment\"},{\"node\":\"Traffic Satisfaction\"}],\"end_date\":\"20141012\",\"start_date\":\"20141006\"},{\"topics\":[{\"node\":\"Healthcare\"},{\"node\":\"Education\"},{\"node\":\"Environment\"},{\"node\":\"Cultural Satisfaction\"},{\"node\":\"Traffic Satisfaction\"}],\"end_date\":\"20141015\",\"start_date\":\"20141013\"}],\"source\":\"NEWS\"},\"dataSubIndices\":null,\"subIndicesDetailData\":\"success\"}";
    	Gson gson = new GsonBuilder().create();
    	reportResponse(gson.fromJson(tmp, CityData.class));
    	return true;
    }
    
	@Override
	protected void manageStringResponse(int status, String response) {
		Gson gson = new GsonBuilder().create();
		reportResponse(gson.fromJson(response, CityData.class));
	}
}
