package com.rajansurani.covid19tracker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName ("cases_time_series")
    @Expose
    private List<CasesTimeSeries> mCasesTimeSeriesList;

    @SerializedName ("statewise")
    @Expose
    private List<StateData> mStateData;

    @SerializedName ("tested")
    @Expose
    private List<Tested> mTested;

    public List<CasesTimeSeries> getCasesTimeSeriesList() {
        return mCasesTimeSeriesList;
    }

    public void setCasesTimeSeriesList(List<CasesTimeSeries> casesTimeSeriesList) {
        mCasesTimeSeriesList = casesTimeSeriesList;
    }

    public List<StateData> getStateData() {
        return mStateData;
    }

    public void setStateData(List<StateData> stateData) {
        mStateData = stateData;
    }

    public List<Tested> getTested() {
        return mTested;
    }

    public void setTested(List<Tested> tested) {
        mTested = tested;
    }
}
