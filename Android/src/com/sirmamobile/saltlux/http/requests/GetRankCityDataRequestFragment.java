package com.sirmamobile.saltlux.http.requests;

import android.app.Activity;
import android.os.Bundle;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.base.utils.Task;
import com.sirmamobile.saltlux.http.api.GetRankCity;
import com.sirmamobile.saltlux.http.model.RankCity;

public class GetRankCityDataRequestFragment extends SaltluxRequestFragment {
	
	private static final String ARG_INDEX = "ARG_INDEX";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof GetRankCityDataRequestListener))
            throw new RuntimeException("Wrong interface type");
    }

    public static RequestFragment newInstance(String index) {
        RequestFragment f = new GetRankCityDataRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Override
	protected void onState(String state) {
    	
    }

	@Override
	protected void onResponse(Object response, Object data) {
        ((GetRankCityDataRequestListener)getActivity()).getRankCityData((RankCity)response);
	}

	@Override
	protected Task getTask() {
        return new GetRankCity(getArguments().getString(ARG_INDEX));
	}
}
