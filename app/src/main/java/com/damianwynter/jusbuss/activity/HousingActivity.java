package com.damianwynter.jusbuss.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Housing;
import com.damianwynter.jusbuss.ui.HousingListFragment;
import com.damianwynter.jusbuss.ui.ViewHousingInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HousingActivity extends AppCompatActivity implements HousingListFragment.OnHousingSelectedInterface {
    private static final String TAG = HousingActivity.class.getSimpleName();
    public static final String HOUSING_LIST_FRAGMENT = "housing_list_fragment";
    public static Housing[] mHousings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing);
        setTitle("JusBuss - Grocery");
        getRentals();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void getRentals(){
        String diningUrl = getString(R.string.host) + "/rentals";

        if(isNetworkAvailable()){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(diningUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HousingActivity.this, "Failed to make request. Please try again later",
                                    Toast.LENGTH_SHORT).show();
                            Log.v(TAG, "Exception Caught: ", e);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        String jsonData = response.body().string();
                        if(response.isSuccessful()){
                            mHousings = getHousings(jsonData);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setTitle("JusBuss - Housing");

                                    HousingListFragment savedFragment = (HousingListFragment) getSupportFragmentManager()
                                                .findFragmentByTag(HOUSING_LIST_FRAGMENT);
                                    if(savedFragment == null) {
                                        HousingListFragment fragment = new HousingListFragment();
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.housing_content, fragment, HOUSING_LIST_FRAGMENT);
                                        fragmentTransaction.commit();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(HousingActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch(IOException | JSONException e) {
                        Log.e(TAG, "Exception Caught: ", e);
                    }
                }
            });
        }
    }

    private Housing[] getHousings(String jsonData) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonData);

        Housing[] rentals = new Housing[jsonArray.length()];

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objJson = jsonArray.getJSONObject(i);
            Housing h = new Housing();

            h.setFname(objJson.getString("first_name"));
            h.setLname(objJson.getString("last_name"));
            h.setPhone(objJson.getString("phone"));
            h.setAddress(objJson.getString("address"));
            h.setDescription(objJson.getString("description"));
            h.setTenantGender(objJson.getString("tenant_gender"));
            h.setNumOccupancy(objJson.getInt("num_occupancy"));
            h.setVacancy(objJson.getInt("vacancy"));
            h.setUtilises(objJson.getString("utilities"));
            h.setPrice(objJson.getDouble("price"));
            h.setStatus(objJson.getString("status"));
            h.setLongitude(objJson.getDouble("longitude"));
            h.setLatitude(objJson.getDouble("latitude"));
            h.setIcon(objJson.getString("icon"));

            rentals[i] = h;
        }
        return rentals;
    }


    @Override
    public void OnListHousingSelected(int index) {
        Intent intent = new Intent(this, ViewHousingInfo.class);
        intent.putExtra("housing", mHousings[index]);
        startActivity(intent);
    }
}
