package com.sirmamobile.saltlux.http.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sirmamobile.base.utils.ParcelWrapper;

public class CityData implements Parcelable{

	@SerializedName("JSONDataCityDetails")
	private String jsonDataCityDetails;
	private String cityData;
	private List<Data> data = new ArrayList<Data>();
	private DataCityDetails dataCityDetails;
	private DataSubIndices dataSubIndices;
	private String subIndicesDetailData;

	
	public String getJsonDataCityDetails() {
		return jsonDataCityDetails;
	}

	public void setJsonDataCityDetails(String jsonDataCityDetails) {
		this.jsonDataCityDetails = jsonDataCityDetails;
	}

	public String getCityData() {
		return cityData;
	}

	public void setCityData(String cityData) {
		this.cityData = cityData;
	}
	
	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
	
	public DataCityDetails getDataCityDetails() {
		return dataCityDetails;
	}

	public void setDataCityDetails(DataCityDetails dataCityDetails) {
		this.dataCityDetails = dataCityDetails;
	}

	public DataSubIndices getDataSubIndices() {
		return dataSubIndices;
	}

	public void setDataSubIndices(DataSubIndices dataSubIndices) {
		this.dataSubIndices = dataSubIndices;
	}

	public String getSubIndicesDetailData() {
		return subIndicesDetailData;
	}

	public void setSubIndicesDetailData(String subIndicesDetailData) {
		this.subIndicesDetailData = subIndicesDetailData;
	}




	public static final Creator<CityData> CREATOR = new Creator<CityData>() {
        @Override
        public CityData createFromParcel(Parcel in) {
            return new CityData(in);
        }

        @Override
        public CityData[] newArray(int size) {
            return new CityData[size];
        }
    };

    private CityData(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        jsonDataCityDetails = pw.readString();
        cityData = pw.readString();
        pw.readList(data, Data.class.getClassLoader());
        dataCityDetails = pw.readParcelable(DataCityDetails.class.getClassLoader());
        dataSubIndices = pw.readParcelable(DataSubIndices.class.getClassLoader());
        subIndicesDetailData = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeString(jsonDataCityDetails);
        pw.writeString(cityData);
        pw.writeList(data);
        pw.writeParcelable(dataSubIndices, flags);
        pw.writeString(subIndicesDetailData);
    }
    
    
}
