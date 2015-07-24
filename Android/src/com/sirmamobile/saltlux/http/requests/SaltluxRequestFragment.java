package com.sirmamobile.saltlux.http.requests;

import android.widget.Toast;

import com.sirmamobile.base.fragments.RequestFragment;

public abstract class SaltluxRequestFragment extends RequestFragment {
	
	@Override
	protected final void onError(Object error) {
		if (error != null && error instanceof Exception)
	        Toast.makeText(getActivity(), ((Exception) error).getMessage(), Toast.LENGTH_LONG).show();
		else if(error != null && error instanceof Error)
	        Toast.makeText(getActivity(), ((Error)error).getMessage(), Toast.LENGTH_LONG).show();
		else
	        Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_LONG).show();
	}
}
