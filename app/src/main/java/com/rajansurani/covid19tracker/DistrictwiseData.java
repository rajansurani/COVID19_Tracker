package com.rajansurani.covid19tracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rajansurani.covid19tracker.Api.ApiClient;
import com.rajansurani.covid19tracker.Api.ApiInterface;
import com.rajansurani.covid19tracker.Model.District;
import com.rajansurani.covid19tracker.Model.DistrictData;

import java.util.ArrayList;
import java.util.List;

public class DistrictwiseData extends AppCompatActivity {

    TextView mState,mLastUpdate, mStatusConfirmedCount, mStatusActiveCount, mStatusRecoveredCount, mStatusDeceasedCount;
    TextView mStatusConfirmedCountInc, mStatusActiveCountInc, mStatusRecoveredCountInc, mStatusDeceasedCountInc;

    List<DistrictData> mDistrictDataList = new ArrayList<> ();
    final String TAG  = getClass ().getSimpleName ();
    String state;
    DistrictDataAdapter mDistrictDataAdapter;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_districtwise_data);

        ActionBar toolbar = getSupportActionBar ();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("COVID19 TRACKER");
        }

        mState = findViewById (R.id.state_name);
        mLastUpdate = findViewById (R.id.state_last_update_time);
        mStatusConfirmedCount = findViewById (R.id.state_confirmed_count);
        mStatusActiveCount = findViewById (R.id.state_active_count);
        mStatusRecoveredCount = findViewById (R.id.state_recovered_count);
        mStatusDeceasedCount = findViewById (R.id.state_deceased_count);
        mStatusConfirmedCountInc = findViewById (R.id.state_confirmed_increase_count);
        mStatusActiveCountInc = findViewById (R.id.state_active_increase_count);
        mStatusRecoveredCountInc = findViewById (R.id.state_recovered_increase_count);
        mStatusDeceasedCountInc = findViewById (R.id.state_deceased_increase_count);

        mRecyclerView = findViewById (R.id.district_recyclerview);
        mRecyclerView.setLayoutManager (new LinearLayoutManager (DistrictwiseData.this));

        Intent intent = getIntent ();
        state = intent.getStringExtra ("name");
        mState.setText (intent.getStringExtra ("name"));
        mLastUpdate.setText (intent.getStringExtra ("last_update"));
        mStatusConfirmedCount.setText (String.valueOf (intent.getIntExtra ("confirmed",0)));
        mStatusActiveCount.setText (String.valueOf (intent.getIntExtra ("active",0)));
        mStatusRecoveredCount.setText (String.valueOf (intent.getIntExtra ("recovered",0)));
        mStatusDeceasedCount.setText (String.valueOf (intent.getIntExtra ("deceased",0)));

        mStatusActiveCountInc.setText ("+"+intent.getIntExtra ("active_inc",0));
        mStatusConfirmedCountInc.setText ("+"+intent.getIntExtra ("confirmed_inc",0));
        mStatusDeceasedCountInc.setText ("+"+intent.getIntExtra ("deceased_inc",0));
        mStatusRecoveredCountInc.setText ("+"+intent.getIntExtra ("recovered_inc",0));

        loadJSON ();
    }

    public void loadJSON(){
        final ProgressDialog progressDialog = new ProgressDialog(DistrictwiseData.this);
        progressDialog.setMessage("Loading Data..."); // Setting Message
        progressDialog.setTitle("COVID19 Tracker"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        ApiInterface apiInterface = ApiClient.getApiClient ().create (ApiInterface.class);
        Call<List<DistrictData>> call;

        call = apiInterface.getDistrictData ();

        call.enqueue (new Callback<List<DistrictData>> () {
            @Override
            public void onResponse(Call<List<DistrictData>> call, Response<List<DistrictData>> response) {
                if(response.isSuccessful ()) {

                        if(!mDistrictDataList.isEmpty ())
                            mDistrictDataList.clear ();

                        mDistrictDataList = response.body ();
                        List<District> districtList= new ArrayList<> ();
                        Log.i (TAG, String.valueOf (response));
                        for(DistrictData d : mDistrictDataList)
                        {
                            if(d.getState ().equals (state)) {
                                Log.i (TAG, d.getState ());
                                districtList = d.getDistrictList ();
                                break;
                            }
                        }
                        Log.i(TAG, String.valueOf (districtList));
                        mDistrictDataAdapter = new DistrictDataAdapter (DistrictwiseData.this, districtList);
                        mRecyclerView.setAdapter (mDistrictDataAdapter);
                        mDistrictDataAdapter.notifyDataSetChanged ();

                        progressDialog.dismiss ();

                } else{
                    Toast.makeText (DistrictwiseData.this, "No Reuslt!!", Toast.LENGTH_LONG).show ();
                }
            }

            @Override
            public void onFailure(Call<List<DistrictData>> call, Throwable t) {
                Log.e(TAG, "Response Failure");
                Log.e (TAG, String.valueOf (t));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_about:
                openAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openAbout()
    {
        Intent intent = new Intent (DistrictwiseData.this, About.class);
        startActivity (intent);
    }
}
