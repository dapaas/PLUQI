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

public class CityRatingDataAdapter extends RecycleViewAdapter {

	public CityRatingDataAdapter(RecycleViewAdapterListener listener) {
		super(listener, true);
	}

	@Override
	protected RecycleViewHolder getParentHolder(ViewGroup parent) {
		return null;
	}

	@Override
	protected RecycleViewHolder getChildHolder(ViewGroup parent) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.city_rating_item, parent, false);
		CityRatingViewHolder vh = new CityRatingViewHolder(v);
		return vh;
	}

	public void setRatingData(Data data) {
		List<RecycleViewItem> items = new ArrayList<RecycleViewItem>();
		items.add(new CityRatingViewItem(R.drawable.ic_edu, "Education:", data.getEducation()));
		items.add(new CityRatingViewItem(R.drawable.ic_envi, "Environment:", data.getEnvironment()));
		items.add(new CityRatingViewItem(R.drawable.ic_health, "Healthcare:", data.getHealthcare()));
		items.add(new CityRatingViewItem(R.drawable.ic_culture, "Cultural:", data.getCulturalSatisfaction()));
		items.add(new CityRatingViewItem(R.drawable.ic_satisfaction, "Traffic:", data.getTrafficSatisfaction()));
		
		setData(items);
	}

}
