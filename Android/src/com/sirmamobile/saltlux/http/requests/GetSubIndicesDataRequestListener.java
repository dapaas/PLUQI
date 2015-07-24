package com.sirmamobile.saltlux.http.requests;

import com.sirmamobile.saltlux.http.model.CityData;

public interface GetSubIndicesDataRequestListener {
    void getSubIndicesDataResponse(CityData data);
    void getSubIndicesDataError(Object error);
}
