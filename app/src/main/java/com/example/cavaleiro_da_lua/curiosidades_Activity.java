package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class curiosidades_Activity extends AppCompatActivity {

    ImageView img_fase_lua;
    ImageView img_curio;
    TextView view_txt_curio;
    int index_curio;
    static public final String EXTRA_CURIO = ".curiosidade";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosidades);
        view_txt_curio = findViewById(R.id.txt_Curio);
        img_fase_lua = findViewById(R.id.img_lua);
        img_curio = findViewById(R.id.img_curio);


        ImageButton btn_proxima = findViewById(R.id.btn_curio_proxima);
        ImageButton btn_anterior = findViewById(R.id.btn_curio_anterior);


        Intent dadosIntent = getIntent();
        index_curio = dadosIntent.getIntExtra(curiosidades_Activity.EXTRA_CURIO,0);

        mostrar_curiosidade();
        btn_proxima.setOnClickListener(view -> {
            index_curio++;
            if(index_curio > 3) {
                index_curio = 0;
            }
            trocar_curiosidade();
        });
        btn_anterior.setOnClickListener(view -> {
            index_curio--;
            if(index_curio < 0){
                index_curio = 3;
            }
            trocar_curiosidade();
        });



    }

    private void mostrar_curiosidade(){
        String[] txt_curio = getResources().getStringArray(R.array.txt_curio);
        int[] fases_lua = {
                R.drawable.nova, R.drawable.crescente, R.drawable.minguante, R.drawable.cheia
        };
        int[] curio = {
                R.drawable.transtorno, R.drawable.traje, R.drawable.origem, R.drawable.poderes
        };
        img_fase_lua.setImageResource(fases_lua[index_curio]);
        img_curio.setImageResource(curio[index_curio]);
        view_txt_curio.setText(txt_curio[index_curio]);

    }

    private void trocar_curiosidade(){
        Intent intent = new Intent(this, curiosidades_Activity.class);
        intent.putExtra(EXTRA_CURIO, index_curio);
        startActivity(intent);
    }

    private void compartilhar() {
        String[] txt_curio_perguntas = getResources().getStringArray(R.array.txt_curio_pergunta);
        Intent intentCompartilhar = new Intent();
        intentCompartilhar.setAction(Intent.ACTION_SEND);
        String mensagem = txt_curio_perguntas[index_curio];
        intentCompartilhar.putExtra(Intent.EXTRA_TEXT, mensagem);
        intentCompartilhar.setType("text/plain");
        startActivity(Intent.createChooser(intentCompartilhar, getString(R.string.chooser_compartilhar)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.add(Menu.NONE, 8, Menu.NONE, "Compartilhar");
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
                intent = new Intent(getApplicationContext(),inicio_Activity.class);
                break;
            case 8:
                compartilhar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}