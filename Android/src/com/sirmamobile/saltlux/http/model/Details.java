package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sirmamobile.base.utils.ParcelWrapper;

public class Details implements Parcelable{

	private int temperature;
	private int humidity;
	@SerializedName("PM10")
	private int pm10;
	@SerializedName("green_spaces")
	private int greenSpaces;
	
	

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPm10() {
		return pm10;
	}

	public void setPm10(int pm10) {
		this.pm10 = pm10;
	}

	public int getGreenSpaces() {
		return greenSpaces;
	}

	public void setGreenSpaces(int greenSpaces) {
		this.greenSpaces = greenSpaces;
	}

	public static final Creator<Details> CREATOR = new Creator<Details>() {
        @Override
        public Details createFromParcel(Parcel in) {
            return new Details(in);
        }

        @Override
        public Details[] newArray(int size) {
            return new Details[size];
        }
    };

    private Details(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
        temperature = pw.readInt();
        humidity = pw.readInt();
        pm10 = pw.readInt();
        greenSpaces = pw.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
        pw.writeInt(temperature);
        pw.writeInt(humidity);
        pw.writeInt(pm10);
        pw.writeInt(greenSpaces);
    }
    
    
}
