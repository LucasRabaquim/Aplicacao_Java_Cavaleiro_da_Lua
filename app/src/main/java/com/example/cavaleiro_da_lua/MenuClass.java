package com.example.cavaleiro_da_lua;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

public class MenuClass {
   public Intent selecionarMenu(Context activity, MenuItem item) {
      Class[] arrayTelas = {curiosidades_Activity.class, mapa_activity.class, diferencas_Activity.class, sensor_Activity.class, quiz_Activity.class,  tema_Activity.class, inicio_Activity.class, sorteio_Activity.class, Review_Activity.class};
      int[] idItem = {R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8, R.id.item9};
      for(int i = 0; i < arrayTelas.length; i++)
         if(item.getItemId() == idItem[i])
            return new Intent(activity, arrayTelas[i]);
      return null;
   }
}