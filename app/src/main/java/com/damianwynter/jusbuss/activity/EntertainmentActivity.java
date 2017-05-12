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
import com.damianwynter.jusbuss.models.Entertainment;
import com.damianwynter.jusbuss.ui.EntListFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EntertainmentActivity extends AppCompatActivity implements EntListFragment.OnEntertainmentSelectedInterface {
    private static final String TAG = EntertainmentActivity.class.getSimpleName();
    public static Entertainment[] mEntertainments;
    public static final String ENT_LIST_FRAGMENT = "ent_list_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        getEntertainment();
    }

    private void getEntertainment(){
        String entUrl = getString(R.string.host) + "/entertainment";

        if(isNetworkAvailable()){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(entUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EntertainmentActivity.this, "Failed to make request. Please try again later",
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
                            mEntertainments = getEntertainment(jsonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setTitle("JusBuss - Entertainment");

                                    EntListFragment savedFragment = (EntListFragment) getSupportFragmentManager()
                                            .findFragmentByTag(ENT_LIST_FRAGMENT);
                                    if(savedFragment == null) {
                                        EntListFragment fragment = new EntListFragment();
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.ent_content, fragment, ENT_LIST_FRAGMENT);
                                        fragmentTransaction.commit();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(EntertainmentActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private Entertainment[] getEntertainment(String jsonData) throws JSONException {
        JSONArray ent = new JSONArray(jsonData);

        Entertainment[] entertainments = new Entertainment[ent.length()];

        for(int i = 0; i < ent.length(); i++){
            JSONObject jsonEnt = ent.getJSONObject(i);
            Entertainment entertainment = new Entertainment();

            entertainment.setName(jsonEnt.getString("name"));
            entertainment.setCategory(jsonEnt.getString("category"));
            entertainment.setAddress(jsonEnt.getString("address"));
            entertainment.setAdmission(jsonEnt.getString("admission"));
            entertainment.setDate(jsonEnt.getString("date"));
            entertainment.setTime(jsonEnt.getString("time"));
            entertainment.setPoster(jsonEnt.getString("poster"));
            entertainment.setTicket_sold_at(jsonEnt.getString("ticket_sold_at"));
            entertainment.setTel(jsonEnt.getString("tel"));

            entertainments[i] = entertainment;
        }
        return entertainments;
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

    @Override
    public void OnListEntSelected(int index) {

    }
}
