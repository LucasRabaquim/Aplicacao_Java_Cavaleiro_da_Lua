package com.example.cavaleiro_da_lua;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

public class MenuClass {
    public Intent selecionarMenu(Context activity, MenuItem item) {
        Class tela = null;
        switch (item.getItemId()) {
            case R.id.item1:
                tela = curiosidades_Activity.class;
                break;
            case R.id.item2:
                tela = mapa_activity.class;
                break;
            case R.id.item3:
                tela = diferencas_Activity.class;
                break;
            case R.id.item4:
                tela = sensor_Activity.class;
                break;
            case R.id.item5:
                tela = quiz_Activity.class;
                break;
            case R.id.item6:
                tela = tema_Activity.class;
                break;
            case R.id.item7:
                tela = inicio_Activity.class;
                break;
            case R.id.item8:
                tela = sorteio_Activity.class;
                break;
            case R.id.item9:
                tela = Review_Activity.class;
                break;
            default:
                break;
        }
        if(tela != null)
            return new Intent(activity, tela);
        return null;
    }
}
