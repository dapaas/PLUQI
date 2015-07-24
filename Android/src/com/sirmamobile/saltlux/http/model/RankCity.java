package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class RankCity implements Parcelable{

	private List<Data> data = new ArrayList<Data>();
	private String rankOfCitiesData;
	
	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public String getRankOfCitiesData() {
		return rankOfCitiesData;
	}

	public void setRankOfCitiesData(String rankOfCitiesData) {
		this.rankOfCitiesData = rankOfCitiesData;
	}

	public static final Creator<RankCity> CREATOR = new Creator<RankCity>() {
        @Override
        public RankCity createFromParcel(Parcel in) {
            return new RankCity(in);
        }

        @Override
        public RankCity[] newArray(int size) {
            return new RankCity[size];
        }
    };

    private RankCity(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        pw.readList(data, Data.class.getClassLoader());
        rankOfCitiesData = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeList(data);
        pw.writeString(rankOfCitiesData);
    }
    
    
}
