package com.sirmamobile.saltlux.utils;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sirmamobile.base.utils.ApplicationContextProvider;
import com.sirmamobile.base.utils.LogUtil;
import com.sirmamobile.saltlux.navigation.NavigationType;

public class SharedPreferencesUtil {

    private static final String STARTING_TAB = "STARTING_TAB";
    private static final String INITIAL_LOAD = "INITIAL_LOAD";
	
    private static void setProperty(String name, Object value){
    	LogUtil.log(name + " = " + value);
        if(value == null)
            return;
        Class<? extends Object> clazz = value.getClass();
        Editor editor = getSharedPreferences().edit();
        if(clazz.equals(Boolean.class))
            editor.putBoolean(name, (Boolean)value);
        else if(clazz.equals(Float.class))
            editor.putFloat(name, (Float)value);
        else if(clazz.equals(Integer.class))
            editor.putInt(name, (Integer)value);
        else if(clazz.equals(Long.class))
            editor.putLong(name, (Long)value);
        else if(clazz.equals(String.class))
            editor.putString(name, (String)value);
        else
            editor.putString(name, value.toString());
        editor.commit();
    }

    private static final class SharedPreferencesWrapper implements SharedPreferences{

    	private SharedPreferences sp;
    	
		public SharedPreferencesWrapper(SharedPreferences sp) {
			super();
			this.sp = sp;
		}

		@Override
		public boolean contains(String key) {
			return sp.contains(key);
		}

		@Override
		public Editor edit() {
			return sp.edit();
		}

		@Override
		public Map<String, ?> getAll() {
			return sp.getAll();
		}

		@Override
		public boolean getBoolean(String key, boolean defValue) {
			boolean val = sp.getBoolean(key, defValue);;
			LogUtil.log(key + " is " + val);
			return val;
		}

		@Override
		public float getFloat(String key, float defValue) {
			float val = sp.getFloat(key, defValue);;
			LogUtil.log(key + " is " + val);
			return val;
		}

		@Override
		public int getInt(String key, int defValue) {
			int val = sp.getInt(key, defValue);;
			LogUtil.log(key + " is " + val);
			return val;
		}

		@Override
		public long getLong(String key, long defValue) {
			long val = sp.getLong(key, defValue);;
			LogUtil.log(key + " is " + val);
			return val;
		}

		@Override
		public String getString(String key, String defValue) {
			String val = sp.getString(key, defValue);;
			LogUtil.log(key + " is " + val);
			return val;
		}

		@Override
		public Set<String> getStringSet(String arg0, Set<String> arg1) {
			Set<String> val = sp.getStringSet(arg0, arg1);;
			LogUtil.log(arg0 + " is " + val);
			return val;
		}

		@Override
		public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
			sp.registerOnSharedPreferenceChangeListener(listener);
		}

		@Override
		public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
			sp.unregisterOnSharedPreferenceChangeListener(listener);
		}
    }
    
    private static String sp = "com.sirmamobile.sofspirka";

    public static void init(Context context){
        sp = context.getPackageName();
    }

    private static void removeProperty(String name){
    	LogUtil.log(name);
        Editor editor = getSharedPreferences().edit();
        editor.remove(name);
        editor.commit();
    }

    private static SharedPreferences getSharedPreferences(){
        SharedPreferences spp = ApplicationContextProvider.getContext().getSharedPreferences(sp, Context.MODE_MULTI_PROCESS);
        return new SharedPreferencesWrapper(spp);
    }

    public static synchronized void clearAll(){
        Editor editor = getSharedPreferences().edit();
        editor.clear().commit();
    }
    
    public static synchronized void initialLoad() {
        setProperty(INITIAL_LOAD, true);
    }

    public static synchronized boolean hasInitialLoad() {
    	return getSharedPreferences().getBoolean(INITIAL_LOAD, false);
    }

    public static synchronized void removeInitialLoad() {
        removeProperty(INITIAL_LOAD);
    }
    
    public static synchronized void setStartingTab(NavigationType type) {
        setProperty(STARTING_TAB, type.name());
    }

    public static synchronized NavigationType getStartingTab() {
    	return NavigationType.valueOf(getSharedPreferences().getString(STARTING_TAB, NavigationType.HOME.name()));
    }

    public static synchronized void removeStartingTab() {
        removeProperty(STARTING_TAB);
    }
}