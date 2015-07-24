package com.sirmamobile.saltlux.navigation;

import android.view.View;

public interface NavigationContainerListener {
	
	public void onDrawerClosed(View view);
	public void onDrawerOpened(View drawerView);
	public void menuSelected(NavigationType type);
}
