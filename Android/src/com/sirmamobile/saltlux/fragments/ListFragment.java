package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirmamobile.base.BaseFragment;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapterListener;
import com.sirmamobile.saltlux.recycleview.RecycleViewDecoration;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;
import com.sirmamobile.saltlux.recycleview.saltlux.CityDataAdapter;
import com.sirmamobile.saltlux.recycleview.saltlux.CityDataViewItem;

/**
 * Created by Martin on 5/5/2015.
 */
public class ListFragment extends BaseFragment implements RecycleViewAdapterListener,
																HomeFragmentChild{

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
	}
	
	private CityDataAdapter mAdapter;
    private RecyclerView rvWorkout;
    
    @Override
    protected View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	
       View v = inflater.inflate(R.layout.list, container, false);

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
    		((HomeFragment)getParentFragment()).demandData(this);
    	else
    		mAdapter.setCityData(data.getData());
    }
    
    public void getCityData(CityData data){
    	this.data = data;
    	getArguments().putParcelable(ARG_CITY_DATA, data);
    	mAdapter.setCityData(data.getData());
	}

	@Override
	public void itemClicked(View root, RecycleViewItem item) {
		((HomeFragment)getParentFragment()).citySelected(((CityDataViewItem)item).getData());
	}

	@Override
	public Context getContext() {
		return getActivity();
	}
}
