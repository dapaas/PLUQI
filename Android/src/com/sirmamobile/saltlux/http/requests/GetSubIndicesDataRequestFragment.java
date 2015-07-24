package com.sirmamobile.saltlux.http.requests;

import android.app.Activity;
import android.os.Bundle;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.base.utils.Task;
import com.sirmamobile.saltlux.fragments.CityFragment.Indices;
import com.sirmamobile.saltlux.http.api.GetSubIndicesData;
import com.sirmamobile.saltlux.http.model.CityData;

public class GetSubIndicesDataRequestFragment extends SaltluxRequestFragment {

	private static final String ARG_CITIES = "ARG_CITIES";
	private static final String ARG_INDEX = "ARG_INDEX";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof GetCityDataRequestListener))
            throw new RuntimeException("Wrong interface type");
    }

    public static RequestFragment newInstance(Indices index, String city) {
        RequestFragment f = new GetSubIndicesDataRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INDEX, index.name());
        args.putString(ARG_CITIES, city);
        f.setArguments(args);
        return f;
    }

    @Override
	protected void onState(String state) {
    	
    }

	@Override
	protected void onResponse(Object response, Object data) {
        ((GetSubIndicesDataRequestListener)getActivity()).getSubIndicesDataResponse((CityData)response);
	}

	@Override
	protected Task getTask() {
        return new GetSubIndicesData(Indices.valueOf(getArguments().getString(ARG_INDEX)), getArguments().getString(ARG_CITIES));
	}
}
