package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class Values implements Parcelable{

	private String index;
	private int value;
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static final Creator<Values> CREATOR = new Creator<Values>() {
        @Override
        public Values createFromParcel(Parcel in) {
            return new Values(in);
        }

        @Override
        public Values[] newArray(int size) {
            return new Values[size];
        }
    };

    private Values(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        index = pw.readString();
        value = pw.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeString(index);
        pw.writeInt(value);

    }
    
    
}
