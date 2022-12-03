package com.example.cavaleiro_da_lua;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

class Tema extends AppCompatActivity {

    public static final String PREFERENCIAS_NAME = "com.example.cavaleiro_da_lua";
    public static final String PREFERENCIAS_VALOR = "com.example.cavaleiro_da_lua";


    public void escolher_tema(SharedPreferences settings, boolean tema){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREFERENCIAS_VALOR,tema);
        editor.apply();
    }
    public boolean recuperar_tema(SharedPreferences settings) {
        return settings.getBoolean(PREFERENCIAS_VALOR, false);
    }
    public int aplicar_tema(SharedPreferences settings){
        return recuperar_tema(settings) ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
    }


}
