package com.damianwynter.jusbuss.ui;

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
import com.damianwynter.jusbuss.models.Dining;
import com.damianwynter.jusbuss.models.FoodMenu;
import com.damianwynter.jusbuss.models.SizeVariations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DiningActivity extends AppCompatActivity implements DiningListFragment.OnFoodShopSelectedInterface {
    private static final String TAG = DiningActivity.class.getSimpleName();
    public static final String DINING_LIST_FRAGMENT = "dining_list_fragment";
    public static FoodMenu[] mMenus;
    public static int[] mMenuLength;
    public static Dining[] mDinings;
    public static FoodMenu[][] mFoodMenus;
    public static SizeVariations[][] mFoodSizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);

        getDining();
    }

    @Override
    public void onListFoodShopSelected(int index) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("Index", index);
        startActivity(intent);
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

    private void getDining(){
        String diningUrl = getString(R.string.host) + "/dining";

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
                            Toast.makeText(DiningActivity.this, "Failed to make request. Please try again later",
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
                            mDinings = getFoodShops(jsonData);
                            mFoodMenus = getMenu(jsonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setTitle("JusBuss - Dining");

                                    DiningListFragment savedFragment = (DiningListFragment) getSupportFragmentManager()
                                            .findFragmentByTag(DINING_LIST_FRAGMENT);
                                    if(savedFragment == null) {
                                        DiningListFragment fragment = new DiningListFragment();
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.dining_content, fragment, DINING_LIST_FRAGMENT);
                                        fragmentTransaction.commit();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(DiningActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch(IOException | JSONException e) {
                        Log.e(TAG, "Exception Caught: ", e);
                    }
                }
            });
        }
    }

    private Dining[] getFoodShops(String jsonData) throws JSONException{
        JSONArray foodShops = new JSONArray(jsonData);

        Dining[] dinings = new Dining[foodShops.length()];

        for(int i = 0; i < foodShops.length(); i++){
            JSONObject jsonFoodShop = foodShops.getJSONObject(i);
            Dining dining = new Dining();

            dining.setName(jsonFoodShop.getString("name"));
            dining.setDescription(jsonFoodShop.getString("description"));
            dining.setAddress(jsonFoodShop.getString("address"));
            dining.setCloseTime(jsonFoodShop.getInt("closeTime"));
            dining.setOpenTime(jsonFoodShop.getInt("openTime"));
            dining.setIcon(jsonFoodShop.getString("icon"));

            dinings[i] = dining;
        }
        return dinings;
    }

    private FoodMenu[][] getMenu(String jsonData) throws JSONException {
        JSONArray foodShops = new JSONArray(jsonData);
        FoodMenu[][] foodMenus = new FoodMenu[foodShops.length()][];
        mFoodSizes = new SizeVariations[foodMenus.length][];

        for(int i = 0; i < foodShops.length(); i++){
            JSONObject foodShop = foodShops.getJSONObject(i);
            JSONArray data = foodShop.getJSONArray("menu");

            // Store length of menu to array to access in the fragment class
            mMenuLength = new int[foodShops.length()];
            mMenuLength[i] = data.length();

            mMenus = new FoodMenu[data.length()];
            for(int y = 0; y < data.length(); y++) {
                JSONObject jsonFoodShop = data.getJSONObject(y);

                FoodMenu menu = new FoodMenu();

                menu.setName(jsonFoodShop.getString("name"));
                menu.setDescription(jsonFoodShop.getString("description"));

                mMenus[y] = menu;

                JSONArray sizeData = jsonFoodShop.getJSONArray("variations");
                SizeVariations[] varSizes = new SizeVariations[sizeData.length()];
                for (int s = 0; s < sizeData.length(); s++){
                    JSONObject jsonSizeData = sizeData.getJSONObject(s);

                    SizeVariations sizeVariations = new SizeVariations();

                    sizeVariations.setSize(jsonSizeData.getString("size"));
                    sizeVariations.setPrice(jsonSizeData.getInt("price"));

                    varSizes[s] = sizeVariations;
                }
                mFoodSizes[i] = varSizes;
            }
            foodMenus[i] = mMenus;
        }

        return foodMenus;
    }
}
