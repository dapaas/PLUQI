package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class Node implements Parcelable{

	private String node;

	
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public static final Creator<Node> CREATOR = new Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel in) {
            return new Node(in);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };

    private Node(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        node = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeString(node);

    }
    
    
}
