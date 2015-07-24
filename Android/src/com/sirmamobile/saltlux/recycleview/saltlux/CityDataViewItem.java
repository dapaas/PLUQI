package com.sirmamobile.saltlux.recycleview.saltlux;

import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;

public class CityDataViewItem extends RecycleViewItem{
	
	Data cityData;

	public CityDataViewItem(Data cityData) {
		super(Type.CLICKABLE);
		this.cityData = cityData;
	}

	public Data getData() {
		return cityData;
	}
}
