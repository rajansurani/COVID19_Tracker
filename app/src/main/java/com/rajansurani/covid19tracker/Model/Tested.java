package com.rajansurani.covid19tracker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tested {

    @SerializedName ("positivecasesfromsamplesreported")
    @Expose
    private String positivecasesfromsamplesreported;

    @SerializedName ("samplereportedtoday")
    @Expose
    private String samplereportedtoday;

    @SerializedName ("source")
    @Expose
    private String source;

    @SerializedName ("testsconductedbyprivatelabs")
    @Expose
    private String testsconductedbyprivatelabs;

    @SerializedName ("totalindividualstested")
    @Expose
    private String totalindividualstested;

    @SerializedName ("totalpositivecases")
    @Expose
    private String totalpositivecases;

    @SerializedName ("totalsamplestested")
    @Expose
    private String totalsamplestested;

    @SerializedName ("updatetimestamp")
    @Expose
    private String updatetimestamp;

    public void setPositivecasesfromsamplesreported(String positivecasesfromsamplesreported) {
        this.positivecasesfromsamplesreported = positivecasesfromsamplesreported;
    }

    public void setSamplereportedtoday(String samplereportedtoday) {
        this.samplereportedtoday = samplereportedtoday;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTestsconductedbyprivatelabs(String testsconductedbyprivatelabs) {
        this.testsconductedbyprivatelabs = testsconductedbyprivatelabs;
    }

    public void setTotalindividualstested(String totalindividualstested) {
        this.totalindividualstested = totalindividualstested;
    }

    public void setTotalpositivecases(String totalpositivecases) {
        this.totalpositivecases = totalpositivecases;
    }

    public void setTotalsamplestested(String totalsamplestested) {
        this.totalsamplestested = totalsamplestested;
    }

    public void setUpdatetimestamp(String updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }
}
