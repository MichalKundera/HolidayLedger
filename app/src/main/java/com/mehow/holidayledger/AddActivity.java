package com.mehow.holidayledger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Purchase;
import com.mehow.holidayledger.model.entities.enums.Category;
import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddActivity extends AppCompatActivity {
    private EditText sumText;
    private Spinner currencySpinner;
    private EditText dateText;
    private Spinner categorySpinner;
    private EditText descriptionText;
    private CheckBox locationCheckBox;
    private Button addButton;


    private FusedLocationProviderClient mFusedLocationClient;
    Location mLocation=null;
    private final int MY_PERMISSIONS_REQUEST_LOCATION =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(AddActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission_group.LOCATION},MY_PERMISSIONS_REQUEST_LOCATION);}
        }
        else{
        Task task = mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            mLocation = location;
                        }
                    }
                });
        }


        sumText = findViewById(R.id.sumText);
        dateText = findViewById(R.id.dateText);
        categorySpinner = findViewById(R.id.categorySpinner);
        descriptionText = findViewById(R.id.descriptionText);
        locationCheckBox = findViewById(R.id.locationCheckBox);
        currencySpinner = findViewById(R.id.currencySpinner);
        addButton = findViewById(R.id.addButton);
        toolbar = findViewById(R.id.toolbar);



        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        dateText.setText(timeStamp);

        currencySpinner.setAdapter(new ArrayAdapter<CurrencyShortcut>(this, android.R.layout.simple_list_item_1, CurrencyShortcut.values()));
        categorySpinner.setAdapter(new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, Category.values()));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        Task task = mFusedLocationClient.getLastLocation()
                                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        // Got last known location. In some rare situations this can be null.
                                        if (location != null) {
                                            mLocation = location;

                                        }
                                    }
                                }

                                );
                    }

                } else {

                    locationCheckBox = findViewById(R.id.locationCheckBox);
                    locationCheckBox.setVisibility(View.INVISIBLE);

                }
                return;
            }

        }
    }

    @SuppressLint("ResourceAsColor")
    public void addButtonClicked(View view) throws InterruptedException {
        if (allFieldsFilled()) {
            LatLng latLng=null;
            if(mLocation!=null) {
                latLng = new LatLng(mLocation.getLatitude(),mLocation.getLongitude());
            }

            LatLng latLng2 = new LatLng(50.880096,20.641668);

            Purchase purchase = new Purchase(DBManager.getInstance().getNextPurchaseID(), DBManager.getInstance().getLogged(), (CurrencyShortcut) currencySpinner.getSelectedItem(),
                    Float.parseFloat(sumText.getText().toString()),descriptionText.getText().toString(), new Date(dateText.getText().toString().trim()),
                    (Category) categorySpinner.getSelectedItem());

            if(locationCheckBox.isChecked() && mLocation!=null)
                purchase.setLocation(latLng);


            DBManager.getInstance().getJourney(1).addPurchase(purchase);

            addButton.setClickable(false);
            addButton.setText(R.string.add_success);

            mHandler.postDelayed(mRunnable, 700);



        }

    }

    private boolean allFieldsFilled() {
        if(sumText.getText().toString().isEmpty() || dateText.getText().toString().isEmpty() )
        {
            return false;
        }
        return true;
    }

    @SuppressLint("ResourceAsColor")
    private void clearAllFields()
    {
        sumText.setText("");
        descriptionText.setText("");
        addButton.setText(R.string.add_purchase);
        addButton.setBackgroundColor(R.color.fundsHigh);
        addButton.refreshDrawableState();
        addButton.setClickable(true);
    }


    private static class MyHandler extends Handler {}
    private final MyHandler mHandler = new MyHandler();

    public static class MyRunnable implements Runnable {
        private final WeakReference<AddActivity> mActivity;

        public MyRunnable(AddActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            AddActivity activity = mActivity.get();
            if (activity != null) {
                activity.clearAllFields();
            }
        }
    }

    private MyRunnable mRunnable = new MyRunnable(this);



}
