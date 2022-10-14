package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    // Definindo os elementos do designs para programar neles
    ProgressBar barra;
    Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao = findViewById(R.id.btn_entrar);
        barra = findViewById(R.id.progressBar_entrar);
    }

    /* Botão para entrar, esconde o botão e mostra a barra de carregamento, mudando de tela após
     um tempo indeterminado. */
    public void entrar(View view){
        botao.setVisibility(View.INVISIBLE);
        barra.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, inicio_Activity.class);
        startActivity(intent);
    }
}