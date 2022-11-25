package com.example.cavaleiro_da_lua;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class tema_Activity extends AppCompatActivity {

    Button btn_aplicar,btn_voltar;
    TextView txt_tema;
    Switch switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);
        btn_aplicar = findViewById(R.id.btn_aplicar);
        btn_voltar = findViewById(R.id.btn_volta);
        txt_tema = findViewById(R.id.txt_tema);
        switcher = findViewById(R.id.switch_tema);
       /* tema_classe.recuperar_tema();

        btn_aplicar.setOnClickListener(view -> {
            boolean tema = switcher.isChecked();
            SharedPreferences settings = getSharedPreferences(PREFERENCIAS_NAME , 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(PREFERENCIAS_VALOR, tema);
            editor.apply();
            tema_classe.recuperar_tema();
        });*/
    }






}


