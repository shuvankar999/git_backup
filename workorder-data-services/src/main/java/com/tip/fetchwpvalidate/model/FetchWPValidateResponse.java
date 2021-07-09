package com.tip.fetchwpvalidate.model;

import java.util.Map;

public class FetchWPValidateResponse {

    private Map<String, Object> fetchWPValidateMap;

    public Map<String, Object> getFetchWPValidateMap() {
        return fetchWPValidateMap;
    }

    public void setFetchWPValidateMap(Map<String, Object> fetchWPValidateMap) {
        this.fetchWPValidateMap = fetchWPValidateMap;
    }

    @Override
    public String toString() {
        return "FetchWPValidateResponse [fetchWPValidateMap=" + fetchWPValidateMap + "]";
    }


}
