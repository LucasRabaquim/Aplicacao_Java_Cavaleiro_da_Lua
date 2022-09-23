package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class inicio_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        // Pegando o id das TextViews para "Ativar a funcionalidade do link"
        TextView txt_noticia1 = findViewById(R.id.txt_noticia1);
        TextView txt_noticia2 = findViewById(R.id.txt_noticia2);
        TextView txt_noticia3 = findViewById(R.id.txt_noticia3);
        txt_noticia1.setMovementMethod(LinkMovementMethod.getInstance());
        txt_noticia2.setMovementMethod(LinkMovementMethod.getInstance());
        txt_noticia3.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // Botão para acessar o menu, intent explicita.
    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}