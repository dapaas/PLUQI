package com.sirmamobile.saltlux.recycleview;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import com.sirmamobile.saltlux.recycleview.RecycleViewItem.ClickState;

public abstract class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder>{
	
    private List<RecycleViewItem> items = new ArrayList<RecycleViewItem>();
    private List<RecycleViewItem> visible = new ArrayList<RecycleViewItem>();
    private boolean idDraggable = false;
    
	private RecycleViewAdapterListener listener;
    private OnClickListener clickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
        	RecycleViewHolder vh = (RecycleViewHolder) v.getTag();
        	RecycleViewItem item = vh.getItem();
        	if(item.getType().equals(RecycleViewItem.Type.ALLWAYS_SELECTED) || item.getType().equals(RecycleViewItem.Type.ALLWAYS_UNSELECTED))
        		return;
        	if(item.isParent())
	            manageParentClick(item);
            else
            	manageChildClick(v, item, false);
        }
    };
    
    private OnLongClickListener longClick = new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			if(!idDraggable)
				return true;
			RecycleViewHolder vh = (RecycleViewHolder) v.getTag();
        	RecycleViewItem item = vh.getItem();
        	item.drag();
			return false;
		}
	};
	
    public boolean isIdDraggable() {
		return idDraggable;
	}

	public void setIdDraggable(boolean idDraggable) {
		this.idDraggable = idDraggable;
		if(!idDraggable)
			dragDone();
	}

	private void manageParentClick(RecycleViewItem item){
    	item.clicked();
    	int position = visible.indexOf(item);
    	int count = item.size();
		manageVisibles();
		notifyItemChanged(position);
    	if(item.getClickState().equals(ClickState.SELECTED)){
    		notifyItemRangeInserted(position + 1, count);
    	}
    	else if(item.getClickState().equals(ClickState.UNSELECTED)){
    		notifyItemRangeRemoved(position + 1, count);
    	}
    }
    
    private void manageChildClick(View v, RecycleViewItem item, boolean silentSelect){
    	item.clicked();
    	int position = visible.indexOf(item);
		if(!childMultiselect){
			for(int i = 0; i < visible.size(); i++){
				RecycleViewItem rvi = visible.get(i);
				if(!rvi.equals(item) && (rvi.getClickState().equals(ClickState.SELECTED) || rvi.getClickState().equals(ClickState.REPEAT))){
					rvi.clearState();
					notifyItemChanged(i);
				}
			}
		}
		notifyItemChanged(position);
		if(!silentSelect)
			listener.itemClicked(v, item);
    }
    
    private boolean childMultiselect = false;
    private boolean parentMultiselect = true;
    
    public RecycleViewAdapter(RecycleViewAdapterListener listener, boolean childMultiselect) {
		super();
		this.listener = listener;
		this.childMultiselect = childMultiselect;
	}

	@Override
    public final RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecycleViewHolder  vh = null;
		if(viewType == PARENT)
			vh = getParentHolder(parent);
		else 
			vh = getChildHolder(parent);
		vh.setClickListener(clickListener, longClick);
		return vh;
    }

	protected RecycleViewAdapterListener getListener() {
		return listener;
	}

	protected abstract RecycleViewHolder getParentHolder(ViewGroup parent);
	protected abstract RecycleViewHolder getChildHolder(ViewGroup parent);
	
	private static final int PARENT = 0;
	private static final int CHILD = 1;
	
	@Override
	public int getItemViewType(int position) {
		if(visible.get(position).isParent())
			return PARENT;
		else
			return CHILD;
	}

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
    	holder.setData(listener.getContext(), visible.get(position));
    }

    public int getSelectedIndex(){
        for(int i = 0; i < visible.size(); i++)
            if(visible.get(i).getClickState().equals(RecycleViewItem.ClickState.REPEAT) || visible.get(i).getClickState().equals(RecycleViewItem.ClickState.SELECTED))
                return i;
        return 0;
    }

    public RecycleViewItem getFirstSelectedItem(){
        for(RecycleViewItem ni : visible)
            if(ni.getClickState().equals(RecycleViewItem.ClickState.REPEAT) || ni.getClickState().equals(RecycleViewItem.ClickState.SELECTED))
                return ni;
        return null;
    }
    
    public RecycleViewItem getDragItem(){
        for(RecycleViewItem ni : visible)
            if(ni.getClickState().equals(RecycleViewItem.ClickState.DRAG))
                return ni;
        return null;
    }

    public void setSelectByIndex(int index, boolean silentSelect){
    	RecycleViewItem item = visible.get(index);
    	if(item.isParent())
            manageParentClick(item);
        else
        	//(TODO) Must fix that null workaround
        	manageChildClick(null, item, silentSelect);
    }
    
    @Override
    public void onViewAttachedToWindow(RecycleViewHolder holder) {
    	super.onViewAttachedToWindow(holder);
    	holder.updateUI(listener.getContext());
    }
    
    @Override
    public final int getItemCount() {
        return visible.size();
    }
    
    public List<RecycleViewItem> getVisible() {
		return visible;
	}

	protected void setData(List<RecycleViewItem> data){
    	items.clear();
    	items.addAll(data);
    	manageVisibles();
    	notifyDataSetChanged();
    }
    
    public void clear(){
    	visible.clear();
    	items.clear();
    }
    
    private void manageVisibles(){
    	visible.clear();
    	for(RecycleViewItem item : items)
    		if(item.isVisible())
    			visible.add(item);
    }
    
    public int getDragItemPosition(){
    	RecycleViewItem item = getDragItem();
    	if(item != null){
    		int position = visible.indexOf(item);
    		return position;
    	}
    	return -1;
    }
    
    public int getItemPosition(RecycleViewItem item){
    	return visible.indexOf(item);
    }
    
    public void shiftAllUp(int from, int to){
    	RecycleViewItem rvi = visible.get(from);
		visible.remove(from);
		if(to == visible.size())
			visible.add(rvi);
		else
			visible.add(to, rvi);
		notifyItemRangeChanged(from, to - from + 1);
		//notifyDataSetChanged();
    }
    
    public void shiftAllDown(int from, int to){
    	RecycleViewItem rvi = visible.get(from);
		visible.add(to, rvi);
		visible.remove(from + 1);
		notifyItemRangeChanged(to, from - to + 1);
		//notifyDataSetChanged();
    }
    
    public void dragDone(){
    	RecycleViewItem dragged = getDragItem();
    	if(dragged != null){
    		int dragPosition = visible.indexOf(dragged);
    		dragged.clearState();
    		notifyItemChanged(dragPosition);
    	}
    }
}
