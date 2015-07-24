package com.sirmamobile.saltlux.navigation;

import com.sirmamobile.saltlux.R;

public enum NavigationType {

	HOME(R.id.home, R.string.home),
	RANK(R.id.rank, R.string.rank_of_cities),
	COMPARE(R.id.compare, R.string.compare_cities);
	

	private int menuItemID;
	private int resLabel;
	
	NavigationType(int menuItemID, int resLabel){
		this.resLabel = resLabel;
		this.menuItemID = menuItemID;
	}
	
	public int getResLabel() {
		return resLabel;
	}

	public int getMenuItemID() {
		return menuItemID;
	}

	private boolean isIt(int id){
		return menuItemID == id;
	}
	
	public static NavigationType fromID(int id){
		for(NavigationType nt : values())
			if(nt.isIt(id))
				return nt;
		return null;
	}
}
