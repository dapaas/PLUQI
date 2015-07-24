package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestFragment;
import com.sirmamobile.saltlux.http.requests.GetSubIndicesDataRequestFragment;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapterListener;
import com.sirmamobile.saltlux.recycleview.RecycleViewDecoration;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;
import com.sirmamobile.saltlux.recycleview.saltlux.CityDataAdapter;
import com.sirmamobile.saltlux.recycleview.saltlux.CityRatingDataAdapter;
import com.sirmamobile.saltlux.recycleview.saltlux.CityRatingViewItem;

public class CityFragment extends SaltluxFragment implements OnClickListener, RecycleViewAdapterListener{
	
	private static final String ARG_CITY_DATA = "ARG_CITY_DATA";
	private static final String ARG_CITY_FULL = "ARG_CITY_FULL";
	
	public enum Indices{
		education,
		enviroment,
		healthcare,
		culture,
		traffic
	}
	
	public static final CityFragment load(Data city){
		CityFragment df = new CityFragment();
		Bundle b = new Bundle();
		b.putParcelable(ARG_CITY_DATA, city);
        df.setArguments(b);
		return df;
	}
	
	@Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	if(!(activity instanceof CityListener))
    		throw new RuntimeException("Wrong interface type");
    }
	
	private Data city;
	private CityData data;
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		city = getArguments().getParcelable(ARG_CITY_DATA);
		data = getArguments().getParcelable(ARG_CITY_FULL);
	}
	private TextView txtDetailsOf;
	private TextView txtTemperatureValue;
	private TextView txtHumidityValue;
	private TextView txtPM10Value;
	private TextView txtGreenSpacesValue;

	@Override
    protected View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.city, container, false);
    	TextView txtCityName = (TextView) v.findViewById(R.id.txtCityName);
    	txtCityName.setText(city.getCity()+": " + city.getPluqi());
    	v.findViewById(R.id.txtCityDetails).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((CityListener)getActivity()).cityDetailsCliked(city.getCity());
			}
		});

       RecyclerView rvWorkout = (RecyclerView) v.findViewById(R.id.cityRating);
  	   RecyclerView.ItemDecoration itemDecoration = new RecycleViewDecoration(getActivity());
  	   rvWorkout.addItemDecoration(itemDecoration);
  	   LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
  	   mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
  	   mLayoutManager.scrollToPosition(0);
  	   rvWorkout.setLayoutManager(mLayoutManager);
  	   CityRatingDataAdapter mAdapter = new CityRatingDataAdapter(this);
  	   mAdapter.setRatingData(city);
  	   rvWorkout.setAdapter(mAdapter);
  	   
  	   
    	txtDetailsOf = (TextView) v.findViewById(R.id.txtDetailsOf);
    	txtTemperatureValue = (TextView) v.findViewById(R.id.txtTemperatureValue);
    	txtHumidityValue = (TextView) v.findViewById(R.id.txtHumidityValue);
    	txtPM10Value = (TextView) v.findViewById(R.id.txtPm10);
    	txtGreenSpacesValue = (TextView) v.findViewById(R.id.txtGreenSpacesValue);
    	



 	   return v;
    }
	
	@Override
	public void onResume() {
		super.onResume();
		if(data == null)
			RequestFragment.doRequest(getFragmentManager(), GetSubIndicesDataRequestFragment.newInstance(Indices.education, city.getCity()), GetCityDataRequestFragment.CALLER_LIST);
		else
			fillData();
	}
	
	public void getSubIndicesDataResponse(CityData data){
		this.data = data;
		getArguments().putParcelable(ARG_CITY_FULL, data);
		fillData();
	}
	
	private void fillData(){
		
	}

	@Override
	public void onClick(View v) {}

	@Override
	public void itemClicked(View root, RecycleViewItem item) {
		getView().findViewById(R.id.llDetailsOf).setVisibility(View.VISIBLE);
		txtDetailsOf.setVisibility(View.VISIBLE);
		txtDetailsOf.setText("Details of " + ((CityRatingViewItem)item).getRatingName());
		txtTemperatureValue.setText(String.valueOf(data.getDataSubIndices().getResponse().getDetails().getTemperature()));
		txtHumidityValue.setText(String.valueOf(data.getDataSubIndices().getResponse().getDetails().getHumidity()));
		txtPM10Value.setText(String.valueOf(data.getDataSubIndices().getResponse().getDetails().getPm10()));
		txtGreenSpacesValue.setText(String.valueOf(data.getDataSubIndices().getResponse().getDetails().getGreenSpaces()));		
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return null;
	}
}
