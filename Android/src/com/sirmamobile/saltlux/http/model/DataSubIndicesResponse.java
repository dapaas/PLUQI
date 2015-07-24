package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class DataSubIndicesResponse implements Parcelable{

	private String name;
	private int weight;
	private Details details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public static final Creator<DataSubIndicesResponse> CREATOR = new Creator<DataSubIndicesResponse>() {
        @Override
        public DataSubIndicesResponse createFromParcel(Parcel in) {
            return new DataSubIndicesResponse(in);
        }

        @Override
        public DataSubIndicesResponse[] newArray(int size) {
            return new DataSubIndicesResponse[size];
        }
    };

    private DataSubIndicesResponse(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        name = pw.readString();
        weight = pw.readInt();
        details = pw.readParcelable(Details.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeString(name);
        pw.writeInt(weight);
        pw.writeParcelable(details, flags);
    }
    
    
}
