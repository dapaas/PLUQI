package com.sirmamobile.saltlux.recycleview.saltlux;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapter;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapterListener;
import com.sirmamobile.saltlux.recycleview.RecycleViewHolder;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;

public class CityDataAdapter extends RecycleViewAdapter{
	
	public CityDataAdapter(RecycleViewAdapterListener listener) {
		super(listener, true);
	}
	
	@Override
	protected RecycleViewHolder getParentHolder(ViewGroup parent) {
		return null;
	}
	
	@Override
	protected RecycleViewHolder getChildHolder(ViewGroup parent) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycle_item, parent, false);
		CityDataViewHolder vh = new CityDataViewHolder(v);
        vh.setProgressColor(v.getResources().getColor(R.color.material_blue_900));
        return vh;
	}

	public void setCityData(List<Data> data){
		List<RecycleViewItem> items = new ArrayList<RecycleViewItem>();
		for(Data bs : data)
			items.add(new CityDataViewItem(bs));
		setData(items);
	}	
}
