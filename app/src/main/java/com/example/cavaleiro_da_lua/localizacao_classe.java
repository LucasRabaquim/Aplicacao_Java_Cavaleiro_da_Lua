package com.example.cavaleiro_da_lua;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class localizacao_classe implements LocationListener {
    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(Location location) {
        latitude  = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
