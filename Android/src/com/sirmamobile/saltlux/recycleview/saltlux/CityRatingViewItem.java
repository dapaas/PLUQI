package com.sirmamobile.saltlux.recycleview.saltlux;

import com.sirmamobile.saltlux.recycleview.RecycleViewItem;

public class CityRatingViewItem extends RecycleViewItem {

	String ratingName;
	double ratingValue;
	int ivIcon;

	public CityRatingViewItem(int ivIcon, String ratingName, double ratingValue) {
		super(Type.SINGLE_SELECT);
		this.ratingName = ratingName;
		this.ratingValue = ratingValue;
		this.ivIcon = ivIcon;
	}

	public String getRatingName() {
		return ratingName;
	}

	public double getRatingValue() {
		return ratingValue;
	}
	
	public int getImageIcon() {
		return ivIcon;
	}



}
