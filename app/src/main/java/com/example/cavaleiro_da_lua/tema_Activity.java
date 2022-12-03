package com.example.cavaleiro_da_lua;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class tema_Activity extends AppCompatActivity{

    Button btn_aplicar,btn_voltar;
    TextView txt_tema,txt_titulo;
    Switch switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        btn_aplicar = findViewById(R.id.btn_aplicar);
        btn_voltar = findViewById(R.id.btn_volta);
        txt_tema = findViewById(R.id.txt_tema);
        txt_titulo = findViewById(R.id.txt_tema_titulo);
        switcher = findViewById(R.id.switch_tema);
        carregar();
        btn_aplicar.setOnClickListener(view -> salvar());
        btn_voltar.setOnClickListener(view -> finish());
        switcher.setOnClickListener(view ->
            txt_tema.setText( (switcher.isChecked()) ? "Escuro" : "Claro")
        );
    }
    public void carregar(){
        Tema tema = new Tema();
        SharedPreferences settings = getSharedPreferences("com.example.cavaleiro_da_lua", 0);
        boolean temaAtual = tema.recuperar_tema(settings);
        txt_tema.setText( (temaAtual) ? "Escuro" : "Claro");
        switcher.setChecked(temaAtual);
    }
    public void salvar(){
        Tema tema = new Tema();
        SharedPreferences settings = getSharedPreferences("com.example.cavaleiro_da_lua", 0);
        tema.escolher_tema(settings, switcher.isChecked());
        AppCompatDelegate.setDefaultNightMode(tema.aplicar_tema(settings));
        Intent intent = new Intent(getApplicationContext(), tema_Activity.class);
        finish();
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        MenuClass menu = new MenuClass();
        Intent intent = menu.selecionarMenu(getApplicationContext(), item);
        if(intent != null)
            startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}




