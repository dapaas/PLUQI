package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sirmamobile.base.BaseFragment;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.DataCityDetailsResults;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapterListener;
import com.sirmamobile.saltlux.recycleview.RecycleViewDecoration;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;
import com.sirmamobile.saltlux.recycleview.saltlux.CityDataAdapter;
import com.sirmamobile.saltlux.recycleview.saltlux.CityDataViewItem;
import com.sirmamobile.saltlux.recycleview.saltlux.RankOfCitiesAdapter;

/**
 * Created by Martin on 5/5/2015.
 */
public class RankOfCityDetailFragment extends BaseFragment implements RecycleViewAdapterListener{

	private static final String ARG_DATA = "ARG_DATA";
	private DataCityDetailsResults data;
	public static final RankOfCityDetailFragment load(DataCityDetailsResults results, int position, RankOfCityDetailIndexListener indexListener, int index){
		RankOfCityDetailFragment df = new RankOfCityDetailFragment();
		Bundle b = new Bundle();
		b.putParcelable(ARG_DATA, results);
        df.setArguments(b);
		return df;
	}
	
	@Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	if(!(activity instanceof RankOfCityListener))
    		throw new RuntimeException("Wrong interface type");
    }
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		data = getArguments().getParcelable(ARG_DATA);
	}
    @Override
    protected View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	
       View v = inflater.inflate(R.layout.rank_city_details, container, false);
       TextView txtDate = (TextView) v.findViewById(R.id.txtDate);
       txtDate.setText(data.getEndDate());

       RecyclerView rvWorkout = (RecyclerView) v.findViewById(R.id.rvRankCities);
 	   RecyclerView.ItemDecoration itemDecoration = new RecycleViewDecoration(getActivity());
 	   rvWorkout.addItemDecoration(itemDecoration);
 	   LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
 	   mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
 	   mLayoutManager.scrollToPosition(0);
 	   rvWorkout.setLayoutManager(mLayoutManager);
 	  RankOfCitiesAdapter mAdapter = new RankOfCitiesAdapter(this);
 	  mAdapter.setNodeData(data.getTopics());
 	   rvWorkout.setAdapter(mAdapter);
 	  
 	   return v;
    }
    @Override
    public void onResume() {
    	super.onResume();
    }
    

	@Override
	public void itemClicked(View root, RecycleViewItem item) {
	}

	@Override
	public Context getContext() {
		return getActivity();
	}
}
