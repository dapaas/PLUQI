package com.sirmamobile.saltlux.http.requests;

import android.app.Activity;
import android.os.Bundle;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.base.utils.Task;
import com.sirmamobile.saltlux.http.api.GetCityData;
import com.sirmamobile.saltlux.http.model.CityData;

public class GetCityDataRequestFragment extends SaltluxRequestFragment {

	public static final String CALLER_LIST = "CALLER_LIST";
	public static final String CALLER_MAP = "CALLER_MAP";
	
	private static final String ARG_EDUCATION   = "ARG_EDUCATION";
	private static final String ARG_ENVIRONMENT = "ARG_ENVIRONMENT";
	private static final String ARG_HEALTHCARE = "ARG_HEALTHCARE";
	private static final String ARG_CULTURAL_SATISFACTION = "ARG_CULTURAL_SATISFACTION";
	private static final String ARG_TRAFFIC_SATISFACTION = "ARG_TRAFFIC_SATISFACTION";


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof GetCityDataRequestListener))
            throw new RuntimeException("Wrong interface type");
    }

    public static RequestFragment newInstance(int education, int environment, int healthcare,
											  int culturalSatisfaction, int trafficSatisfaction) {
        RequestFragment f = new GetCityDataRequestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EDUCATION, education);
        args.putInt(ARG_ENVIRONMENT, environment);
        args.putInt(ARG_HEALTHCARE, healthcare);
        args.putInt(ARG_CULTURAL_SATISFACTION, culturalSatisfaction);
        args.putInt(ARG_TRAFFIC_SATISFACTION, trafficSatisfaction);

        f.setArguments(args);
        return f;
    }

    @Override
	protected void onState(String state) {
    	
    }

	@Override
	protected void onResponse(Object response, Object data) {
        ((GetCityDataRequestListener)getActivity()).getCityData((CityData)response, data);
	}
	
	@Override
	protected Task getTask() {
        return new GetCityData(getArguments().getInt(ARG_EDUCATION),
        					   getArguments().getInt(ARG_ENVIRONMENT),
        					   getArguments().getInt(ARG_HEALTHCARE),
        					   getArguments().getInt(ARG_CULTURAL_SATISFACTION),
        					   getArguments().getInt(ARG_TRAFFIC_SATISFACTION));
	}
}
