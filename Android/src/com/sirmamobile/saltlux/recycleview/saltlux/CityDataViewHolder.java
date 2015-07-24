package com.sirmamobile.saltlux.recycleview.saltlux;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
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
import com.sirmamobile.saltlux.recycleview.RecycleViewHolder;

public class CityDataViewHolder extends RecycleViewHolder{

	public CardView cv;
	public TextView txtTitle;
	public ProgressBar pbIndex;
	
	public CityDataViewHolder(View v) {
		super(v);
        cv = (CardView) v.findViewById(R.id.card_view);
        txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        pbIndex = (ProgressBar) v.findViewById(R.id.pbIndex);
	}
	
	public void setOnIconClickListener(OnClickListener listener){
	}
	
	public void setProgressColor(int color) {
	    final float[] roundedCorners = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
	    ShapeDrawable pgDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners,     null, null));
	    pgDrawable.getPaint().setColor(color);
	    ClipDrawable progress = new ClipDrawable(pgDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
		pbIndex.setProgressDrawable(progress);
	}
	
	@Override
	 public void dataIsSet(final Context context) {
		CityDataViewItem cityDataItem = (CityDataViewItem) item;
		txtTitle.setText(cityDataItem.getData().getCity());
		pbIndex.setProgress((int)cityDataItem.getData().getPluqi());
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
