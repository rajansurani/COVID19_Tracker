package com.rajansurani.covid19tracker.Api;

import com.rajansurani.covid19tracker.Model.Data;
import com.rajansurani.covid19tracker.Model.DistrictData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("data.json")
    Call<Data> getData();

    @GET("v2/state_district_wise.json")
    Call<List<DistrictData>> getDistrictData();
}
