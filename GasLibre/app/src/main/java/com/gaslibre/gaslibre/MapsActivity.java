package com.gaslibre.gaslibre;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.DAO.DBManager;
import android.content.Context;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private UserController usuarioController= new UserController(this);
    private DBManager dbManager;
    private Context context;
    //osvaldo teste
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //verifica se ususario logado (se tem usuario na sessao)
         if(!usuarioController.estaLogadoNaSessao()){
           //caso em que nao estah logado direciona pra tela de login
            chamaTelaDeLogin();
        }

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

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

        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));

        //Habilita MyLocation para o Maps
        mMap.setMyLocationEnabled(true);

        //Pega o objeto ~LocationManager~ do System Service ~LOCATION_SERVICE~
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Criteria � uma classe que indica os crit�rios de aplica��o para a sele��o de um provedor de localiza��o
        Criteria localizacao = new Criteria();

        // Pega o nome do melhor provedor
        String provedor = locationManager.getBestProvider(localizacao, true);

        //Pegar localiza��o corrente
        Location meuLocal = locationManager.getLastKnownLocation(provedor);

        //Indica o tipo do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        double latitude = meuLocal.getLatitude();
        double longitude = meuLocal.getLongitude();

        LatLng latlong = new LatLng(latitude,longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong));

        //Zoom e localiza��o do caboco no Maps
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("Voc� est� aqui!"));

    }

    private void chamaTelaDeLogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }

}
