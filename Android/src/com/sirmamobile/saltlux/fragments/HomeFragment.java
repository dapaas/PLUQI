package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestFragment;

public class HomeFragment extends SaltluxFragment implements OnSeekBarChangeListener{

	private static final String TAG_LIST = "TAG_LIST";
	private static final String TAG_MAP = "TAG_MAP";
	
	private static final String ARG_CITY_DATA = "ARG_CITY_DATA";
	private static final String ARG_MODE = "ARG_MODE";
	
	private static enum Mode {
		LIST,
		MAP
	}
	
	public static final HomeFragment load(){
		HomeFragment df = new HomeFragment();
		Bundle b = new Bundle();
        df.setArguments(b);
		return df;
	}
	
	@Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	if(!(activity instanceof HomeListener))
    		throw new RuntimeException("Wrong interface type");
    }
	
	private CityData data;
	private Mode mode;
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		data = getArguments().getParcelable(ARG_CITY_DATA);
		mode = Mode.valueOf(getArguments().getString(ARG_MODE, Mode.LIST.name()));
		setHasOptionsMenu(true);
	}

    private TextView txtSbHealth;
    private TextView txtSbEnvironmentValue;
    private TextView txtSbEducationValue;
    private TextView txtSbCulturalSatisfactionValue;
    private TextView txtSbTrafficSatisfactionValue;
    
	@Override
    protected View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.home, container, false);
 	   SeekBar sbHealth = (SeekBar) v.findViewById(R.id.sbHealthcare);
 	   sbHealth.setOnSeekBarChangeListener(this);
 	   SeekBar sbEnvironment = (SeekBar) v.findViewById(R.id.sbEnvironment);
 	   sbEnvironment.setOnSeekBarChangeListener(this);
 	   SeekBar sbEducation = (SeekBar) v.findViewById(R.id.sbEducation);
 	   sbEducation.setOnSeekBarChangeListener(this);
 	   SeekBar sbCulturalSatisfaction = (SeekBar) v.findViewById(R.id.sbCulturalSatisfaction);
 	   sbCulturalSatisfaction.setOnSeekBarChangeListener(this);
 	   SeekBar sbTrafficSatisfaction = (SeekBar) v.findViewById(R.id.sbTrafficSatisfaction);
 	   sbTrafficSatisfaction.setOnSeekBarChangeListener(this);
 	   
 	   txtSbHealth = (TextView) v.findViewById(R.id.txtSbHealcareValue);
 	   txtSbEnvironmentValue = (TextView) v.findViewById(R.id.txtSbEnvironmentValue);
 	   txtSbEducationValue = (TextView) v.findViewById(R.id.txtSbEducationValue);
 	   txtSbCulturalSatisfactionValue = (TextView) v.findViewById(R.id.txtSbCulturalSatisfactionValue);
 	   txtSbTrafficSatisfactionValue = (TextView) v.findViewById(R.id.txtSbTrafficSatisfactionValue);
 	  
 	   return v;
    }
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.home, menu);
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		MenuItem mi = menu.findItem(R.id.home);
		switch(mode){
			case LIST:
				mi.setIcon(R.drawable.ic_map_white_48dp);
				mi.setTitle(R.string.map);
				break;
			case MAP:
				mi.setIcon(R.drawable.ic_list_white_48dp);
				mi.setTitle(R.string.list);
				break;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.home){
			switch(mode){
				case LIST:
					mode = Mode.MAP;
					break;
				case MAP:
					mode = Mode.LIST;
					break;
			}
			getArguments().putString(ARG_MODE, mode.name());
			getActivity().invalidateOptionsMenu();
			switchView();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public void onResume() {
    	super.onResume();
    	switchView();
    }
	
	private void switchView(){
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		switch(mode){
			case LIST:
		        transaction.replace(R.id.content, ListFragment.load(), TAG_LIST);
		        transaction.commit();
				break;
			case MAP:
		        transaction.replace(R.id.content, MapFragment.load(), TAG_MAP);
		        transaction.commit();
				break;
		}
	}
	
	public void demandData(HomeFragmentChild f){
		if(data == null)
    		RequestFragment.doRequest(getFragmentManager(), GetCityDataRequestFragment.newInstance(2, 2, 2, 2, 2), GetCityDataRequestFragment.CALLER_LIST);
    	else
    		f.getCityData(data);
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		switch (seekBar.getId()) {
		case R.id.sbHealthcare:
			txtSbHealth.setText(String.valueOf(progress));
			break;
		case R.id.sbEducation:
			txtSbEducationValue.setText(String.valueOf(progress));
			break;
		case R.id.sbEnvironment:
			txtSbEnvironmentValue.setText(String.valueOf(progress));
			break;
		case R.id.sbCulturalSatisfaction:
			txtSbCulturalSatisfactionValue.setText(String.valueOf(progress));
			break;
		case R.id.sbTrafficSatisfaction:
			txtSbTrafficSatisfactionValue.setText(String.valueOf(progress));
			break;
		default:
			break;
		}
	}
	
	public void getCityData(CityData data){
    	this.data = data;
    	getArguments().putParcelable(ARG_CITY_DATA, data);

    	MapFragment mf = (MapFragment) getChildFragmentManager().findFragmentByTag(TAG_MAP);
    	if(mf != null)
    		mf.getCityData(data);
    	
    	ListFragment lf = (ListFragment) getChildFragmentManager().findFragmentByTag(TAG_LIST);
    	if(lf != null)
    		lf.getCityData(data);
	}

	public void citySelected(Data city){
		((HomeListener)getActivity()).cityClicked(city);
	}
	
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
}
