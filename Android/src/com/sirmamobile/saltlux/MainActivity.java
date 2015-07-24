package com.sirmamobile.saltlux;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sirmamobile.base.BaseActivity;
import com.sirmamobile.base.utils.LogUtil;
import com.sirmamobile.saltlux.fragments.CityDetailsFragment;
import com.sirmamobile.saltlux.fragments.CityFragment;
import com.sirmamobile.saltlux.fragments.CityListener;
import com.sirmamobile.saltlux.fragments.HomeFragment;
import com.sirmamobile.saltlux.fragments.HomeListener;
import com.sirmamobile.saltlux.fragments.ListListener;
import com.sirmamobile.saltlux.fragments.MapListener;
import com.sirmamobile.saltlux.fragments.RankOfCityFragment;
import com.sirmamobile.saltlux.fragments.RankOfCityListener;
import com.sirmamobile.saltlux.fragments.SaltluxListener;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.http.model.CompareCities;
import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.http.model.RankCity;
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestListener;
import com.sirmamobile.saltlux.http.requests.GetCityDetailsRequestListener;
import com.sirmamobile.saltlux.http.requests.GetCompareCitiesDataRequestListener;
import com.sirmamobile.saltlux.http.requests.GetRankCityDataRequestListener;
import com.sirmamobile.saltlux.http.requests.GetSubIndicesDataRequestListener;
import com.sirmamobile.saltlux.navigation.NavigationContainer;
import com.sirmamobile.saltlux.navigation.NavigationContainerListener;
import com.sirmamobile.saltlux.navigation.NavigationType;
import com.sirmamobile.saltlux.utils.SharedPreferencesUtil;


public class MainActivity extends BaseActivity implements GetCityDataRequestListener,
														  GetRankCityDataRequestListener,
														  GetCompareCitiesDataRequestListener,
														  GetSubIndicesDataRequestListener,
														  NavigationContainerListener,
														  SaltluxListener,
														  HomeListener,
														  MapListener,
														  ListListener,
														  CityListener,
														  RankOfCityListener,
														  GetCityDetailsRequestListener{

	private static final String ARG_HOME = "ARG_HOME";
	private static final String ARG_RANK = "ARG_RANK";
	private static final String ARG_CITY = "ARG_CITY";
	private static final String ARG_CITY_DETAILS = "ARG_CITY_DETAILS";

	
	private Toolbar toolbar;
	private NavigationContainer mDrawerLayout;
	
	@Override
	protected void onCreation(Bundle arg0, boolean fromState) {
		super.onCreation(arg0, fromState);
        setContentView(R.layout.activity_main);
		
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(toolbar);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		mDrawerLayout = (NavigationContainer) findViewById(R.id.drawer_layout);
        mDrawerLayout.initNavigation(this, this);
        
        if(!fromState)
        	mDrawerLayout.setSelected(SharedPreferencesUtil.getStartingTab(), false);
	}
    
	private String prevTitle;
	
	@Override
    public void demandMenu(NavigationType type) {
    	NavigationType selected = mDrawerLayout.getSelected();
        LogUtil.log(this, type + " " + selected);
        if(!type.equals(selected)){
        	mDrawerLayout.setSelected(type, true);
        }
    }
    
    @Override
    public void demandTitle(String title) {
    	prevTitle = title;
    	getSupportActionBar().setTitle(title);
    }
    
	private void openDrawer() {
        mDrawerLayout.openDrawer(findViewById(R.id.navigation_frame));
    }

    private boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(findViewById(R.id.navigation_frame));
    }

    private boolean isDrawerVisible() {
        return mDrawerLayout.isDrawerVisible(findViewById(R.id.navigation_frame));
    }

    private void closeDrawer() {
        mDrawerLayout.closeDrawer(findViewById(R.id.navigation_frame));
    }
    
	@Override
    protected boolean handleBackButton(){

        if(isDrawerOpen()){
            closeDrawer();
            return true;
        }

        if(getBackStackSize() == 1){
            askForExit();
            return true;
        }

        return false;
    }
	
	@Override
	public void getCityDataError(Object error) {
	
	}

	@Override
	public void getCityData(CityData data, Object d) {
		HomeFragment f = (HomeFragment) getFragment(ARG_HOME);
		if(f != null)
			f.getCityData(data);
	}

	@Override
	public void getRankCityData(RankCity data) {
		RankOfCityFragment f = (RankOfCityFragment) getFragment(ARG_RANK);
		if(f != null)
			f.getCityData(data);
	}

	@Override
	public void getRankCityDataError(Object error) {

	}

	@Override
	public void getCompareCitiesData(CompareCities data) {

	}

	@Override
	public void getCompareCitiesDataError(Object error) {

	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerLayout.onOptionsItemSelected(item))
            return true;
		return super.onOptionsItemSelected(item);
    }
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerLayout.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerLayout.onConfigurationChanged(newConfig);
    }
    
    @Override
	public void onDrawerClosed(View view) {
		//invalidateOptionsMenu();
		if(prevTitle != null)
	    	getSupportActionBar().setTitle(prevTitle);
	}
	
	@Override
	public void onDrawerOpened(View drawerView) {
		//invalidateOptionsMenu();
    	getSupportActionBar().setTitle(getString(R.string.app_name));
	}

	@Override
	public void menuSelected(NavigationType type) {
		switch (type){
		    case HOME:
		    	SharedPreferencesUtil.setStartingTab(NavigationType.HOME);
		        if(getFragment(ARG_HOME) != null)
		            clearFragmentsBackStack(ARG_HOME);
		        else
		            replaceFragment(R.id.content_frame, HomeFragment.loadHome(type), ARG_HOME, true);
		        break;
		    case RANK:
		    	 if(getFragment(ARG_RANK) != null)
			            clearFragmentsBackStack(ARG_RANK);
			        else
			            replaceFragment(R.id.content_frame, RankOfCityFragment.loadRankCity(type), ARG_RANK, true);
		    	break;
		}
		closeDrawer();
	}
	
	public void cityClicked(Data city) {
		replaceFragment(R.id.content_frame, HomeFragment.loadCity(NavigationType.HOME, city), ARG_CITY, true);
	}
	
	@Override
	public void getSubIndicesDataError(Object error) {

	}
	
	@Override
	public void getSubIndicesDataResponse(CityData data) {
		CityFragment cf = (CityFragment)getFragment(ARG_CITY);
		if(cf != null)
			cf.getSubIndicesDataResponse(data);
	}

	@Override
	public void getCityDataDetailsResponse(CityData data, Object d) {
		CityDetailsFragment cf = (CityDetailsFragment)getFragment(ARG_CITY_DETAILS);
		if(cf != null)
			cf.getCityDetailsResponse(data);
	}

	@Override
	public void getCityDataDetailsError(Object error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cityDetailsCliked(String city) {
		replaceFragment(R.id.content_frame, CityDetailsFragment.loadCityDetails(NavigationType.HOME, city), ARG_CITY_DETAILS, true);
	}
}
