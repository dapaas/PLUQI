package com.sirmamobile.saltlux.fragments;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.http.model.Data;

/**
 * Created by Martin on 5/5/2015.
 */
public class MapFragment extends BaseMapFragment implements OnMapReadyCallback, 
																		OnMyLocationButtonClickListener,
																		OnInfoWindowClickListener,
																		HomeFragmentChild{
	
	private static final String ARG_CITY_DATA = "ARG_CITY_DATA";
	
	public static final MapFragment load(){
		MapFragment df = new MapFragment();
		Bundle b = new Bundle();
        df.setArguments(b);
		return df;
	}
	
	@Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	if(!(activity instanceof MapListener))
    		throw new RuntimeException("Wrong interface type");
    }
	
	private CityData data;
	
	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		data = getArguments().getParcelable(ARG_CITY_DATA);
	}
    
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
    		boolean fromState, boolean firstCall, Bundle savedInstanceState) {
    	return inflater.inflate(R.layout.map, container, false);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	if(data == null)
    		((HomeFragment)getParentFragment()).demandData(this);
    	else
    		fillData();
    }
    
    @Override
    public void getCityData(CityData data){
    	this.data = data;
    	getArguments().putParcelable(ARG_CITY_DATA, data);
    	fillData();
	}

    @Override
    protected MapView getMapView(View v) {
    	return (MapView) v.findViewById(R.id.map);
    }
 
    private boolean initialLoad = true;
    
    @Override
    protected void mapIsReady(GoogleMap googleMap) {
    	fillData();
    }
    
    private Map<Marker, Data> markers = new HashMap<Marker, Data>();

    private void fillData(){

    	if(!isMapReady)
    		return;

    	if(data == null)
    		return;

    	mapView.getMap().clear();
    	markers.clear();
    	
    	LatLngBounds.Builder builder = new LatLngBounds.Builder();
    	//BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_bg);
    	
    	float d = (int) getActivity().getResources().getDimension(R.dimen.dp15);
    	int w = (int) getActivity().getResources().getDimension(R.dimen.dp2);
    	
    	Paint p = new Paint();
    	p.setColor(0x80FF515D);
    	
    	Paint p1 = new Paint();
    	p1.setStyle(Style.STROKE);
    	p1.setStrokeWidth(w);
    	p1.setAntiAlias(true);
    	p1.setColor(0xFFFF515D);
    	
    	for(Data stop: data.getData()){

    		int dd = (int) (d + d * (stop.getPluqi() / (float)10));
    		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        	Bitmap bmp = Bitmap.createBitmap(dd, dd, conf);
        	Canvas canvas = new Canvas(bmp);
        	canvas.drawCircle(dd / 2, dd / 2, dd / 2, p);
        	canvas.drawCircle(dd / 2, dd / 2, dd / 2 - w / 2, p1);
        	
	    	LatLng l = new LatLng(stop.getLat(), stop.getLon());
	    	Marker m = mapView.getMap().addMarker(new MarkerOptions()
	        .position(l)
	        .title(stop.getCity())
	        .icon(BitmapDescriptorFactory.fromBitmap(bmp))
	        .snippet(String.valueOf(stop.getPluqi())));
	    	builder.include(m.getPosition());

	    	markers.put(m, stop);
	    	
	    	bmp.recycle();
		}
    	
    	if(initialLoad){
    		initialLoad = false;
			
			LatLngBounds bounds = builder.build();
	    	CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, (int) getResources().getDimension(R.dimen.dp30));
	    	mapView.getMap().moveCamera(cu);
    	}
    }
    
    @Override
    public void onInfoWindowClick(Marker marker) {
    	((HomeFragment)getParentFragment()).citySelected(markers.get(marker));
    }
}
