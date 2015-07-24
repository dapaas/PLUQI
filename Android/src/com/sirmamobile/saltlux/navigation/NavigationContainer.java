package com.sirmamobile.saltlux.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.sirmamobile.saltlux.R;

public class NavigationContainer extends DrawerLayout implements OnNavigationItemSelectedListener{

    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationContainerListener listener;
    private NavigationView navigationView;
    
	public NavigationContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationContainer(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context){
		setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
	}

	private MenuItem map;
	private MenuItem list;
	private MenuItem lines;
	private MenuItem about;
	
	public void initNavigation(Activity activity, NavigationContainerListener listener){
		this.listener = listener;
		navigationView = (NavigationView) findViewById(R.id.navigation_frame);
		
		navigationView.setNavigationItemSelectedListener(this);
		
		mDrawerToggle = new ActionBarDrawerToggle(activity, 
        		this,
        		R.string.app_name, 
        		R.string.empty) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				NavigationContainer.this.listener.onDrawerClosed(view);
			}
			

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				NavigationContainer.this.listener.onDrawerOpened(drawerView);
			}
		};
		
		map = navigationView.getMenu().getItem(0).getSubMenu().findItem(R.id.map);
		list = navigationView.getMenu().getItem(0).getSubMenu().findItem(R.id.list);
		lines = navigationView.getMenu().findItem(R.id.rank);
		about = navigationView.getMenu().findItem(R.id.compare);

		setDrawerListener(mDrawerToggle);
	}

	public NavigationType getSelected(){
		if(map.isChecked())
			return NavigationType.fromID(map.getItemId());
		if(list.isChecked())
			return NavigationType.fromID(list.getItemId());
		if(lines.isChecked())
			return NavigationType.fromID(lines.getItemId());
		if(about.isChecked())
			return NavigationType.fromID(about.getItemId());
		return null;
	}
	
	public void setSelected(NavigationType nt, boolean silent){
		
		map.setChecked(false);
		list.setChecked(false);
		lines.setChecked(false);
		about.setChecked(false);
		
		switch(nt.getMenuItemID()){
			case R.id.map:
				about.setChecked(true);
				map.setChecked(true);
				about.setChecked(false);
				if(!silent)
					onNavigationItemSelected(map);
				break;
			case R.id.list:
				about.setChecked(true);
				list.setChecked(true);
				about.setChecked(false);
				if(!silent)
					onNavigationItemSelected(list);
				break;
			case R.id.rank:
				lines.setChecked(true);
				if(!silent)
					onNavigationItemSelected(lines);
				break;
			case R.id.compare:
				about.setChecked(true);
				if(!silent)
					onNavigationItemSelected(about);
				break;
		}
	}
	
	@Override
	public boolean onNavigationItemSelected(MenuItem arg0) {
		lines.setChecked(true);
		about.setChecked(true);
		arg0.setChecked(true);
		if(!arg0.equals(map))
			map.setChecked(false);
		if(!arg0.equals(list))
			list.setChecked(false);
		if(!arg0.equals(lines))
			lines.setChecked(false);
		if(!arg0.equals(about))
			about.setChecked(false);
		
		NavigationType nt = NavigationType.fromID(arg0.getItemId());
		listener.menuSelected(nt);

		return true;
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void syncState() {
		mDrawerToggle.syncState();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return mDrawerToggle.onOptionsItemSelected(item);
	}
}
