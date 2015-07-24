package com.sirmamobile.saltlux.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.sirmamobile.base.BaseFragment;

/**
 * Created by Martin on 5/5/2015.
 */
public abstract class BaseMapFragment extends BaseFragment implements OnMapReadyCallback, 
																		OnMyLocationButtonClickListener,
																		OnInfoWindowClickListener {
	
	private static final String ARG_CP = "ARG_CP";
	
	protected MapView mapView;
	private CameraPosition cp;
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		if(fromState)
			cp = savedInstanceState.getParcelable(ARG_CP); 
	}
	
    @Override
    protected final View onViewCreation(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	View v = createView(inflater, container, fromState, firstCall, savedInstanceState);

    	mapView = getMapView(v);
    	isMapReady = false;
    	mapView.onCreate(savedInstanceState);
    	
        mapView.getMapAsync(this);
        
        return v;
    }
    
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, boolean fromState, boolean firstCall, Bundle savedInstanceState);
    protected abstract MapView getMapView(View v);
    
    @Override
    public void onResume() {
    	super.onResume();
        mapView.onResume();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	mapView.onPause();
    }
    
    protected boolean isMapReady;
    
    @Override
    public final void onMapReady(GoogleMap googleMap) {
 
    	googleMap.setMyLocationEnabled(true);
    	googleMap.setOnMyLocationButtonClickListener(this);
    	googleMap.setOnInfoWindowClickListener(this);
    	isMapReady = true;
    	googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {			
			@Override
			public void onCameraChange(CameraPosition position) {
				cp = position;
			}
		});
    	if(cp != null)
    		googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
    	
    	if(mapView.getWidth() == 0 && mapView.getHeight() == 0)
    		return;
    	
    	mapIsReady(googleMap);
    }
    
    @Override
    public boolean onMyLocationButtonClick() {

    	return false;
    }

    @Override
    public void onDestroy() {
        if(mapView != null)
        	mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mapView != null)
        	mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(mapView != null){
        	mapView.onSaveInstanceState(outState);
        	if(cp != null)
        		outState.putParcelable(ARG_CP, cp);
        }
        super.onSaveInstanceState(outState);
    }
    
    protected abstract void mapIsReady(GoogleMap googleMap);
}
