package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestFragment;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapterListener;
import com.sirmamobile.saltlux.recycleview.RecycleViewDecoration;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;
import com.sirmamobile.saltlux.recycleview.saltlux.CityDataAdapter;

/**
 * Created by Martin on 5/5/2015.
 */
public class ListFragment extends SaltluxFragment implements RecycleViewAdapterListener,
																OnSeekBarChangeListener{

	private static final String ARG_CITY_DATA = "ARG_CITY_DATA";
	
	public static final ListFragment load(){
		ListFragment df = new ListFragment();
		Bundle b = new Bundle();
        df.setArguments(b);
		return df;
	}
	
	@Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	if(!(activity instanceof ListListener))
    		throw new RuntimeException("Wrong interface type");
    }
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		data = getArguments().getParcelable(ARG_CITY_DATA);
		setHasOptionsMenu(true);
	}
	
	private CityDataAdapter mAdapter;
    private RecyclerView rvWorkout;
    private TextView txtSbHealth;
    private TextView txtSbEnvironmentValue;
    private TextView txtSbEducationValue;
    private TextView txtSbCulturalSatisfactionValue;
    private TextView txtSbTrafficSatisfactionValue;
    
    @Override
    protected View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.list, container, false);
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


 	   
 	   rvWorkout = (RecyclerView) v.findViewById(R.id.workout);
 	   RecyclerView.ItemDecoration itemDecoration = new RecycleViewDecoration(getActivity());
 	   rvWorkout.addItemDecoration(itemDecoration);
 	   LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
 	   mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
 	   mLayoutManager.scrollToPosition(0);
 	   rvWorkout.setLayoutManager(mLayoutManager);
 	   mAdapter = new CityDataAdapter(this);
 	   rvWorkout.setAdapter(mAdapter);
 	  
 	   return v;
    }

    private CityData data;
    
    @Override
    public void onResume() {
    	super.onResume();
    	if(data == null)
    		RequestFragment.doRequest(getFragmentManager(), GetCityDataRequestFragment.newInstance(2, 2, 2, 2, 2), GetCityDataRequestFragment.CALLER_LIST);
    	else
    		mAdapter.setCityData(data.getData());
    }
    
    public void getCityData(CityData data){
    	System.out.println("asdasd");
    	this.data = data;
    	getArguments().putParcelable(ARG_CITY_DATA, data);
    	mAdapter.setCityData(data.getData());
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
    
    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.home_list, menu);
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.map)
			((ListListener)getActivity()).swicthToMap();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemClicked(View root, RecycleViewItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return getActivity();
	}
}
