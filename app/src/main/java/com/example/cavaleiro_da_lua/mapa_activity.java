package com.example.cavaleiro_da_lua;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class mapa_activity extends AppCompatActivity{

    TextView view_distancia, view_local;
    ImageView view_imagem;
    RadioButton rd1,rd2,rd3,rd4;
    String[] local = {"Museu de Belas Artes de Budapeste","Coblença na Alemanha","Bairro histórico do Cairo, Egito","Empresa organizadora de eventos\n em Budapeste"};
    int[] imagem = {R.drawable.museu,R.drawable.castelo,R.drawable.cairo,R.drawable.manicomio};
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
        view_imagem = findViewById(R.id.img_local);
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
                localEscolhido(museu,local[0],imagem[0]);
            else if(rd2.isChecked())
                localEscolhido(castelo,local[1],imagem[1]);
            else if(rd3.isChecked())
                localEscolhido(cairo,local[2],imagem[2]);
            else if(rd4.isChecked())
                localEscolhido(manicomio,local[3],imagem[3]);
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.msgbox_local_texto).setTitle(R.string.msgbox_local_titulo);
                builder.create().show();
            }
        }
    }

    double[] localMapa = museu;
    public void localEscolhido(double[] local, String titulo, int imagem){
        double latitude = localizacao_classe.latitude;
        double longitude = localizacao_classe.longitude;
        double distancia = Math.round(Math.sqrt(Math.pow(latitude -(local[0]),2) + Math.pow(longitude -(local[1]),2))* 111139/1000);
        String texto = "A distância é de:\n" + Double.toString(distancia) + " Km";
        view_distancia.setText(texto);
        view_local.setText(titulo);
        view_imagem.setImageResource(imagem);
        localMapa = local;
    }

    public void mostrarMapa(View view) {
        String mapa = "geo:" + localMapa[0] + "," + localMapa[1];
        Uri urlMapa = Uri.parse(mapa);
        Intent intentMapa = new Intent(Intent.ACTION_VIEW, urlMapa);
        startActivity(intentMapa);
    }

    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}