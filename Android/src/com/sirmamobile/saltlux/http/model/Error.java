package com.sirmamobile.saltlux.http.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.sirmamobile.base.utils.ParcelWrapper;

public class Error implements Parcelable {

   

    public Error() {

    }


    public static final Creator<Error> CREATOR = new Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };

    private Error(Parcel in) {
        ParcelWrapper pw = new ParcelWrapper(in);
//        code = pw.readString();
//        message = pw.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelWrapper pw = new ParcelWrapper(dest);
//        pw.writeString(code);
//        pw.writeString(message);
    }
}
