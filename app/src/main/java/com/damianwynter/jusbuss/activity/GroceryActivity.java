package com.damianwynter.jusbuss.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Grocery;
import com.damianwynter.jusbuss.ui.GroceryListFragment;
import com.damianwynter.jusbuss.ui.HousingListFragment;
import com.damianwynter.jusbuss.ui.ViewGroceryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GroceryActivity extends AppCompatActivity implements GroceryListFragment.OnListGrocerySelectedInterface {
    private static final String TAG = GroceryActivity.class.getSimpleName();
    public static final String GROCERY_LIST_FRAGMENT = "grocery_list_fragment";
    public static Grocery[] mGrocery;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        setTitle("JusBuss - Grocery");
        //getRentals();

        new GetData().execute();
    }

    private class GetData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String diningUrl = getString(R.string.host) + "/grocery";

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
                                Toast.makeText(GroceryActivity.this, "Failed to make request. Please try again later",
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
                                mGrocery = getGrocery(jsonData);


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setTitle("JusBuss - Grocery");

                                        HousingListFragment savedFragment = (HousingListFragment) getSupportFragmentManager()
                                                .findFragmentByTag(GROCERY_LIST_FRAGMENT);
                                        if(savedFragment == null) {
                                            GroceryListFragment fragment = new GroceryListFragment();
                                            FragmentManager fragmentManager = getSupportFragmentManager();
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                            fragmentTransaction.replace(R.id.grocery_content, fragment, GROCERY_LIST_FRAGMENT);
                                            fragmentTransaction.commit();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(GroceryActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } catch(IOException | JSONException e) {
                            Log.e(TAG, "Exception Caught: ", e);
                        }
                    }
                });
            }


            return diningUrl;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(GroceryActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                getGrocery(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
    //private void getRentals(){}


    private Grocery[] getGrocery(String jsonData) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonData);

        Grocery[] groceryArr = new Grocery[jsonArray.length()];

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objJson = jsonArray.getJSONObject(i);
            Grocery grocery = new Grocery();

            grocery.setName(objJson.getString("name"));
            grocery.setAddress(objJson.getString("address"));
            grocery.setDescription(objJson.getString("description"));
            grocery.setOpenHours(objJson.getInt("openHours"));
            grocery.setCloseHours(objJson.getInt("closeHours"));
            grocery.setLongitude(objJson.getDouble("longitude"));
            grocery.setLatitude(objJson.getDouble("latitude"));
            grocery.setIcon(objJson.getString("icon"));

            groceryArr[i] = grocery;
        }
        progressDialog.dismiss();
        return groceryArr;
    }

    @Override
    public void OnListGrocerySelected(int index) {
        Intent i = new Intent(this, ViewGroceryInfo.class);
        i.putExtra("grocery",mGrocery[index]);
        startActivity(i);
    }


}
