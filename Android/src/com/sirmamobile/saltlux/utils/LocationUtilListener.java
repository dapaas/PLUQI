package com.sirmamobile.saltlux.utils;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;

public interface LocationUtilListener {
	void onConnectionFailed(ConnectionResult result);
	void onConnected(Bundle connectionHint);
	void onDisconnected();
	void onLocationChanged(Location location);
}
