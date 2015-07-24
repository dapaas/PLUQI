package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sirmamobile.base.utils.ParcelWrapper;

public class Data implements Parcelable{

	@SerializedName("City")
	private String city;
	
	@SerializedName("Education")
	private String education;
	
	@SerializedName("Environment")
	private String environment;
	
	@SerializedName("Healthcare")
	private String healthcare;
	
	@SerializedName("Cultural Satisfaction")
	private String culturalSatisfaction;
	
	@SerializedName("Traffic Satisfaction")
	private String trafficSatisfaction;
	
	@SerializedName("PLUQI")
	private double pluqi;
	
	private double lon;
	private double lat;
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getHealthcare() {
		return healthcare;
	}

	public void setHealthcare(String healthcare) {
		this.healthcare = healthcare;
	}

	public String getCulturalSatisfaction() {
		return culturalSatisfaction;
	}

	public void setCulturalSatisfaction(String culturalSatisfaction) {
		this.culturalSatisfaction = culturalSatisfaction;
	}

	public String getTrafficSatisfaction() {
		return trafficSatisfaction;
	}

	public void setTrafficSatisfaction(String trafficSatisfaction) {
		this.trafficSatisfaction = trafficSatisfaction;
	}

	public double getPluqi() {
		return pluqi;
	}

	public void setPluqi(double pluqi) {
		this.pluqi = pluqi;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
    
    private Data(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        city = pw.readString();
        education = pw.readString();
        environment = pw.readString();
        healthcare = pw.readString();
        culturalSatisfaction = pw.readString();
        trafficSatisfaction = pw.readString();
        pluqi = pw.readDouble();
        lon = pw.readDouble();
        lat = pw.readDouble();
        pluqi = pw.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeString(city);
        pw.writeString(education);
        pw.writeString(environment);
        pw.writeString(healthcare);
        pw.writeString(culturalSatisfaction);
        pw.writeString(trafficSatisfaction);
        pw.writeDouble(pluqi);
        pw.writeDouble(lon);
        pw.writeDouble(lat);
        pw.writeDouble(pluqi);
    }
}
