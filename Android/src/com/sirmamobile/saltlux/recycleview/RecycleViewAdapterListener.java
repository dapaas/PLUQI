package com.sirmamobile.saltlux.recycleview;

import android.content.Context;
import android.view.View;




public interface RecycleViewAdapterListener extends RecycleAdapterListener{
	void itemClicked(View root, RecycleViewItem item);
	Context getContext();
}
