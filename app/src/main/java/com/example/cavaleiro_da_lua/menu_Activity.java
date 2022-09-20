package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void telaQuiz(View view){
        Intent intent = new Intent(this, quiz_Activity.class);
        startActivity(intent);
    }
    public void diferencas(View view) {
        Intent intent = new Intent(this, diferencas_Activity.class);
        startActivity(intent);
    }
    public void balanca(View view) {
        Intent intent = new Intent(this, sensor_Activity.class);
        startActivity(intent);
    }
    public void curiosidades(View view) {
        Intent intent = new Intent(this, curiosidades_Activity.class);
        startActivity(intent);
    }
    public void local(View view) {
        Intent intent = new Intent(this, mapa_activity.class);
        startActivity(intent);
    }
    public void inicio(View view) {
        Intent intent = new Intent(this,inicio_activity.class);
        startActivity(intent);
    }
}