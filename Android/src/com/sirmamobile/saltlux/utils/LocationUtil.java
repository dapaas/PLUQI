package com.sirmamobile.saltlux.utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.sirmamobile.base.utils.LogUtil;


public class LocationUtil implements ConnectionCallbacks, 
										OnConnectionFailedListener,
										OnMyLocationButtonClickListener,
										LocationListener{

	private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	
	private static LocationUtil locationUtil = new LocationUtil();
	
	public static boolean isLocationServiceEnabled(Context context){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		boolean gps_enabled = false;
		boolean network_enabled = false;

		try {
		    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch(Exception ex) {}

		try {
		    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch(Exception ex) {}
		
		return gps_enabled || network_enabled;
	}
	
	public synchronized static void init(Context context){
		locationUtil.initInner(context);
	}
	
	public static void addListener(LocationUtilListener object) {
		locationUtil.addListenerInner(object);
	}

	public static void removeListener(LocationUtilListener object) {
		locationUtil.removeListenerInner(object);
	}
	
	private static void activate(){
		locationUtil.activateInner();
	}
	
	private static void deactivate(){
		locationUtil.deactivateInner();
	}
	
	public static Location getLastKnownLocation(){
		return locationUtil.getLastKnownLocationInner();
	}
	
	public static void refresh(){
		locationUtil.onLocationChanged(getLastKnownLocation());
	}
	
	public static void refresh(LocationUtilListener listener){
		locationUtil.onLocationChanged(getLastKnownLocation(), listener);
	}
	
	private List<WeakReference<LocationUtilListener>> listeners = new ArrayList<WeakReference<LocationUtilListener>>();
	private GoogleApiClient mGoogleApiClient;
	
	private synchronized void initInner(Context context){
		LogUtil.log(this);
		if(mGoogleApiClient != null)
			return;
		mGoogleApiClient = new GoogleApiClient.Builder(context)
	       .addApi(LocationServices.API)
	       .build();
	}
	
	public synchronized void addListenerInner(LocationUtilListener object) {
		LogUtil.log(this);
		
		if(h.hasMessages(1))
			h.removeMessages(1);
		
		listeners.add(new WeakReference<LocationUtilListener>(object));
		clearEmptyReferences();

		if((mGoogleApiClient != null && !mGoogleApiClient.isConnected()) && listeners.size() == 1)
			activateInner();
	}

	public synchronized void removeListenerInner(LocationUtilListener object) {
		LogUtil.log(this);
		WeakReference<LocationUtilListener> toRemove = null;
		for(WeakReference<LocationUtilListener> listener : listeners)
			if(object.equals(listener.get())){
				toRemove = listener;
				break;
			}
		if(toRemove != null)
			listeners.remove(toRemove);
		clearEmptyReferences();
		
		if(listeners.size() == 0)
			h.sendEmptyMessageDelayed(1, 5000);
	}
	
	private Handler h = new Handler(){
		public void handleMessage(android.os.Message msg) {
			deactivateInner();
		};
	};
	
	//private static
	
	private synchronized Location getLastKnownLocationInner() {
		LogUtil.log(this);
		clearEmptyReferences();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected())
        	return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    	return null;
    }
	
	@Override
	public void onConnectionSuspended(int cause) {
		LogUtil.log(this);

	}
	
	@Override
	public boolean onMyLocationButtonClick() {
		LogUtil.log(this);

		return false;
	}
	
	private synchronized void activateInner(){
		LogUtil.log(this);
		mGoogleApiClient.registerConnectionCallbacks(this);
		mGoogleApiClient.registerConnectionFailedListener(this);
		mGoogleApiClient.connect();
		clearEmptyReferences();
	}
	
	private synchronized void deactivateInner(){
		LogUtil.log(this);
		mGoogleApiClient.disconnect();
		mGoogleApiClient.unregisterConnectionCallbacks(this);
		mGoogleApiClient.unregisterConnectionFailedListener(this);
		for(WeakReference<LocationUtilListener> listener : listeners)
			if(listener.get() != null)
				listener.get().onDisconnected();
		clearEmptyReferences();
	}
	
	//private Location lastKnown = null;

	@Override
	public synchronized void onConnectionFailed(ConnectionResult result) {
		LogUtil.log(this);
		for(WeakReference<LocationUtilListener> listener : listeners)
			if(listener.get() != null)
				listener.get().onConnectionFailed(result);
		clearEmptyReferences();
	}

	@Override
	public synchronized void onConnected(Bundle connectionHint) {
		LogUtil.log(this);
		LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                REQUEST,
                this);
		for(WeakReference<LocationUtilListener> listener : listeners)
			if(listener.get() != null)
				listener.get().onConnected(connectionHint);
		clearEmptyReferences();
	}

	@Override
	public synchronized void onLocationChanged(Location location) {
		LogUtil.log(this);
//		if(c++ > 3){
//			location = new Location("X");
//			location.setLatitude(42.7693619);
//			location.setLongitude(23.4481271);
//			mock = location;
//		}
//		location.setLatitude(42.6954322);
//		location.setLongitude(23.3239467);
		for(WeakReference<LocationUtilListener> listener : listeners)
			if(listener.get() != null)
				listener.get().onLocationChanged(location);
		clearEmptyReferences();
	}
	
	public synchronized void onLocationChanged(Location location, LocationUtilListener listener) {
		LogUtil.log(this);
		if(location == null)
			return;
		for(WeakReference<LocationUtilListener> l : listeners)
			if(listener.equals(l.get()))
				listener.onLocationChanged(location);
		clearEmptyReferences();
	}
	
	private synchronized void clearEmptyReferences(){
		LogUtil.log(this);
		List<Object> toRemove = new ArrayList<Object>();
		for(WeakReference<LocationUtilListener> listener : listeners)
			if(listener.get() == null)
				toRemove.add(listener);
		for(Object o : toRemove)
			listeners.remove(o);
	}
}
