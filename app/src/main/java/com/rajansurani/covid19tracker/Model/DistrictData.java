package com.rajansurani.covid19tracker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictData {

    @SerializedName ("state")
    @Expose
    private String state;

    @SerializedName ("districtData")
    @Expose
    private List<District> mDistrictList;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<District> getDistrictList() {
        return mDistrictList;
    }

    public void setDistrictList(List<District> districtList) {
        mDistrictList = districtList;
    }
}
