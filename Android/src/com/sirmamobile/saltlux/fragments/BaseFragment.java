package com.sirmamobile.saltlux.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(!(activity instanceof BaseListener))
            throw new RuntimeException("Wrong interface type");
    }
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setHasOptionsMenu(true);
    }
    
    @Override
	public void onResume() {
		super.onResume();
		((BaseListener) getActivity()).demandTitle(demandTitle());
		onTheResume();
	}
    
    protected void onTheResume(){
    	
    }
    
    protected abstract String demandTitle();
    
}
