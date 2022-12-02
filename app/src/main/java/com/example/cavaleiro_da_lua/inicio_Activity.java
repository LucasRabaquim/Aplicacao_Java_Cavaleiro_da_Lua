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
        Tema tema = new Tema();
        Button[] botoes = {};
        TextView[] textos = {txt_noticia1,txt_noticia2,txt_noticia3,findViewById(R.id.txt_inicio)};
        SharedPreferences settings = getSharedPreferences("com.example.cavaleiro_da_lua", 0);
        tema.aplicar_tema(getApplicationContext(),settings, this.findViewById(android.R.id.content),botoes,textos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
         switch(item.getItemId()){
             case R.id.item1:
                 intent = new Intent(getApplicationContext(), curiosidades_Activity.class);
                 break;
             case R.id.item2:
                 intent = new Intent(getApplicationContext(), mapa_activity.class);
                 break;
             case R.id.item3:
                 intent = new Intent(getApplicationContext(), diferencas_Activity.class);
                 break;
             case R.id.item4:
                 intent = new Intent(getApplicationContext(), sensor_Activity.class);
                 break;
             case R.id.item5:
                 intent = new Intent(getApplicationContext(), quiz_Activity.class);
                 break;
            case R.id.item6:
                intent = new Intent(getApplicationContext(),tema_Activity.class);
                break;
	    case R.id.item7:
                intent = new Intent(getApplicationContext(),inicio_Activity.class);
                break;
             case R.id.item8:
                 intent = new Intent(getApplicationContext(),problemas_Activity.class);
                 break;
			case R.id.item9:
                 intent = new Intent(getApplicationContext(),Review.class);
                 break;
             default:
                 return super.onOptionsItemSelected(item);
         }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}