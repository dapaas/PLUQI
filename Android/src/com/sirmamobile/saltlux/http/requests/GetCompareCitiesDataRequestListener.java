package com.sirmamobile.saltlux.http.requests;

import com.sirmamobile.saltlux.http.model.CompareCities;

public interface GetCompareCitiesDataRequestListener {
    void getCompareCitiesData(CompareCities data);
    void getCompareCitiesDataError(Object error);
}
