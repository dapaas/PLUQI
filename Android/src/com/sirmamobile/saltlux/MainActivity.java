package com.sirmamobile.saltlux;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sirmamobile.base.BaseActivity;
import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.saltlux.fragments.BaseListener;
import com.sirmamobile.saltlux.http.model.CityData;
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestFragment;
import com.sirmamobile.saltlux.http.requests.GetCityDataRequestListener;


public class MainActivity extends BaseActivity implements BaseListener,
														  HomeFragmentListener,
														  GetCityDataRequestListener{
    private static final String HOME_FRAGMENT_TAG = "HOME_FRAGMENT_TAG";
	private Toolbar toolbar;
	
	@Override
	protected void onCreation(Bundle arg0, boolean fromState) {
		super.onCreation(arg0, fromState);
        setContentView(R.layout.activity_main);
        if (!fromState)
	    	replaceFragment(R.id.content_frame, HomeFragment.load(), HOME_FRAGMENT_TAG, false);
        
        RequestFragment.doRequest(getSupportFragmentManager(), GetCityDataRequestFragment.newInstance(2,2,2,2,2));

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(toolbar);

	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void demandTitle(String title) {

	}
	@Override
	public void getCityDataResponse(CityData data) {
		HomeFragment hf = (HomeFragment) getFragment(HOME_FRAGMENT_TAG);
        if(hf != null)
            hf.getCityData(data);	
	}

	@Override
	public void getCityDataError(Object error) {		
	}

}
