package com.sirmamobile.saltlux.recycleview.saltlux;

import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.http.model.Node;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;

public class RankOfCitiesViewItem extends RecycleViewItem {

	Node node;
	int position;

	public RankOfCitiesViewItem(Node node, int position) {
		super(Type.SINGLE_SELECT);
		this.node = node;
		this.position = position;
	}

	public Node getNode() {
		return node;
	}

	public int getPosition() {
		return position;
	}
	
	
	
	



}
