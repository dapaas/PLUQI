package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class CompareCitiesResponse implements Parcelable{

	
	public List<CompareCitiesResponseItems> getResponse() {
		return response;
	}

	public void setResponse(List<CompareCitiesResponseItems> response) {
		this.response = response;
	}

	private List<CompareCitiesResponseItems> response = new ArrayList<CompareCitiesResponseItems>();

	public static final Creator<CompareCitiesResponse> CREATOR = new Creator<CompareCitiesResponse>() {
        @Override
        public CompareCitiesResponse createFromParcel(Parcel in) {
            return new CompareCitiesResponse(in);
        }

        @Override
        public CompareCitiesResponse[] newArray(int size) {
            return new CompareCitiesResponse[size];
        }
    };

    private CompareCitiesResponse(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        pw.readList(response, CompareCitiesResponseItems.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeList(response);
    }
    
    
}
