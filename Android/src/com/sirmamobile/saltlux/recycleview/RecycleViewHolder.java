package com.sirmamobile.saltlux.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem.ClickState;

public abstract class RecycleViewHolder extends RecyclerView.ViewHolder{
	
	protected RecycleViewItem item;
	private Float normalElevation = null;
	
	
    public RecycleViewHolder(View v) {
        super(v);
        itemView.setTag(this);
    }
    
    public void setClickListener(OnClickListener listener, OnLongClickListener longClick){
        itemView.setOnClickListener(listener);
        itemView.setOnLongClickListener(longClick);
    }
    
    protected void setData(Context context, RecycleViewItem item){
    	this.item = item;
    	if(!item.getClickState().equals(ClickState.DRAG)){
    		itemView.setTranslationY(0);
    		if(normalElevation != null)
    			removeDragElevation();
    	}
    	else
    		addDragElevation();
    	dataIsSet(context);
    }
    
    public void addDragElevation(){
    	if(normalElevation == null)
    		normalElevation = itemView.getElevation();
    	if(itemView.getElevation() <= normalElevation)
    		itemView.setElevation(normalElevation + itemView.getResources().getDimension(R.dimen.dp8));
    }
    
    private void removeDragElevation(){
    	itemView.setElevation(normalElevation);
    	itemView.invalidate();
    	normalElevation = null;
    }
    
    public RecycleViewItem getItem() {
		return item;
	}

	protected abstract void dataIsSet(Context context);
    public abstract void updateUI(Context context);
}
