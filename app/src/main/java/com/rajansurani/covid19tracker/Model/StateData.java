package com.rajansurani.covid19tracker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateData {

    @SerializedName ("active")
    @Expose
    private int active;

    @SerializedName ("confirmed")
    @Expose
    private int confirmed;

    @SerializedName ("deaths")
    @Expose
    private int deaths;

    @SerializedName ("deltaconfirmed")
    @Expose
    private int deltaconfirmed;

    @SerializedName ("deltadeaths")
    @Expose
    private int deltadeaths;

    @SerializedName ("deltarecovered")
    @Expose
    private int deltarecovered;

    @SerializedName ("lastupdatedtime")
    @Expose
    private String lastupdatedtime;

    @SerializedName ("recovered")
    @Expose
    private int recovered;

    @SerializedName ("state")
    @Expose
    private String state;

    @SerializedName ("statecode")
    @Expose
    private String statecode;

    @SerializedName ("statenotes")
    @Expose
    private String statenotes;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public void setDeltaconfirmed(int deltaconfirmed) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public int getDeltadeaths() {
        return deltadeaths;
    }

    public void setDeltadeaths(int deltadeaths) {
        this.deltadeaths = deltadeaths;
    }

    public int getDeltarecovered() {
        return deltarecovered;
    }

    public void setDeltarecovered(int deltarecovered) {
        this.deltarecovered = deltarecovered;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getStatenotes() {
        return statenotes;
    }

    public void setStatenotes(String statenotes) {
        this.statenotes = statenotes;
    }

    @Override
    public String toString() {
        return "StateData{" +
                "active=" + active +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", deltaconfirmed=" + deltaconfirmed +
                ", deltadeaths=" + deltadeaths +
                ", deltarecovered=" + deltarecovered +
                ", lastupdatedtime='" + lastupdatedtime + '\'' +
                ", recovered=" + recovered +
                ", state='" + state + '\'' +
                ", statecode='" + statecode + '\'' +
                ", statenotes='" + statenotes + '\'' +
                '}';
    }
}
