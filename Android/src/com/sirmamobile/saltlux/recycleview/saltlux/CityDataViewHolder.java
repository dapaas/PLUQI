package com.sirmamobile.saltlux.recycleview.saltlux;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.custom.CustomProgressBar;
import com.sirmamobile.saltlux.recycleview.RecycleViewHolder;

public class CityDataViewHolder extends RecycleViewHolder{

	public CardView cv;
	public TextView txtTitle;
//	public ProgressBar pbIndex;
	public CustomProgressBar customProgress;
	
	public CityDataViewHolder(View v) {
		super(v);
        cv = (CardView) v.findViewById(R.id.card_view);
        txtTitle = (TextView) v.findViewById(R.id.txtTitle);
//        pbIndex = (ProgressBar) v.findViewById(R.id.pbIndex);
        customProgress = (CustomProgressBar) v.findViewById(R.id.customProgress);

	}
	
	public void setOnIconClickListener(OnClickListener listener){
	}
	
	@Override
	 public void dataIsSet(final Context context) {
		CityDataViewItem cityDataItem = (CityDataViewItem) item;
		txtTitle.setText(cityDataItem.getData().getCity());
	    customProgress.setMaxValue(10);
	    customProgress.setProgressValue((int)cityDataItem.getData().getPluqi());
	    customProgress.setStrokeHeight(100);
	    customProgress.setColor(context.getResources().getColor(cityDataItem.getColor()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    	    big(context);
    	} else {
    		small(context);
    	}
    }
	
	@Override
	public void updateUI(Context context) {
		
	}
	
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void big(Context context){
    	
    }
    
    private void small(Context context){

    }
}
