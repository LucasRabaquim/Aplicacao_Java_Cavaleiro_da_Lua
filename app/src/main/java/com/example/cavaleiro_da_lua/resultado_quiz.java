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

    public void criarPagina(String resultado, String descricao,int img){
        view_imagem.setImageResource(img);
        view_txt_resultado.setText(resultado);
        view_txt_descricao.setText(descricao);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);
        view_imagem = findViewById(R.id.img_resultado);
        view_txt_resultado = findViewById(R.id.txt_resultado);
        view_txt_descricao = findViewById(R.id.txt_descricao);
        Intent dadosIntent = getIntent();
        int acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO,0);
        if(acertos > 4)
            criarPagina("Parabéns !","Você é um verdadeiro cavaleiro da lua.",R.drawable.acertos6);
        else if(acertos > 2)
            criarPagina("Muito bom","Você é honrado viajante da noite",R.drawable.acertos4);
        else if(acertos > 0)
            criarPagina("Dá para melhorar","Recomendo re-assistir a série.",R.drawable.acertos2);
        else
            criarPagina("Qual é?","Você assistiu a série antes? Khonshu está furioso.",R.drawable.acertos0);
    }
    public void voltar(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}