package com.sirmamobile.saltlux.http.requests;

import android.app.Activity;
import android.os.Bundle;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.base.utils.Task;
import com.sirmamobile.saltlux.http.api.GetCompareCities;
import com.sirmamobile.saltlux.http.model.CompareCities;

public class GetCompareCitiesDataRequestFragment extends SaltluxRequestFragment {

	private static final String ARG_CITIES = "ARG_CITIES";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof GetCityDataRequestListener))
            throw new RuntimeException("Wrong interface type");
    }

    public static RequestFragment newInstance(String cities) {
        RequestFragment f = new GetCompareCitiesDataRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITIES, cities);
        f.setArguments(args);
        return f;
    }

    @Override
	protected void onState(String state) {
    	
    }

	@Override
	protected void onResponse(Object response, Object data) {
        ((GetCompareCitiesDataRequestListener)getActivity()).getCompareCitiesData((CompareCities)response);
	}

	@Override
	protected Task getTask() {
        return new GetCompareCities(getArguments().getString(ARG_CITIES));
	}
}
