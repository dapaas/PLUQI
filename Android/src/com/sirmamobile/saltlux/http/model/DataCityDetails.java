package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class DataCityDetails implements Parcelable{

	private List<DataCityDetailsResults> results = new ArrayList<DataCityDetailsResults>();
	private String source;
	
	

	public List<DataCityDetailsResults> getResults() {
		return results;
	}

	public void setResults(List<DataCityDetailsResults> results) {
		this.results = results;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public static final Creator<DataCityDetails> CREATOR = new Creator<DataCityDetails>() {
        @Override
        public DataCityDetails createFromParcel(Parcel in) {
            return new DataCityDetails(in);
        }

        @Override
        public DataCityDetails[] newArray(int size) {
            return new DataCityDetails[size];
        }
    };

    private DataCityDetails(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        pw.readList(results, DataCityDetailsResults.class.getClassLoader());
        source = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeList(results);
        pw.writeString(source);
    }
    
    
}
