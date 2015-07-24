package com.sirmamobile.saltlux.adapters;

import java.util.HashMap;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.sirmamobile.saltlux.fragments.RankOfCityDetailFragment;
import com.sirmamobile.saltlux.fragments.RankOfCityDetailIndexListener;
import com.sirmamobile.saltlux.http.model.DataCityDetails;

public class RankCityFragmentAdapter extends FragmentStatePagerAdapter implements RankOfCityDetailIndexListener{

	private DataCityDetails others;
	private RankCityFragmentAdapterListener listener;
	private Map<Integer, Integer> indices = new HashMap<Integer, Integer>();
	
    public RankCityFragmentAdapter(FragmentManager fm, DataCityDetails others) {
        super(fm);
        this.others = others;
    }

    public void setListener(RankCityFragmentAdapterListener listener) {
		this.listener = listener;
	}

	@Override
    public Fragment getItem(int i) {
		int index = 0;
		if(indices.containsKey(i))
			index = indices.get(i);
		return RankOfCityDetailFragment.load(others.getResults().get(i), i, this, index);
    }

    @Override
    public int getCount() {
        return others.getResults().size();
    }
    
    @Override
    public void indexChanged(int position, int index) {
    	indices.put(position, index);
    }
   
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    	super.destroyItem(container, position, object);
    }
}
