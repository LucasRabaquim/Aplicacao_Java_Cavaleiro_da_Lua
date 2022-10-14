package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class resultado_Activity extends AppCompatActivity {

    ImageView view_imagem;
    TextView view_txt_resultado;
    TextView view_txt_descricao;
    Button btn_menu;
    Button btn_voltar_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);
        view_imagem = findViewById(R.id.img_resultado);
        btn_menu = findViewById(R.id.btn_menu);
        btn_voltar_quiz = findViewById(R.id.btn_voltar);
        view_txt_resultado = findViewById(R.id.txt_resultado);
        view_txt_descricao = findViewById(R.id.txt_descricao);
        view_imagem = findViewById(R.id.img_resultado);

        // Pega os dados passados pelo quiz
        Intent dadosIntent = getIntent();
        int acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO,0);
        if(acertos > 4)
            criarPagina(0);
        else if(acertos > 2)
            criarPagina(1);
        else if(acertos > 0)
            criarPagina(2);
        else
            criarPagina(3);

        // Botão de Sair
        btn_menu.setOnClickListener(view -> {
            Intent intent = new Intent(resultado_Activity.this, menu_Activity.class);
            startActivity(intent);
        });
        btn_voltar_quiz.setOnClickListener(view -> {
            Intent intent = new Intent(resultado_Activity.this, quiz_Activity.class);
            startActivity(intent);
        });
    }

    // Coloca as informações recebidas na tela
    public void criarPagina(int index){
        int[] img = {
                R.drawable.acertos6,R.drawable.acertos4,R.drawable.acertos2,R.drawable.acertos0
        };
        String[] txt_resultado = getResources().getStringArray(R.array.txt_resultado_quiz);
        String[] txt_descricao = getResources().getStringArray(R.array.txt_descricao_quiz);

        view_imagem.setImageResource(img[index]);
        view_txt_resultado.setText(txt_resultado[index]);
        view_txt_descricao.setText(txt_descricao[index]);
    }
}