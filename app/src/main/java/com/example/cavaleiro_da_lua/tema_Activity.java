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
        btn_aplicar.setOnClickListener(view -> carregar());
    }
    public void carregar(){
        Button[] botoes = {btn_aplicar,btn_voltar};
        TextView[] textos = {txt_tema,txt_titulo};
        Tema tema = new Tema();
        SharedPreferences settings = getSharedPreferences("com.example.cavaleiro_da_lua", 0);
        tema.escolher_tema(settings,switcher.isChecked());
        boolean temaAtual = tema.recuperar_tema(settings);
        tema.aplicar_tema(getApplicationContext(),settings, this.findViewById(android.R.id.content),botoes,textos);
        txt_tema.setText( (temaAtual) ? "Escuro" : "Claro");
        switcher.setChecked(temaAtual);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch(item.getItemId()){
            case R.id.item1:
                intent = new Intent(getApplicationContext(), curiosidades_Activity.class);
                break;
            case R.id.item2:
                intent = new Intent(getApplicationContext(), mapa_activity.class);
                break;
            case R.id.item3:
                intent = new Intent(getApplicationContext(), diferencas_Activity.class);
                break;
            case R.id.item4:
                intent = new Intent(getApplicationContext(), sensor_Activity.class);
                break;
            case R.id.item5:
                intent = new Intent(getApplicationContext(), quiz_Activity.class);
                break;
            case R.id.item6:
                intent = new Intent(getApplicationContext(),inicio_Activity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}




