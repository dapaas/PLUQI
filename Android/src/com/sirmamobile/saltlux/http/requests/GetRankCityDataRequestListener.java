package com.sirmamobile.saltlux.http.requests;

import com.sirmamobile.saltlux.http.model.RankCity;

public interface GetRankCityDataRequestListener {
    void getRankCityData(RankCity data);
    void getRankCityDataError(Object error);
}
