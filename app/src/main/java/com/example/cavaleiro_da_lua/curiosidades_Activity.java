package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

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
    String[] perguntas = {"Que o cavaleiro da lua sofre de Transtorno Dissociativo de Identidade","Quais são os poderes do cavaleiro da lua?","Por que o trade do cavaleiro da lua é branco","Qual a origem da família do cavaleiro da lua"};
    String[] texto_Curio = {
            "1) O transtorno do Cavaleiro da Lua\n" +
                    "O personagem sofre de Transtorno Dissociativo de Identidade.\n Seus principais 'eus'" +
                    "são: O taxista Jack Lockey, o milionário Steven Grant, \n e seu eu verdadeiro eu, Marc Spector." +
                    "Jack Lockey \n aparece somente no último ep, durante as cenas pós-créditos.",

            "2) Seus poderes\n" +
                    "Ao aceitar o pacto com Khonshu, Marc ganha poderes como: visão noturna,\n" +
                    "a habilidade de enxergar o plano lunar, etc. \nTudo isso se modifica de acordo \n" +
                    "com as fases da lua.",

            "3) Por que seu traje é branco?\n" +
                    "Muitos super heróis se vestem com trajes discretos,\n com cores como preto," +
                    "para passarem despercebidos. Diferentes desses,\n o cavaleiro da lua se veste de branco,\n" +
                    "justamente para que seus unimigos o percebam.",
            "4) A origem Judaica \n" +
                    "A família de Marc Spector teve que fugir\n da Europa durante a perseguição aos judeus na\n" +
                    "Segunda Guerra Mundial. Encontraram refúgio nos Estados Unidos e\n" +
                    "Marc cresceu segundo os ensinamentos de seu pai rabino.",
    };


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
        view_txt_curio.setText(texto_Curio[Curio_atual]);
    }

    static public final String EXTRA_CURIO = ".curiosidade";

    public void proxima(View view){
       Curio_atual++;
       if(Curio_atual > 3)
           Curio_atual = 0;
       Intent intent = new Intent(this, curiosidades_Activity.class);
       intent.putExtra(EXTRA_CURIO, Curio_atual);
       // Inicia a proxima activity
       startActivity(intent);
    }
    public void anterior(View view){
        Curio_atual--;
        if(Curio_atual < 0)
            Curio_atual = 3;
        Intent intent = new Intent(this, curiosidades_Activity.class);
        intent.putExtra(EXTRA_CURIO, Curio_atual);
        // Inicia a proxima activity
        startActivity(intent);
    }

    public void compartilhar(View view) {
        // String mapa = "geo:" + localMapa[0] + "," + localMapa[1]
        Intent intentCompartilhar = new Intent();
        intentCompartilhar.setAction(Intent.ACTION_SEND);
        String mensagem = "Você sabe: " + perguntas[Curio_atual] + "? Baixe o app Cavaleiro da Lua para saber mais.";
        intentCompartilhar.putExtra(Intent.EXTRA_TEXT, mensagem);
        intentCompartilhar.setType("text/plain");
        startActivity(intentCompartilhar);
    }
    
    

    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}