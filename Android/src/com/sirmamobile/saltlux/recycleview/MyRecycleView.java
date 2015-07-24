package com.sirmamobile.saltlux.recycleview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyRecycleView extends RecyclerView implements OnTouchListener{
	
	public MyRecycleView(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
		init(arg0);
	}

	public MyRecycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyRecycleView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context){
		//setOnTouchListener(this);
	}
	
	@Override
	public void setAdapter(Adapter adapter) {
		if(!(adapter instanceof RecycleViewAdapter))
			throw new RuntimeException("The adapter must extends: " + RecycleViewAdapter.class.getName());
		else
			super.setAdapter(adapter);
		if(((RecycleViewAdapter)adapter).isIdDraggable())
			addOnItemTouchListener(new OnItemTouchListener() {
				
				Float oldDeltaY = null;
				
				private Handler handler = new Handler(){
					@Override
					public void handleMessage(Message msg) {
						if(msg.what == 0){
							scrollToPosition(msg.arg1 + 1);
						}
						else if(msg.what == 1){
							scrollToPosition(msg.arg1 - 1);
						}
						//handler.sendMessageDelayed(msg, 500);
					}
				};
				
				@Override
				public void onTouchEvent(RecyclerView arg0, MotionEvent event) {
					
					if(getAdapter() == null)
						return;
					
					if(event.getAction() == MotionEvent.ACTION_MOVE){
						
						int dragItemAdapterPosition = ((RecycleViewAdapter)getAdapter()).getDragItemPosition();
						if(dragItemAdapterPosition == -1)
							return;
						RecycleViewHolder dragViewHolder = (RecycleViewHolder) findViewHolderForAdapterPosition(dragItemAdapterPosition);
						View dragView = dragViewHolder.itemView;
	
						//if(elevation == null)
							//elevation = dragViewHolder.itemView.getElevation();
						dragViewHolder.addDragElevation();
						
						float deltaY = event.getY() - (dragView.getTop() + dragView.getHeight() / 2);
						
						dragView.setTranslationY(0);
						float dragViewYline = dragView.getTop() + dragView.getHeight() / 2 + deltaY;
						View lyingView = findChildViewUnder(0, dragViewYline);
						dragView.setTranslationY(deltaY);
						
						if(lyingView != null && !dragView.equals(lyingView) && oldDeltaY != null){
							
							boolean isDragDown = deltaY > oldDeltaY;
							
							int lyingViewYline = lyingView.getTop() + lyingView.getHeight() / 2;
							final int lyingItemAdapterPosition = ((ViewHolder)lyingView.getTag()).getAdapterPosition();
	
							if(isDragDown && dragViewYline > lyingViewYline  && lyingItemAdapterPosition > dragItemAdapterPosition){
								((RecycleViewAdapter)getAdapter()).shiftAllUp(dragItemAdapterPosition, lyingItemAdapterPosition);
								if(indexOfChild(lyingView) >= getChildCount() - 1 && lyingItemAdapterPosition < getAdapter().getItemCount() - 1){
									if(handler.hasMessages(1))
										handler.removeMessages(1);
									if(!handler.hasMessages(0)){
										Message m = new Message();
										m.what = 0;
										m.arg1 = lyingItemAdapterPosition;
										handler.sendMessageDelayed(m, 200);
									}
								}
								else{
									if(handler.hasMessages(0))
										handler.removeMessages(0);
									if(handler.hasMessages(1))
										handler.removeMessages(1);
								}
							}
							else if(!isDragDown && dragViewYline < lyingViewYline && lyingItemAdapterPosition < dragItemAdapterPosition){
								((RecycleViewAdapter)getAdapter()).shiftAllDown(dragItemAdapterPosition, lyingItemAdapterPosition);
								if(indexOfChild(lyingView) <= 0 && lyingItemAdapterPosition > 0){
									if(handler.hasMessages(0))
										handler.removeMessages(0);
									if(!handler.hasMessages(1)){
										Message m = new Message();
										m.what = 1;
										m.arg1 = lyingItemAdapterPosition;
										handler.sendMessageDelayed(m, 200);
									}
								}
								else{
									if(handler.hasMessages(0))
										handler.removeMessages(0);
									if(handler.hasMessages(1))
										handler.removeMessages(1);
								}
							}
						}
	
						oldDeltaY = deltaY;
					}
					if(event.getAction() == MotionEvent.ACTION_UP){
						((RecycleViewAdapter)getAdapter()).dragDone();
					}
				}
				
				@Override
				public void onRequestDisallowInterceptTouchEvent(boolean arg0) {
	
				}
				
				@Override
				public boolean onInterceptTouchEvent(RecyclerView arg0, MotionEvent arg1) {
					return ((RecycleViewAdapter)getAdapter()).getDragItem() != null;
				}
			});
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		return false;
	}
}
