package com.example.cavaleiro_da_lua;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class inicio_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        // Pegando o id das TextViews para "Ativar a functionalism do link"
        TextView txt_noticia1 = findViewById(R.id.txt_noticia1);
        TextView txt_noticia2 = findViewById(R.id.txt_noticia2);
        TextView txt_noticia3 = findViewById(R.id.txt_noticia3);
        txt_noticia1.setMovementMethod(LinkMovementMethod.getInstance());
        txt_noticia2.setMovementMethod(LinkMovementMethod.getInstance());
        txt_noticia3.setMovementMethod(LinkMovementMethod.getInstance());

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