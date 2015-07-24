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

public class RankOfCitiesViewHolder extends RecycleViewHolder{

	public CardView cv;
	public TextView txtTitle;
	public TextView txtNumber;

	
	public RankOfCitiesViewHolder(View v) {
		super(v);
        cv = (CardView) v.findViewById(R.id.card_view);
        txtTitle = (TextView) v.findViewById(R.id.txtCityTitle);
        txtNumber = (TextView) v.findViewById(R.id.txtNumber);

	}
	
	public void setOnIconClickListener(OnClickListener listener){
	}
	
	@Override
	 public void dataIsSet(final Context context) {
		RankOfCitiesViewItem rankCitiesItem = (RankOfCitiesViewItem) item;
		txtTitle.setText(rankCitiesItem.getNode().getNode());
		txtNumber.setText(String.valueOf(rankCitiesItem.getPosition()));
		txtNumber.setBackgroundColor(rankCitiesItem.getPosition() <= 3 ? context.getResources().getColor(R.color.material_red_600) : context.getResources().getColor(R.color.material_blue_800));
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
