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
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rajansurani.covid19tracker.Api.ApiClient;
import com.rajansurani.covid19tracker.Api.ApiInterface;
import com.rajansurani.covid19tracker.Model.Data;
import com.rajansurani.covid19tracker.Model.StateData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mLastUpdate, mStatusConfirmedCount, mStatusActiveCount, mStatusRecoveredCount, mStatusDeceasedCount;
    TextView mStatusConfirmedCountInc, mStatusActiveCountInc, mStatusRecoveredCountInc, mStatusDeceasedCountInc;
    List<StateData> mStateDataList = new ArrayList<> ();
    final String TAG = getClass ().getSimpleName ();

    boolean doubleBackToExitPressedOnce = false;

    StateDataAdapter mStateDataAdapter;
    StateData currentStatus;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ActionBar toolbar = getSupportActionBar ();
        if (getSupportActionBar () != null) {
            getSupportActionBar ().setTitle ("COVID19 TRACKER");
        }
        mLastUpdate = findViewById (R.id.last_update_time);
        mStatusConfirmedCount = findViewById (R.id.status_confirmed_count);
        mStatusActiveCount = findViewById (R.id.status_active_count);
        mStatusRecoveredCount = findViewById (R.id.status_recovered_count);
        mStatusDeceasedCount = findViewById (R.id.status_deceased_count);
        mStatusConfirmedCountInc = findViewById (R.id.status_confirmed_increase_count);
        mStatusActiveCountInc = findViewById (R.id.status_active_increase_count);
        mStatusRecoveredCountInc = findViewById (R.id.status_recovered_increase_count);
        mStatusDeceasedCountInc = findViewById (R.id.status_deceased_increase_count);

        mRecyclerView = findViewById (R.id.state_recyclerview);
        mRecyclerView.setLayoutManager (new LinearLayoutManager (MainActivity.this));

        loadJSON ();


    }

    public void loadJSON() {
        final ProgressDialog progressDialog = new ProgressDialog (MainActivity.this);
        progressDialog.setMessage ("Loading Data..."); // Setting Message
        progressDialog.setTitle ("COVID19 Tracker"); // Setting Title
        progressDialog.setProgressStyle (ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show (); // Display Progress Dialog
        progressDialog.setCancelable (false);

        ApiInterface apiInterface = ApiClient.getApiClient ().create (ApiInterface.class);
        Call<Data> call;

        call = apiInterface.getData ();

        call.enqueue (new Callback<Data> () {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.isSuccessful ()) {

                    if (response.body ().getStateData () != null) {
                        if (!mStateDataList.isEmpty ())
                            mStateDataList.clear ();

                        mStateDataList = response.body ().getStateData ();
                        updateCurrentStatus ();

                        List<StateData> onlyState = mStateDataList;
                        onlyState.remove (0);
                        mStateDataAdapter = new StateDataAdapter (MainActivity.this, onlyState);
                        mRecyclerView.setAdapter (mStateDataAdapter);
                        mStateDataAdapter.notifyDataSetChanged ();
                        initListner ();
                        progressDialog.dismiss ();
                    }

                } else {
                    Toast.makeText (MainActivity.this, "No Reuslt!!", Toast.LENGTH_LONG).show ();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void updateCurrentStatus() {
        currentStatus = mStateDataList.get (0);
        Log.e (TAG, currentStatus.toString ());

        mLastUpdate.setText (currentStatus.getLastupdatedtime ());
        mStatusConfirmedCount.setText (String.valueOf (currentStatus.getConfirmed ()));
        mStatusActiveCount.setText (String.valueOf (currentStatus.getActive ()));
        mStatusRecoveredCount.setText (String.valueOf (currentStatus.getRecovered ()));
        mStatusDeceasedCount.setText (String.valueOf (currentStatus.getDeaths ()));

        mStatusActiveCountInc.setText ("+" + (currentStatus.getDeltaconfirmed () - currentStatus.getDeltadeaths () - currentStatus.getDeltarecovered ()));
        mStatusConfirmedCountInc.setText ("+" + currentStatus.getDeltaconfirmed ());
        mStatusDeceasedCountInc.setText ("+" + currentStatus.getDeltadeaths ());
        mStatusRecoveredCountInc.setText ("+" + currentStatus.getDeltarecovered ());
    }

    private void initListner() {
        mStateDataAdapter.setOnItemClickListener (new StateDataAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent (MainActivity.this, DistrictwiseData.class);
                StateData stateData = mStateDataList.get (position);
                Log.i (TAG, stateData.toString ());
                intent.putExtra ("name", stateData.getState ());
                intent.putExtra ("active", stateData.getActive ());
                intent.putExtra ("confirmed", stateData.getConfirmed ());
                intent.putExtra ("recovered", stateData.getRecovered ());
                intent.putExtra ("death", stateData.getDeaths ());
                int active_inc = stateData.getDeltaconfirmed () - stateData.getDeltarecovered () - stateData.getDeltadeaths ();
                intent.putExtra ("active_inc", active_inc);
                intent.putExtra ("confirmed_inc", stateData.getDeltaconfirmed ());
                intent.putExtra ("recovered_inc", stateData.getDeltarecovered ());
                intent.putExtra ("death_inc", stateData.getDeltadeaths ());
                intent.putExtra ("last_update", stateData.getLastupdatedtime ());
                startActivity (intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId ()) {
            case R.id.action_about:
                openAbout ();
                return true;
            default:
                return super.onOptionsItemSelected (item);
        }
    }

    public void openAbout() {
        Intent intent = new Intent (MainActivity.this, About.class);
        startActivity (intent);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed ();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText (this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show ();

        new Handler ().postDelayed (new Runnable () {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
