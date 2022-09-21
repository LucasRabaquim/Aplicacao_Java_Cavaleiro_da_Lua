package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class inicio_activity extends AppCompatActivity {

    TextView txt_noticia1,txt_noticia2,txt_noticia3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        txt_noticia1 = findViewById(R.id.txt_noticia1);
        txt_noticia2 = findViewById(R.id.txt_noticia2);
        txt_noticia3 = findViewById(R.id.txt_noticia3);
        txt_noticia1.setMovementMethod(LinkMovementMethod.getInstance());
        txt_noticia2.setMovementMethod(LinkMovementMethod.getInstance());
        txt_noticia3.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}