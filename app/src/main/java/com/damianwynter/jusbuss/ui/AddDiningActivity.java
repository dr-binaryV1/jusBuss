package com.damianwynter.jusbuss.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.damianwynter.jusbuss.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddDiningActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String TAG = AddDiningActivity.class.getSimpleName();

    private TextView mName;
    private TextView mDesc;
    private TextView mAddress;
    private TextView mOpen;
    private TextView mClose;
    private ImageView mImg;
    private Button addRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dining);

        setTitle("JusBuss - Create Restaurant");
        mName = (TextView) findViewById(R.id.dining_name);
        mDesc = (TextView) findViewById(R.id.dining_desc);
        mAddress = (TextView) findViewById(R.id.dining_address);
        mImg = (ImageView) findViewById(R.id.dining_img);
        mOpen = (TextView) findViewById(R.id.open_time);
        mClose = (TextView) findViewById(R.id.close_time);
        addRestaurant = (Button) findViewById(R.id.add_dining);

        setListener();
    }

    public void setListener(){
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("name", mName.getText());
                    obj.put("description", mDesc.getText());
                    obj.put("address", mAddress.getText());
                    obj.put("openTime", mOpen.getText());
                    obj.put("closeTime", mClose.getText());
                    obj.put("icon", "dining.png");

                    post(getString(R.string.host) + "/dining", obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void post(String url, String json){
        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddDiningActivity.this, "There was an error completing your request", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddDiningActivity.this, "Event Successfully added", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
                    }
                }
            });

        } else {
            Toast.makeText(this, "Network Unavailable. Please check internet connection and try again",
                    Toast.LENGTH_SHORT).show();
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
}
