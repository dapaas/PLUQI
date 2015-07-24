package com.sirmamobile.saltlux.recycleview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Martin on 5/4/2015.
 */
public abstract class RecycleViewItem {

	public static enum Type {
		SINGLE_SELECT,
		SELECTABLE,
		CLICKABLE,
		ALLWAYS_SELECTED,
		ALLWAYS_UNSELECTED
	}
	
    public static enum ClickState{
    	CLICKED,
        SELECTED,
        UNSELECTED,
        REPEAT,
        DRAG;
    }
    
    private Type type;
    private int clicks;
    private ClickState clickState;
    
    public RecycleViewItem(Type type) {
        this.type = type;
        clearState();
    }

	public ClickState getClickState() {
        return clickState;
    }

    public Type getType() {
		return type;
	}

	public void clearState(){
    	switch (type){
	    	case CLICKABLE:
	    	case SINGLE_SELECT:
	    	case SELECTABLE:
	    	case ALLWAYS_UNSELECTED:
	            clickState = ClickState.UNSELECTED;
	    		break;
	    	case ALLWAYS_SELECTED:
				clickState = ClickState.SELECTED;
	    		break;
		}
    }

	public void drag(){
		clickState = clickState.DRAG;
	}
	
    public ClickState clicked(){
    	clicks++;
    	switch (type){
	    	case CLICKABLE:
                clickState = ClickState.CLICKED;
	    		break;
	    	case SINGLE_SELECT:
	    		if(clickState.equals(ClickState.SELECTED))
	    			clickState = ClickState.REPEAT;
	    		else
	    			clickState = ClickState.SELECTED;
	    		break;
	    	case SELECTABLE:
	    		if(clickState.equals(ClickState.SELECTED))
	    			clickState = ClickState.UNSELECTED;
	    		else
	    			clickState = ClickState.SELECTED;
    			break;
	    	case ALLWAYS_SELECTED:
    			clickState = ClickState.SELECTED;
    			break;
	    	case ALLWAYS_UNSELECTED:
    			clickState = ClickState.UNSELECTED;
	    		break;
    	}
        return clickState;
    }

	public int getClicks() {
		return clicks;
	}
	
	private RecycleViewItem parent;
	private List<RecycleViewItem> children = new ArrayList<RecycleViewItem>();
	
	public boolean isParent(){
		return children.size() != 0;
	}
	
	public void setParent(RecycleViewItem parent) {
		this.parent = parent;
	}

	public RecycleViewItem getParent() {
		return parent;
	}

	public void add(int location, RecycleViewItem object) {
		children.add(location, object);
	}

	public boolean add(RecycleViewItem object) {
		return children.add(object);
	}

	public boolean addAll(int location,
			Collection<? extends RecycleViewItem> collection) {
		return children.addAll(location, collection);
	}

	public boolean addAll(
			Collection<? extends RecycleViewItem> collection) {
		return children.addAll(collection);
	}

	public void clear() {
		children.clear();
	}

	public boolean contains(Object object) {
		return children.contains(object);
	}

	public boolean isVisible() {
		if(parent == null)
			return true;
		else
			return parent.getClickState().equals(ClickState.SELECTED) || parent.getClickState().equals(ClickState.REPEAT);
	}
	
	public boolean containsAll(Collection<?> collection) {
		return children.containsAll(collection);
	}

	public RecycleViewItem get(int location) {
		return children.get(location);
	}

	public RecycleViewItem remove(int location) {
		return children.remove(location);
	}

	public boolean remove(Object object) {
		return children.remove(object);
	}

	public boolean removeAll(Collection<?> collection) {
		return children.removeAll(collection);
	}

	public int size() {
		return children.size();
	}
}
