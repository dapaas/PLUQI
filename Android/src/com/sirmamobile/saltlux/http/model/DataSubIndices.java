package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class DataSubIndices implements Parcelable{

	private DataSubIndicesResponse response;
	
	public DataSubIndicesResponse getResponse() {
		return response;
	}

	public void setResponse(DataSubIndicesResponse response) {
		this.response = response;
	}

	public static final Creator<DataSubIndices> CREATOR = new Creator<DataSubIndices>() {
        @Override
        public DataSubIndices createFromParcel(Parcel in) {
            return new DataSubIndices(in);
        }

        @Override
        public DataSubIndices[] newArray(int size) {
            return new DataSubIndices[size];
        }
    };

    private DataSubIndices(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        response = pw.readParcelable(DataSubIndicesResponse.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeParcelable(response, flags);
    }
    
    
}
