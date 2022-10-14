package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btn_quiz = findViewById(R.id.btn_quiz);
        Button btn_historia = findViewById(R.id.btn_historia);
        Button btn_balanca = findViewById(R.id.btn_balanca);
        Button btn_curiosidade = findViewById(R.id.btn_curiosidade);
        Button btn_local = findViewById(R.id.btn_hq3);
        Button btn_inicio = findViewById(R.id.btn_inicio);

        btn_quiz.setOnClickListener(view -> {
            Intent intent = new Intent(this, quiz_Activity.class);
            startActivity(intent);
        });

        btn_historia.setOnClickListener(view -> {
            Intent intent = new Intent(this, diferencas_Activity.class);
            startActivity(intent);
        });

        btn_balanca.setOnClickListener(view -> {
            Intent intent = new Intent(this, sensor_Activity.class);
            startActivity(intent);
        });

        btn_curiosidade.setOnClickListener(view -> {
            Intent intent = new Intent(this, curiosidades_Activity.class);
            startActivity(intent);
        });

        btn_local.setOnClickListener(view -> {
            Intent intent = new Intent(this, mapa_activity.class);
            startActivity(intent);
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(this, inicio_Activity.class);
            startActivity(intent);
        });
    }
}