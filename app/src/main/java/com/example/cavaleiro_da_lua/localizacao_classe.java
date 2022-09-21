package com.example.cavaleiro_da_lua;

import android.location.Location;
import android.location.LocationListener;

public class localizacao_classe implements LocationListener {
    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(Location location) {
        latitude  = location.getLatitude();
        longitude = location.getLongitude();
    }

}
