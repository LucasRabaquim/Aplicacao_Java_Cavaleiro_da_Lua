package com.example.cavaleiro_da_lua;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class mapa_activity extends AppCompatActivity{

    TextView view_distancia, view_local;
    WebView view_mapa;
    RadioButton rd1,rd2,rd3,rd4;
    String[] local = {"Museu de Belas Artes de Budapeste","Coblença na Alemanha","Bairro histórico do Cairo, Egito","Empresa organizadora de eventos\n em Budapeste"};
    double[] museu = {47.5162, 19.0764},
             castelo = {50.2052, 7.3365},
             cairo = {30.0323, 31.2562},
             manicomio = {47.4937, 19.1220};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        view_distancia = findViewById(R.id.txt_distancia);
        view_local = findViewById(R.id.txt_local);
        view_mapa = findViewById(R.id.wb_local);
        rd1 = findViewById(R.id.rd1_local);
        rd2 = findViewById(R.id.rd2_local);
        rd3 = findViewById(R.id.rd3_local);
        rd4 = findViewById(R.id.rd4_local);
    }

    public void mostrarLocal(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mapa_activity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(mapa_activity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(mapa_activity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }
        LocationManager  locationManager  = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new localizacao_classe();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if(rd1.isChecked())
                localEscolhido(museu,local[0]);
            else if(rd2.isChecked())
                localEscolhido(castelo,local[1]);
            else if(rd3.isChecked())
                localEscolhido(cairo,local[2]);
            else if(rd4.isChecked())
                localEscolhido(manicomio,local[3]);
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.msgbox_local_texto).setTitle(R.string.msgbox_local_titulo);
                builder.create().show();
            }
        }
    }

    public void localEscolhido(double[] local, String titulo){
        double latitude = localizacao_classe.latitude;
        double longitude = localizacao_classe.longitude;
        double distancia = Math.sqrt(Math.pow(latitude -(local[0]),2) + Math.pow(longitude -(local[1]),2))* 111139/1000;
        distancia = Math.round(distancia);
        String texto = "A distância é de:\n" + Double.toString(distancia) + " Km";
        view_distancia.setText(texto);
        view_local.setText(titulo);
        mostrarMapa(local);
    }
    public void mostrarMapa(double[] local) {
        view_mapa.getSettings().setJavaScriptEnabled(true);
        view_mapa.loadUrl("https://www.google.com/maps/search/?api=1&query=" + local[0] + "," + local[1]);
    }

    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}