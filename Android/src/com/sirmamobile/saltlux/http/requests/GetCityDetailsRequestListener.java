package com.sirmamobile.saltlux.http.requests;

import com.sirmamobile.saltlux.http.model.CityData;

public interface GetCityDetailsRequestListener {
    void getCityDataDetailsResponse(CityData data, Object d);
    void getCityDataDetailsError(Object error);
}
