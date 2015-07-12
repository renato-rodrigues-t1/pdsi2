package com.gaslibre.gaslibre.Control.Service;

import android.content.Context;
import android.location.LocationManager;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;


public class GPSHelper {

    private Context context;
    // flag for GPS Status
    private boolean isGPSEnabled = false;
    // flag for network status
    private boolean isNetworkEnabled = false;
    private LocationManager locationManager;
    private Location location;
    private double latitude;
    private double longitude;
    public static LatLng TargetedPosto;

    public GPSHelper(Context context) {
        this.context = context;

        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

    }

    public void getMyLocation() {
        List<String> providers = locationManager.getProviders(true);

        Location l = null;
        for (int i = 0; i < providers.size(); i++) {
            l = locationManager.getLastKnownLocation(providers.get(i));
            if (l != null)
                break;
        }
        if (l != null) {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
        }
    }

    public boolean isGPSenabled() {
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return (isGPSEnabled || isNetworkEnabled);
    }

    /**
     * Function to get latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Function to get longitude
     */
    public double getLongitude() {
        return longitude;
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

}

