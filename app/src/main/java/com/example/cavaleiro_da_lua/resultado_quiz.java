package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class resultado_quiz extends AppCompatActivity {

    ImageView view_imagem;
    TextView view_txt_resultado;
    TextView view_txt_descricao;

    // Coloca as informações recebidas na tela
    public void criarPagina(int resultado, int descricao,int img){
        view_imagem.setImageResource(img);
        view_txt_resultado.setText(getResources().getString(resultado));
        view_txt_descricao.setText(getResources().getString(descricao));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);
        view_imagem = findViewById(R.id.img_resultado);
        view_txt_resultado = findViewById(R.id.txt_resultado);
        view_txt_descricao = findViewById(R.id.txt_descricao);

        // Pega os dados passados pelo quiz
        Intent dadosIntent = getIntent();
        int acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO,0);
        if(acertos > 4)
            criarPagina(R.string.txt_resultado1,R.string.txt_descricao1,R.drawable.acertos6);
        else if(acertos > 2)
            criarPagina(R.string.txt_resultado2,R.string.txt_descricao2,R.drawable.acertos4);
        else if(acertos > 0)
            criarPagina(R.string.txt_resultado3,R.string.txt_descricao3,R.drawable.acertos2);
        else
            criarPagina(R.string.txt_resultado4,R.string.txt_descricao4,R.drawable.acertos0);
    }

    public void voltar(View view){
        Intent intent = new Intent(this, quiz_Activity.class);
        startActivity(intent);
    }
    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}