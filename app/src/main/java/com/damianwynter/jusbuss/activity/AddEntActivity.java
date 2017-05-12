package com.damianwynter.jusbuss.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.damianwynter.jusbuss.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddEntActivity extends AppCompatActivity {
    Calendar myCalendar = Calendar.getInstance();
    String path;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg; charset=utf-8");
    public static final String TAG = AddEntActivity.class.getSimpleName();
    private TextView mDate;
    private TextView mTime;
    private TextView mName;
    private TextView mAddress;
    private TextView mTel;
    private TextView mAdmission;
    private TextView mTickets;
    private ImageView img;
    private RadioGroup mCategory;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ent);

        setTitle("JusBuss - Create Entertainment");
        mName = (TextView) findViewById(R.id.nameEditText);
        mAddress = (TextView) findViewById(R.id.addressEditText);
        mTel = (TextView) findViewById(R.id.TelEditText);
        mAdmission = (TextView) findViewById(R.id.admissionEditText);
        mTickets = (TextView) findViewById(R.id.ticketEditText);
        mCategory = (RadioGroup) findViewById(R.id.category);
        addButton = (Button) findViewById(R.id.addButton);
        img = (ImageView) findViewById(R.id.eventImg);

        setListener();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    path = getPathFromURI(data.getData());
                    img.setImageURI(data.getData());
                }
        }
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void setListener() {
        mDate = (TextView) findViewById(R.id.dateEditText);
        mTime = (TextView) findViewById(R.id.timeEditText);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddEntActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mTime.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEntActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                startActivityForResult(intent, 100);
            }
        });



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();
                try{
                    obj.put("name", mName.getText());
                    obj.put("time", mTime.getText());
                    obj.put("date", mDate.getText());
                    obj.put("tel", mTel.getText());
                    obj.put("admission", mAdmission.getText());
                    obj.put("address", mAddress.getText());
                    obj.put("ticket_sold_at", mTickets.getText());

                    if(mCategory.getCheckedRadioButtonId() != -1){
                        int id = mCategory.getCheckedRadioButtonId();
                        View radioButton = mCategory.findViewById(id);
                        int radioId = mCategory.indexOfChild(radioButton);
                        RadioButton rBtn = (RadioButton) mCategory.getChildAt(radioId);
                        String selection = (String) rBtn.getText();

                        obj.put("category", selection);
                    }
                    post(getString(R.string.host) + "/entertainment", obj.toString());

                } catch (final JSONException e){
                    Log.v(TAG, "Exception Caught: ", e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void post(String url, String json) throws IOException {
        if(isNetworkAvailable()) {
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
                            Toast.makeText(AddEntActivity.this, "There was an error completing your request", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddEntActivity.this, "Event Successfully added", Toast.LENGTH_LONG).show();
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

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mDate.setText(sdf.format(myCalendar.getTime()));
    }
}
