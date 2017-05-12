package com.damianwynter.jusbuss.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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

/**
 * Created by infinity on 4/29/2017.
 */

public class AddFacultyActivity extends AppCompatActivity {
    private ImageView imgFacultyIcon;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Button btnAddFaculty;
    public static final String TAG = AddFacultyActivity.class.getSimpleName();
    private EditText edFacultyName, edfacultyLong, edFacultyLat, edFacultyDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        setTitle("JusBuss - Add Faculty");
        imgFacultyIcon = (ImageView) findViewById(R.id.facultyImg);
        btnAddFaculty = (Button) findViewById(R.id.addFacultyButton);
        edFacultyName = (EditText) findViewById(R.id.facultyName);
        edfacultyLong = (EditText) findViewById(R.id.longitudeEditText);
        edFacultyLat = (EditText) findViewById(R.id.latitudeEditText);
        edFacultyDescription = (EditText) findViewById(R.id.descriptionEditText);


       sendData();

    }
    private void sendData(){
        btnAddFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();
                try{
                    obj.put("name",edFacultyName.getText());
                    obj.put("description",edFacultyDescription.getText());
                    obj.put("longitude",Double.parseDouble(String.valueOf(edfacultyLong.getText())));
                    obj.put("latitude",Double.parseDouble(String.valueOf(edFacultyLat.getText())));
                    obj.put("icon","faculty.png");
                    post(getString(R.string.host) + "/university/59055ef9ecc329001168d0bc/faculties", obj.toString());
                } catch (final JSONException e){
                    Log.v(TAG, "Exception Caught: ", e);
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
                            Toast.makeText(AddFacultyActivity.this, "There was an error completing your request", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddFacultyActivity.this, "Event Successfully added", Toast.LENGTH_LONG).show();
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
