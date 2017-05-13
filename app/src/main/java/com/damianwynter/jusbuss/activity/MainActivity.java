package com.damianwynter.jusbuss.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.damianwynter.jusbuss.R;
import com.damianwynter.jusbuss.models.Faculty;
import com.damianwynter.jusbuss.models.GeneralBAF;
import com.damianwynter.jusbuss.models.University;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static University mUniversity;
    ProgressBar progressBar;
    TextView pleaseWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("JusBuss - Navigation App");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //progressBar = (ProgressBar) findViewById(R.id.startBar);
        //progressBar.setVisibility(View.VISIBLE);

        //pleaseWait = (TextView) findViewById(R.id.pleaseWait);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getUniversity();
        setCardListener();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            setTitle("JusBuss - Navigation App");
        } else if (id == R.id.nav_maps) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_university) {

            if(mUniversity != null) {
                Intent intent = new Intent(this, UniversityActivity.class);
                startActivity(intent);
            } else {

                Toast.makeText(this, "App is Initializing, Please wait...", Toast.LENGTH_SHORT).show();
            }

        } else
            if (id == R.id.nav_dining) {
            Intent intent = new Intent(this, DiningActivity.class);
            startActivity(intent);

        } else
            if (id == R.id.nav_share) {
            Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show();
        } else
            if (id == R.id.nav_send) {
            Toast.makeText(this, "Send Clicked", Toast.LENGTH_SHORT).show();
        } else
            if (id == R.id.nav_ent){

            Intent intent = new Intent(this, EntertainmentActivity.class);
            startActivity(intent);

        }else
            if(id == R.id.nav_grocery){
            startActivity(new Intent(this,GroceryActivity.class));
        }else
            if(id == R.id.nav_housing){
            startActivity(new Intent(this,HousingActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getUniversity(){
        String universityUrl = getString(R.string.host) + "/university";

        // Check if network available on device
        if(isNetworkAvailable()){
            // Creating client for http request
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(universityUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // progressBar.setVisibility(View.INVISIBLE);
                            //pleaseWait.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "Failed to make request. Please try again later",
                                    Toast.LENGTH_SHORT).show();
                            Log.v(TAG, "Exception Caught: ", e);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //progressBar.setVisibility(View.INVISIBLE);
                                //pleaseWait.setVisibility(View.INVISIBLE);
                            }
                        });
                        String jsonData = response.body().string();
                        if(response.isSuccessful()){
                            mUniversity = parseUniversityDetails(jsonData);
                        } else {
                            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException | JSONException e){
                        Log.e(TAG, "Exception Caught: ", e);
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

    private University parseUniversityDetails(String jsonData) throws JSONException {
        University university = new University();
        JSONArray universities = new JSONArray(jsonData);
        JSONObject universityData = universities.getJSONObject(0);

        university.setName(universityData.getString("name"));
        university.setDescription(universityData.getString("description"));
        university.setAddress(universityData.getString("address"));
        university.setOpenTime(universityData.getString("openTime"));
        university.setCloseTime(universityData.getString("closeTime"));
        university.setLongitude(universityData.getString("longitude"));
        university.setLatitude(universityData.getString("latitude"));

        university.setFaculties(getFaculty(jsonData));
        university.setmBanks(getBanks(jsonData));
        university.setmAtm(getATM(jsonData));
        university.setmBuildings(getBuildings(jsonData));
        //university.setmClubs(getClubs(jsonData));

        return university;
    }

    private Faculty[] getFaculty(String jsonData) throws JSONException {
        JSONArray universities = new JSONArray(jsonData);
        JSONObject university = universities.getJSONObject(0);

        JSONArray data = university.getJSONArray("faculties");
        Faculty[] faculties = new Faculty[data.length()];

        for(int i = 0; i < data.length(); i++){
            JSONObject jsonFaculty = data.getJSONObject(i);
            Faculty faculty = new Faculty();

            faculty.setName(jsonFaculty.getString("name"));
            faculty.setDescription(jsonFaculty.getString("description"));
            faculty.setLongitude(jsonFaculty.getDouble("longitude"));
            faculty.setLatitude(jsonFaculty.getDouble("latitude"));
            faculty.setId(jsonFaculty.getString("_id"));
            faculty.setIcon(jsonFaculty.getString("icon"));

            faculties[i] = faculty;
        }
        return faculties;
    }
    private GeneralBAF[] getClubs(String jsonData) throws JSONException{
        JSONArray universities = new JSONArray(jsonData);
        JSONObject university = universities.getJSONObject(0);

        JSONArray data = university.getJSONArray("clubs");
        GeneralBAF[] aData = new GeneralBAF[data.length()];

        for(int i = 0; i < data.length(); i++){
            JSONObject jsonFaculty = data.getJSONObject(i);
            GeneralBAF faculty = new GeneralBAF();

            faculty.setaName(jsonFaculty.getString("name"));
            faculty.setaDescription(jsonFaculty.getString("description"));
            faculty.setaLongitude(jsonFaculty.getDouble("longitude"));
            faculty.setaLatitude(jsonFaculty.getDouble("latitude"));
            faculty.setaOpenTime(jsonFaculty.getInt("openTime"));
            faculty.setaCloseTime(jsonFaculty.getInt("closeTime"));

            aData[i] = faculty;
        }
        return aData;
    }
    private GeneralBAF[] getATM(String jsonData) throws JSONException{
        JSONArray universities = new JSONArray(jsonData);
        JSONObject university = universities.getJSONObject(0);

        JSONArray data = university.getJSONArray("atms");
        GeneralBAF[] aData = new GeneralBAF[data.length()];

        for(int i = 0; i < data.length(); i++){
            JSONObject jsonFaculty = data.getJSONObject(i);
            GeneralBAF faculty = new GeneralBAF();

            faculty.setaName(jsonFaculty.getString("name"));
            faculty.setaDescription(jsonFaculty.getString("description"));
            faculty.setaLongitude(jsonFaculty.getDouble("longitude"));
            faculty.setaLatitude(jsonFaculty.getDouble("latitude"));
            faculty.setaOpenTime(jsonFaculty.getInt("openTime"));
            faculty.setaCloseTime(jsonFaculty.getInt("closeTime"));

            aData[i] = faculty;
        }
        return aData;
    }
    private GeneralBAF[] getBanks(String jsonData) throws JSONException{
        JSONArray universities = new JSONArray(jsonData);
        JSONObject university = universities.getJSONObject(0);

        JSONArray data = university.getJSONArray("banks");
        GeneralBAF[] aData = new GeneralBAF[data.length()];

        for(int i = 0; i < data.length(); i++){
            JSONObject jsonFaculty = data.getJSONObject(i);
            GeneralBAF faculty = new GeneralBAF();

            faculty.setaName(jsonFaculty.getString("name"));
            faculty.setaDescription(jsonFaculty.getString("description"));
            faculty.setaLongitude(jsonFaculty.getDouble("longitude"));
            faculty.setaLatitude(jsonFaculty.getDouble("latitude"));
            faculty.setaOpenTime(jsonFaculty.getInt("openTime"));
            faculty.setaCloseTime(jsonFaculty.getInt("closeTime"));

            aData[i] = faculty;
        }
        return aData;
    }
    private GeneralBAF[] getBuildings(String jsonData) throws JSONException{
        JSONArray universities = new JSONArray(jsonData);
        JSONObject university = universities.getJSONObject(0);

        JSONArray data = university.getJSONArray("universityBuildings");
        GeneralBAF[] aData = new GeneralBAF[data.length()];

        for(int i = 0; i < data.length(); i++){
            JSONObject jsonObj = data.getJSONObject(i);
            GeneralBAF dataObj = new GeneralBAF();

            dataObj.setaName(jsonObj.getString("name"));
            dataObj.setaDescription(jsonObj.getString("description"));
            dataObj.setaLongitude(jsonObj.getDouble("longitude"));
            dataObj.setaLatitude(jsonObj.getDouble("latitude"));
            dataObj.setaOpenTime(jsonObj.getInt("openTime"));
            dataObj.setaCloseTime(jsonObj.getInt("closeTime"));

            aData[i] = dataObj;
        }
        return aData;
    }

    private void setCardListener(){
        CardView mapCard = (CardView) findViewById(R.id.mapCardView);
        mapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapIntent);
            }
        });

        CardView facultyCard = (CardView) findViewById(R.id.facultyCardView);
        facultyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facultyIntent = new Intent(MainActivity.this,FacultyActivity.class);
                startActivity(facultyIntent);
            }
        });

        CardView diningCard = (CardView) findViewById(R.id.diningCardView);
        diningCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diningTntent = new Intent(MainActivity.this, DiningActivity.class);
                startActivity(diningTntent);
            }
        });

        CardView entCard = (CardView) findViewById(R.id.entertainmentCardView);
        entCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entIntent = new Intent(MainActivity.this, EntertainmentActivity.class);
                startActivity(entIntent);
            }
        });

        CardView housingCard = (CardView) findViewById(R.id.housingCardView);
        housingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent housingIntent = new Intent(MainActivity.this, HousingActivity.class);
                startActivity(housingIntent);
            }
        });
    }
}
