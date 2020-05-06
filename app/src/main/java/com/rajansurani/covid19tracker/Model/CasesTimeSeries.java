package com.rajansurani.covid19tracker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CasesTimeSeries {

    @SerializedName ("dailyconfirmed")
    @Expose
    private int dailyconfirmed;

    @SerializedName ("dailyrecovered")
    @Expose
    private int dailyrecovered;

    @SerializedName ("dailydeceased")
    @Expose
    private int dailydeceased;

    @SerializedName ("date")
    @Expose
    private String date;

    @SerializedName ("totalconfirmed")
    @Expose
    private int totalconfirmed;

    @SerializedName ("totalrecovered")
    @Expose
    private int totalrecovered;

    @SerializedName ("totaldeceased")
    @Expose
    private int totaldeceased;

    public int getDailyconfirmed() {
        return dailyconfirmed;
    }

    public void setDailyconfirmed(int dailyconfirmed) {
        this.dailyconfirmed = dailyconfirmed;
    }

    public int getDailyrecovered() {
        return dailyrecovered;
    }

    public void setDailyrecovered(int dailyrecovered) {
        this.dailyrecovered = dailyrecovered;
    }

    public int getDailydeceased() {
        return dailydeceased;
    }

    public void setDailydeceased(int dailydeceased) {
        this.dailydeceased = dailydeceased;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalconfirmed() {
        return totalconfirmed;
    }

    public void setTotalconfirmed(int totalconfirmed) {
        this.totalconfirmed = totalconfirmed;
    }

    public int getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(int totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    public int getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(int totaldeceased) {
        this.totaldeceased = totaldeceased;
    }
}
