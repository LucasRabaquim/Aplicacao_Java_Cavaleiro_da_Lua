package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class curiosidades_Activity extends AppCompatActivity {

    ImageView view_imagem1;
    ImageView view_imagem2;
    TextView view_txt_curio;

    int Curio_atual = 0;
    int[] texto_Curio = {R.string.txt_curio1, R.string.txt_curio2, R.string.txt_curio3, R.string.txt_curio4};
    int[] perguntas = {R.string.txt_curio_perguntas1, R.string.txt_curio_perguntas2,R.string.txt_curio_perguntas3,R.string.txt_curio_perguntas4};



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosidades);
        view_txt_curio = findViewById(R.id.txt_Curio);
        view_imagem1 = findViewById(R.id.img_Curio2);
        view_imagem2 = findViewById(R.id.img_Curio);
        int transtorno = R.drawable.transtorno;
        int traje = R.drawable.traje;
        int origem = R.drawable.origem;
        int poderes = R.drawable.poderes;
        int luanova = R.drawable.nova;
        int luacrescente = R.drawable.crescente;
        int luaminguante = R.drawable.minguante;
        int luacheia = R.drawable.cheia;

        Intent dadosIntent = getIntent();
        Curio_atual = dadosIntent.getIntExtra(curiosidades_Activity.EXTRA_CURIO,0);
        if(Curio_atual == 0){
            view_imagem2.setImageResource(transtorno);
            view_imagem1.setImageResource(luanova);
        }
        else if(Curio_atual == 1) {
            view_imagem2.setImageResource(poderes);
            view_imagem1.setImageResource(luacrescente);
        }
        else if(Curio_atual == 2) {
            view_imagem2.setImageResource(traje);
            view_imagem1.setImageResource(luaminguante);
        }
        else{
            view_imagem2.setImageResource(origem);
            view_imagem1.setImageResource(luacheia);
        }
        view_txt_curio.setText(getResources().getString(texto_Curio[Curio_atual]));
    }

    static public final String EXTRA_CURIO = ".curiosidade";

    public void proxima(View view){
        Curio_atual++;
        if(Curio_atual > 3)
            Curio_atual = 0;
        Intent intent = new Intent(this, curiosidades_Activity.class);
        intent.putExtra(EXTRA_CURIO, Curio_atual);
        startActivity(intent);
    }
    public void anterior(View view){
        Curio_atual--;
        if(Curio_atual < 0)
            Curio_atual = 3;
        Intent intent = new Intent(this, curiosidades_Activity.class);
        intent.putExtra(EXTRA_CURIO, Curio_atual);
        startActivity(intent);
    }

    public void compartilhar(View view) {
        Intent intentCompartilhar = new Intent();
        intentCompartilhar.setAction(Intent.ACTION_SEND);
        String mensagem = getResources().getString(perguntas[Curio_atual]);
        intentCompartilhar.putExtra(Intent.EXTRA_TEXT, mensagem);
        intentCompartilhar.setType("text/plain");
        startActivity(intentCompartilhar);
    }



    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}