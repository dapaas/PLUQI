package com.sirmamobile.saltlux.recycleview.saltlux;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.recycleview.RecycleViewHolder;

public class CityRatingViewHolder extends RecycleViewHolder{

	public CardView cv;
	public TextView txtRatingItem;
	public TextView txtRatingValueItem;
	public ImageView ivIcon;

	
	public CityRatingViewHolder(View v) {
		super(v);
        cv = (CardView) v.findViewById(R.id.card_view);
        txtRatingItem = (TextView) v.findViewById(R.id.txtRatingItem);
        txtRatingValueItem = (TextView) v.findViewById(R.id.txtRatingValueItem);
        ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
	}
	
	public void setOnIconClickListener(OnClickListener listener){
	}
	
	@Override
	 public void dataIsSet(final Context context) {
		CityRatingViewItem cityDataItem = (CityRatingViewItem) item;
		txtRatingItem.setText(cityDataItem.getRatingName());
		txtRatingValueItem.setText(String.valueOf(cityDataItem.getRatingValue()));
		ivIcon.setBackgroundResource(cityDataItem.getImageIcon());
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
