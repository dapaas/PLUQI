package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sirmamobile.base.utils.ParcelWrapper;

public class Data implements Parcelable{

	@SerializedName("City")
	private String city;
	
	@SerializedName("Education")
	private double education;
	
	@SerializedName("Environment")
	private double environment;
	
	@SerializedName("Healthcare")
	private double healthcare;
	
	@SerializedName("Cultural Satisfaction")
	private double culturalSatisfaction;
	
	@SerializedName("Traffic Satisfaction")
	private double trafficSatisfaction;
	
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

	public double getEducation() {
		return education;
	}

	public void setEducation(double education) {
		this.education = education;
	}

	public double getEnvironment() {
		return environment;
	}

	public void setEnvironment(double environment) {
		this.environment = environment;
	}

	public double getHealthcare() {
		return healthcare;
	}

	public void setHealthcare(double healthcare) {
		this.healthcare = healthcare;
	}

	public double getCulturalSatisfaction() {
		return culturalSatisfaction;
	}

	public void setCulturalSatisfaction(double culturalSatisfaction) {
		this.culturalSatisfaction = culturalSatisfaction;
	}

	public double getTrafficSatisfaction() {
		return trafficSatisfaction;
	}

	public void setTrafficSatisfaction(double trafficSatisfaction) {
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
	
	

	public Data() {
		super();
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
        education = pw.readDouble();
        environment = pw.readDouble();
        healthcare = pw.readDouble();
        culturalSatisfaction = pw.readDouble();
        trafficSatisfaction = pw.readDouble();
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
        pw.writeDouble(education);
        pw.writeDouble(environment);
        pw.writeDouble(healthcare);
        pw.writeDouble(culturalSatisfaction);
        pw.writeDouble(trafficSatisfaction);
        pw.writeDouble(pluqi);
        pw.writeDouble(lon);
        pw.writeDouble(lat);
        pw.writeDouble(pluqi);
    }
}
