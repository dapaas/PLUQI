package com.sirmamobile.saltlux.http.requests;

import android.app.Activity;
import android.os.Bundle;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.base.utils.Task;
import com.sirmamobile.saltlux.http.api.GetCityData;
import com.sirmamobile.saltlux.http.api.GetCityDetails;
import com.sirmamobile.saltlux.http.model.CityData;

public class GetCityDetailsRequestFragment extends SaltluxRequestFragment {

	public static final String CALLER_LIST = "CALLER_LIST";
	public static final String CALLER_MAP = "CALLER_MAP";
	
	private static final String ARG_CITY   = "ARG_CITY";



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof GetCityDataRequestListener))
            throw new RuntimeException("Wrong interface type");
    }

    public static RequestFragment newInstance(String city) {
        RequestFragment f = new GetCityDetailsRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        f.setArguments(args);
        return f;
    }

    @Override
	protected void onState(String state) {
    	
    }

	@Override
	protected void onResponse(Object response, Object data) {
        ((GetCityDetailsRequestListener)getActivity()).getCityDataDetailsResponse((CityData)response, data);
	}
	
	@Override
	protected Task getTask() {
        return new GetCityDetails(getArguments().getString(ARG_CITY));
	}
}
