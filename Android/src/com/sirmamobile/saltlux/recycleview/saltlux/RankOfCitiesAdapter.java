package com.sirmamobile.saltlux.recycleview.saltlux;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.http.model.Data;
import com.sirmamobile.saltlux.http.model.Node;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapter;
import com.sirmamobile.saltlux.recycleview.RecycleViewAdapterListener;
import com.sirmamobile.saltlux.recycleview.RecycleViewHolder;
import com.sirmamobile.saltlux.recycleview.RecycleViewItem;

public class RankOfCitiesAdapter extends RecycleViewAdapter {

	public RankOfCitiesAdapter(RecycleViewAdapterListener listener) {
		super(listener, true);
	}

	@Override
	protected RecycleViewHolder getParentHolder(ViewGroup parent) {
		return null;
	}

	@Override
	protected RecycleViewHolder getChildHolder(ViewGroup parent) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.rank_cities_item, parent, false);
		RankOfCitiesViewHolder vh = new RankOfCitiesViewHolder(v);
		return vh;
	}

	public void setNodeData(List<Node> node) {
		List<RecycleViewItem> items = new ArrayList<RecycleViewItem>();
		int position = 0;
		for (Node bs : node) {
			position++;
			items.add(new RankOfCitiesViewItem(bs, position));

		}
		setData(items);
	}

}
