package com.sirmamobile.saltlux.http.requests;

import com.sirmamobile.saltlux.http.model.CityData;

public interface GetCityDataRequestListener {
    void getCityData(CityData data, Object d);
    void getCityDataError(Object error);
}
