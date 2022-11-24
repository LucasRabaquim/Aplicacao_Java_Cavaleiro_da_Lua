package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button voltar = findViewById(R.id.btn_voltar_login);
        Button criar = findViewById(R.id.btn_criar_conta);
        Button logar = findViewById(R.id.btn_logar);

        logar.setOnClickListener(view -> {

            
        });
    }
}