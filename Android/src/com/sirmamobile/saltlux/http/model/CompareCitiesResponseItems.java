package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class CompareCitiesResponseItems implements Parcelable{

	private List<Values> values = new ArrayList<Values>();
	private String city;

	public List<Values> getValues() {
		return values;
	}

	public void setValues(List<Values> values) {
		this.values = values;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static final Creator<CompareCitiesResponseItems> CREATOR = new Creator<CompareCitiesResponseItems>() {
        @Override
        public CompareCitiesResponseItems createFromParcel(Parcel in) {
            return new CompareCitiesResponseItems(in);
        }

        @Override
        public CompareCitiesResponseItems[] newArray(int size) {
            return new CompareCitiesResponseItems[size];
        }
    };

    private CompareCitiesResponseItems(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        pw.readList(values, Node.class.getClassLoader());
        city = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeList(values);
        pw.writeString(city);
    }
    
    
}
