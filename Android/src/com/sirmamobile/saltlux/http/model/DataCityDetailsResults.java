package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sirmamobile.base.utils.ParcelWrapper;

public class DataCityDetailsResults implements Parcelable{

	private List<Node> topics = new ArrayList<Node>();
	@SerializedName("end_date")
	private String endDate;
	@SerializedName("start_date")
	private String startDate;

	public static final Creator<DataCityDetailsResults> CREATOR = new Creator<DataCityDetailsResults>() {
        @Override
        public DataCityDetailsResults createFromParcel(Parcel in) {
            return new DataCityDetailsResults(in);
        }

        @Override
        public DataCityDetailsResults[] newArray(int size) {
            return new DataCityDetailsResults[size];
        }
    };

    private DataCityDetailsResults(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        pw.readList(topics, Node.class.getClassLoader());
        endDate = pw.readString();
        startDate = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeList(topics);
        pw.writeString(endDate);
        pw.writeString(startDate);
    }
    
    
}
