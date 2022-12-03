package com.example.cavaleiro_da_lua;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class mapa_activity extends AppCompatActivity{

    // Declarando elementos do design
    TextView view_distancia, view_local;
    ImageView view_imagem;
    RadioButton rd1;
    RadioButton rd2;
    RadioButton rd3;
    RadioButton rd4;

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
        Button btn_local = findViewById(R.id.btn_hq3);
        btn_local.setOnClickListener(view -> mostrarLocal());
    }

    private void verificarPermissao(){
        boolean local_preciso = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        boolean local_aproximado = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        if(local_preciso && local_aproximado) {
            ActivityCompat.requestPermissions(mapa_activity.this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(mapa_activity.this, new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(mapa_activity.this, new String[]
                    {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }
    }

    private boolean gpsAtivo(){
        LocationManager  locationManager  = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new localizacao_classe();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
        }
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void mostrarLocal() {
        try {
            verificarPermissao();
            if (gpsAtivo()) {
                if (rd1.isChecked()) {
                    localEscolhido(0);
                } else if (rd2.isChecked()) {
                    localEscolhido(1);
                } else if (rd3.isChecked()) {
                    localEscolhido(2);
                } else if (rd4.isChecked()) {
                    localEscolhido(3);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.msgbox_local_texto).setTitle(R.string.msgbox_local_titulo);
                    builder.create().show();
                }
            }
        }
        catch(Exception e){}
    }

    double[][] locais = {
            {47.5162, 19.0764},{50.2052, 7.3365},{30.0323, 31.2562},{47.4937, 19.1220}
    };
    int index_local = 0;
    // Calcula a distância do usuário até o local (desconsiderando a curvatura da Terra.)
    private void localEscolhido(int index){
        double latitude = localizacao_classe.latitude;
        double longitude = localizacao_classe.longitude;
        double distancia = Math.round(Math.sqrt(Math.pow(latitude -(locais[index][0]),2) +
                Math.pow(longitude -(locais[index][1]),2))* 111139/1000);
        String[] nome_local = getResources().getStringArray(R.array.txt_locais);
        String texto = getResources().getString(R.string.txt_formatar_distancia,distancia);
        view_distancia.setText(texto);
        view_local.setText(nome_local[index]);
        int[] imagem = {R.drawable.museu,R.drawable.castelo,R.drawable.cairo,R.drawable.manicomio};
        view_imagem.setImageResource(imagem[index]);
        index_local = index;
    }

    // Intent implicita para mostrar o mapa
    private void mostrarMapa() {
        String mapa = "geo:" + locais[index_local][0] + "," + locais[index_local][1];
        Uri urlMapa = Uri.parse(mapa);
        Intent intentMapa = new Intent(Intent.ACTION_VIEW, urlMapa);
        startActivity(intentMapa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.add(Menu.NONE, 8, Menu.NONE, "Abrir Maps");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        MenuClass menu = new MenuClass();
        Intent intent = menu.selecionarMenu(getApplicationContext(), item);
        if(intent != null)
            startActivity(intent);
        else
            mostrarMapa();
        return super.onOptionsItemSelected(item);
    }
}