package com.sirmamobile.saltlux.recycleview.saltlux;

import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;

public class CityDataViewItem extends RecycleViewItem {

	Data cityData;
	private int color;

	public CityDataViewItem(Data cityData, int color) {
		super(Type.CLICKABLE);
		this.cityData = cityData;
		this.color = color;
	}

	public Data getData() {
		return cityData;
	}
	
	public int getColor() {
		return color;
	}



}
