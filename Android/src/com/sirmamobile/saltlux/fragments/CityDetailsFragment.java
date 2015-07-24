package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.adapters.RankCityFragmentAdapter;
import com.sirmamobile.saltlux.adapters.RankCityFragmentAdapterListener;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestFragment;
import com.sirmamobile.saltlux.http.requests.GetCityDetailsRequestFragment;

public class CityDetailsFragment extends SaltluxFragment implements OnClickListener, RankCityFragmentAdapterListener{
	
	private static final String ARG_CITY_DATA = "ARG_CITY_DATA";
	private static final String ARG_CITY_FULL = "ARG_CITY_FULL";
	private String city;
	public enum Indices{
		education,
		enviroment,
		healthcare,
		culture,
		traffic
	}
	
	public static final CityDetailsFragment load(String city){
		CityDetailsFragment df = new CityDetailsFragment();
		Bundle b = new Bundle();
		b.putString(ARG_CITY_DATA, city);
        df.setArguments(b);
		return df;
	}
	
	@Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	if(!(activity instanceof CityListener))
    		throw new RuntimeException("Wrong interface type");
    }
	
	private CityData data;
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		city = getArguments().getString(ARG_CITY_DATA);
		data = getArguments().getParcelable(ARG_CITY_FULL);
	}

	private int index = 0;
	private ViewPager vp;
	private RankCityFragmentAdapter adapter;
	@Override
    protected View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.city_details, container, false);
		TextView txtInformation = (TextView) v.findViewById(R.id.txtInformation);
		txtInformation.setText(String.format("*Information of %s:",city));
		vp = (ViewPager) v.findViewById(R.id.vpCityDetails);
		if (index >= 0)
			vp.setCurrentItem(index);
 	    return v;
    }
	
	@Override
	public void onResume() {
		super.onResume();
		if(data == null)
			RequestFragment.doRequest(getFragmentManager(), GetCityDetailsRequestFragment.newInstance(city), GetCityDataRequestFragment.CALLER_LIST);
		else
			fillData();
	}
	
	public void getCityDetailsResponse(CityData data){
		this.data = data;
		getArguments().putParcelable(ARG_CITY_FULL, data);
		fillData();
	}
	
	private void fillData(){
		if (adapter == null) {
			adapter = new RankCityFragmentAdapter(getChildFragmentManager(),data.getDataCityDetails());
			adapter.setListener(this);
		}
		vp.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		
	}


}
