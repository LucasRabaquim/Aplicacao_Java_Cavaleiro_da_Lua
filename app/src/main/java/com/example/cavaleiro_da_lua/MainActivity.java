package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar barra;
    Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao = findViewById(R.id.btn_entrar);
        barra = findViewById(R.id.progressBar_entrar);
    }
    public void entrar(View view) throws InterruptedException {
        botao.setVisibility(View.INVISIBLE);
        barra.setVisibility(View.VISIBLE);
        Thread.sleep(10000);
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}