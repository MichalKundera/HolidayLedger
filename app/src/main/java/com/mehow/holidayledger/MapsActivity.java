package com.mehow.holidayledger;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mehow.holidayledger.model.DBManager;
import com.mehow.holidayledger.model.entities.Purchase;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {

        super.onStart();
        if(mMap!=null)
        {
            loadMarkers();
        }
    }

    private void loadMarkers()
    {
        List<Purchase> list= DBManager.getInstance().getJourney(1).getPurchaseList();
        if(list!=null)
            for (Purchase p:list
                    ) {
                if(p.getLocation()!=null)
                {
                    String title = p.getPerson().getName()+":"+p.getDescription();
                    String snippet =String.format("%.2f%s",p.getSum(),p.getCurrencyShortcut().name());
                    mMap.addMarker(new MarkerOptions().position(p.getLocation()).title(title).snippet(snippet).visible(true)).showInfoWindow();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(p.getLocation()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p.getLocation(),12.0f));
                }

            }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        loadMarkers();
    }
}
