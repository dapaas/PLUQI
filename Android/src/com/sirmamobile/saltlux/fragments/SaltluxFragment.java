package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.os.Bundle;

import com.sirmamobile.base.BaseFragment;
import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.navigation.NavigationType;

/**
 * Created by Martin on 5/7/2015.
 */
public abstract class SaltluxFragment extends BaseFragment {

    private static final String ARG_TYPE = "ARG_TYPE";
    
    public static final SaltluxFragment loadHome(NavigationType nt){
        HomeFragment nf = HomeFragment.load();
        return load(nf, nt);
    }
    
    public static final SaltluxFragment loadCity(NavigationType nt, Data city){
        CityFragment nf = CityFragment.load(city);
        return load(nf, nt);
    }
    
    public static final SaltluxFragment loadCityDetails(NavigationType nt, String city){
    	CityDetailsFragment nf = CityDetailsFragment.load(city);
        return load(nf, nt);
    }
    
    public static final SaltluxFragment loadRankCity(NavigationType nt) {
    	RankOfCityFragment rf = RankOfCityFragment.load();
    	return load(rf, nt);
    }
    
    private static final SaltluxFragment load(SaltluxFragment f, NavigationType nt){
        f.getArguments().putString(ARG_TYPE, nt.name());
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof SaltluxListener))
            throw new RuntimeException("Wrong interface type");
    }

    private NavigationType type;

    @Override
    protected void onCreation(boolean fromState, Bundle savedInstanceState) {
        super.onCreation(fromState, savedInstanceState);
        type = NavigationType.valueOf(getArguments().getString(ARG_TYPE));
    }
    
    @Override
    public void onResume() {
        super.onResume();
        ((SaltluxListener)getActivity()).demandMenu(type);
        ((SaltluxListener)getActivity()).demandTitle(demandTitle());
    }
    
    protected String demandTitle(){
    	return getString(type.getResLabel());
    }
}
