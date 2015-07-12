package com.gaslibre.gaslibre;

import android.location.Address;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.Intent;

import com.gaslibre.gaslibre.Model.Posto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.Control.Service.GPSHelper;
import com.gaslibre.gaslibre.DAO.DBManager;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements View.OnClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private UserController usuarioController= new UserController(this);
    private DBManager dbManager;
    private Context context;
    private int i;

    private LatLng LtLg;

    private Button buttonNavegar;

    private void inicializaComponentes() {

        buttonNavegar = (Button) findViewById(R.id.BtNavegar);
        buttonNavegar.setOnClickListener((View.OnClickListener)this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GPSHelper gps = new GPSHelper(this);

        // - Requisição da localização pelo gps
        gps.getMyLocation();

        double latitude  = gps.getLatitude();
        double longitude = gps.getLongitude();

        this.LtLg = new LatLng(latitude, longitude);

        //this.aviso(latitude+"");
        //this.aviso(longitude+"");
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        inicializaComponentes();
        // - Adiciconando marcador - exemplo
        //addMarkerMap(-18.921052, -48.257157);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }



    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        mMap.addMarker(new MarkerOptions().position(this.LtLg).title("Onde estou"));
        this.addMarkerMap(GPSHelper.TargetedPosto.latitude, GPSHelper.TargetedPosto.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.LtLg, 14.0f));

    }

    private void chamaTelaDeLogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }

    private void aviso(CharSequence text) {

        Context context = getApplicationContext();
        //CharSequence text = "Usuario ou senha invalidos, por favor verifique dados ouregistre-se";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.BtNavegar:
                AbreNavegacao();
                break;
        }
    }

    public void addMarkerMap(double lat, double log) {

        LatLng temp = new LatLng(lat, log);

        // - Adicionando o marcador do posto
        mMap.addMarker(new MarkerOptions()
                .position(temp)
                .title("Marker")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


    }


    public void AbreNavegacao() {


        Uri gmmIntentUri = Uri.parse("google.navigation:q=-18.921052, -48.257157");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}

