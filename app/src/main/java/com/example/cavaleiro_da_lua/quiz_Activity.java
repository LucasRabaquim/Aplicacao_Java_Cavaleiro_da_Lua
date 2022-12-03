package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class quiz_Activity extends AppCompatActivity {

    // Declarando elementos do design.
    RadioButton rd1;
    RadioButton rd2;
    RadioButton rd3;
    RadioButton rd4;
    ImageView view_imagem;
    TextView view_txt_pergunta;
    TextView view_txt_acertos;

    // Variaveis globais com o numero da pergunta e a quantidade de acertos.
    int pergunta_atual;
    int acertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // "Ligando" o design com o código.
        rd1 = findViewById(R.id.rd_resposta1);
        rd2 = findViewById(R.id.rd_resposta2);
        rd3 = findViewById(R.id.rd_resposta3);
        rd4 = findViewById(R.id.rd_resposta4);
        Button btn_pergunta = findViewById(R.id.btn_proxima);
        view_imagem = findViewById(R.id.img_quiz);
        view_txt_pergunta = findViewById(R.id.txt_quiz);
        view_txt_acertos = findViewById(R.id.txt_acertos);

        // Chamando funções
        PegarDados();
        CriarPergunta();

        // Método que o botão vai chamar quando for clicado
        btn_pergunta.setOnClickListener(view -> {
            if(!(rd1.isChecked() || rd2.isChecked() || rd3.isChecked()|| rd4.isChecked())){
                MostrarErro();
            }
            else {
                VerificarResposta();
                proximaPergunta();
            }
        });

    }

    void PegarDados() {
        Intent dadosIntent = getIntent();
        pergunta_atual = dadosIntent.getIntExtra(quiz_Activity.EXTRA_PERGUNTA,0);
        acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO,0);
        view_txt_acertos.setText(getResources().getString(R.string.acertos, acertos));
    }

    void CriarPergunta(){

        int[] alternativas = {
                R.array.txt_alternativa_1, R.array.txt_alternativa_2, R.array.txt_alternativa_3,
                R.array.txt_alternativa_4, R.array.txt_alternativa_5, R.array.txt_alternativa_6
        };

        int[] imagem = {
                R.drawable.quiz_pergunta1, R.drawable.quiz_pergunta2, R.drawable.quiz_pergunta3,
                R.drawable.quiz_pergunta4, R.drawable.quiz_pergunta5, R.drawable.quiz_pergunta6
        };

        String[] txt_pergunta = getResources().getStringArray(R.array.txt_pergunta);
        String[] alternativa = getResources().getStringArray(alternativas[pergunta_atual]);

        rd1.setText(alternativa[0]);
        rd2.setText(alternativa[1]);
        rd3.setText(alternativa[2]);
        rd4.setText(alternativa[3]);

        // Coloca o texto e a imagem na tela respectivamente.
        view_txt_pergunta.setText(txt_pergunta[pergunta_atual]);
        view_imagem.setImageResource(imagem[pergunta_atual]);
    }

    // As duas linhas abaixo são os parâmetros que vamos passar para a proxima Activity
    static public final String EXTRA_PERGUNTA = ".pergunta";
    static public final String EXTRA_ACERTO = ".acerto";

    // Mensagem de erro
    void MostrarErro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msgbox_quiz_texto).setTitle(R.string.msgbox_quiz_titulo);
        builder.create().show();
    }

    // Verifica se o usuário acertou a questão
    void VerificarResposta(){
        RadioButton[] rd_resposta = {
                rd1, rd3, rd4, rd1, rd2, rd4
        };
        /* Na primeira pergunta a primeira alternativa é a certa, logo esse código quer dizer:
           Se o radio button da primeira questão (rd_resposta[0]) estiver marcado ele acertou!
        */
        if(rd_resposta[pergunta_atual].isChecked()){
            acertos++;
        }
        pergunta_atual++;
    }

    void proximaPergunta(){
        Intent intent;
        if(pergunta_atual == 6){
            intent = new Intent(this, resultado_Activity.class);
            // Não queremos saber o número da questão na tela de resultado.
        }
        else{
            intent = new Intent(this, quiz_Activity.class);
            intent.putExtra(EXTRA_PERGUNTA, pergunta_atual);
            // Precisamos saber o numero da questão para poder mostrar a próxima.
        }
        // Sempre vamos querer saber o número de acertos.
        intent.putExtra(EXTRA_ACERTO, acertos);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        MenuClass menu = new MenuClass();
        Intent intent = menu.selecionarMenu(getApplicationContext(), item);
        if(intent != null)
            startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}