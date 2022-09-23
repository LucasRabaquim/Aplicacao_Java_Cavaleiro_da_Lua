package com.example.cavaleiro_da_lua;

import android.location.Location;
import android.location.LocationListener;

public class localizacao_classe implements LocationListener {
    // Classe com dados de latitude e longitude e detecção de mudança de local.
    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(Location location) {
        latitude  = location.getLatitude();
        longitude = location.getLongitude();
    }

}
