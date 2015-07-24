package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class CompareCities implements Parcelable{


	private List<String> arrCity = new ArrayList<String>();	
	private CompareCitiesResponse data;
	private String compareCitiesData;
	
	public List<String> getArrCity() {
		return arrCity;
	}

	public void setArrCity(List<String> arrCity) {
		this.arrCity = arrCity;
	}

	public String getCompareCitiesData() {
		return compareCitiesData;
	}

	public void setCompareCitiesData(String compareCitiesData) {
		this.compareCitiesData = compareCitiesData;
	}
	
	public CompareCitiesResponse getData() {
		return data;
	}

	public void setData(CompareCitiesResponse data) {
		this.data = data;
	}

	public static final Creator<CompareCities> CREATOR = new Creator<CompareCities>() {
        @Override
        public CompareCities createFromParcel(Parcel in) {
            return new CompareCities(in);
        }

        @Override
        public CompareCities[] newArray(int size) {
            return new CompareCities[size];
        }
    };

    private CompareCities(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        data = pw.readParcelable(CompareCitiesResponseItems.class.getClassLoader());
        pw.readList(arrCity, String.class.getClassLoader());
        compareCitiesData = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeParcelable(data, flags);
        pw.writeList(arrCity);
        pw.writeString(compareCitiesData);
    }
    
    
}
