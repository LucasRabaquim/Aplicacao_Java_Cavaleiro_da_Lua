package com.example.cavaleiro_da_lua;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

class Tema extends Activity{

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
    public void aplicar_tema(Context context, SharedPreferences settings, View view, Button[] botoes, TextView[] textos){
        int[] cores = pegarCores(context,settings);
        view.setBackgroundColor(cores[0]);
        tema_botao(botoes,cores[1],cores[2]);
        tema_texto(textos,cores[3],cores[2]);
    }
    public void tema_botao(Button[] arrayText,int cor, int texto){
        for(Button botao : arrayText){
            botao.setBackgroundColor(cor);
            botao.setTextColor(texto);
        }
    }
    public void tema_texto(TextView[] arrayText,int cor, int texto){
        for(TextView view : arrayText) {
            view.setBackgroundColor(cor);
            view.setTextColor(texto);
        }
    }
    public void tema_radio(RadioButton[] radio,int cor, int texto){
        for(RadioButton botao : radio) {
            botao.setBackgroundColor(cor);
            botao.setTextColor(texto);
        }
    }
    public int[] pegarCores(Context context,SharedPreferences settings){
        int fundo = ContextCompat.getColor(context, R.color.white);
        int botao = ContextCompat.getColor(context, R.color.branco4);
        int texto = ContextCompat.getColor(context, R.color.preto1);
        int fundoTexto = ContextCompat.getColor(context, R.color.brancoTransparente);
        if(recuperar_tema(settings)){
            fundo = ContextCompat.getColor(context, R.color.black);
            botao = ContextCompat.getColor(context, R.color.preto4);
            texto = ContextCompat.getColor(context, R.color.branco1);
            fundoTexto = ContextCompat.getColor(context, R.color.pretoTransparente);
        }
        int[] cores = {fundo,botao,texto,fundoTexto};
        return cores;
    }

}
