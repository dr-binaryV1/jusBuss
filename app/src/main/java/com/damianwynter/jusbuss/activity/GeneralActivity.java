package com.damianwynter.jusbuss.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.GeneralBAF;
import com.damianwynter.jusbuss.ui.DiningListFragment;
import com.damianwynter.jusbuss.ui.GeneralListFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GeneralActivity extends AppCompatActivity implements GeneralListFragment.OnGeneralSelectedItem {
    public static GeneralBAF[] aData;
    public static final String TAG = GeneralActivity.class.getSimpleName();
    public static final String GENERAL_LIST_FRAGMENT = "general_list_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        getData(getIntent().getStringExtra("data"));
    }

    private void getData(String dataString){
        String diningUrl = getString(R.string.host) + "/university/59055ef9ecc329001168d0bc/"+dataString;

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
                            Toast.makeText(GeneralActivity.this, "Failed to make request. Please try again later",
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
                            aData = getDataFromJson(jsonData);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setTitle("JusBuss - ");

                                    GeneralListFragment savedFragment = (GeneralListFragment) getSupportFragmentManager()
                                            .findFragmentByTag(GENERAL_LIST_FRAGMENT);
                                    if(savedFragment == null) {
                                        GeneralListFragment fragment = new GeneralListFragment();
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.general_content, fragment, GENERAL_LIST_FRAGMENT);
                                        fragmentTransaction.commit();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(GeneralActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch(IOException | JSONException e) {
                        Log.e(TAG, "Exception Caught: ", e);
                    }
                }
            });
        }
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
    private GeneralBAF[] getDataFromJson(String jsonData) throws JSONException{
        JSONArray dataArr = new JSONArray(jsonData);

        GeneralBAF[] dataObjArray = new GeneralBAF[dataArr.length()];

        for(int i = 0; i < dataArr.length(); i++){
            JSONObject jsonFoodShop = dataArr.getJSONObject(i);
            GeneralBAF data = new GeneralBAF();

            data.setaName(jsonFoodShop.getString("name"));
            data.setaDescription(jsonFoodShop.getString("description"));
            data.setaLongitude(jsonFoodShop.getDouble("longitude"));
            data.setaCloseTime(jsonFoodShop.getInt("closeTime"));
            data.setaOpenTime(jsonFoodShop.getInt("openTime"));
            data.setaLatitude(jsonFoodShop.getDouble("latitude"));

            dataObjArray[i] = data;
        }
        return dataObjArray;
    }
    @Override
    public void onListSelected(int aIndex) {

    }
}
