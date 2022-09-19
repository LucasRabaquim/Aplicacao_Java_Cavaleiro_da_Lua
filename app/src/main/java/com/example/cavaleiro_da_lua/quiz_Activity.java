package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class quiz_Activity extends AppCompatActivity {

    // Os radio buttons das perguntas, para saberoms qual o usuário marcou.
    RadioButton rd1, rd2, rd3, rd4;

    // Imagem da pergunta
    ImageView view_imagem;

    //A pergunta e a quantidade e acertos
    TextView view_txt_pergunta,view_txt_acertos;

    // Array para guardar o texto de cada pergunta
    String[] texto_pergunta = {
            "1) Onde Steven Grant trabalha?",
            "2) Qual Deus NÃO estava no episódio 3?",
            "3) Qual a forma da Deusa Tuéris?",
            "4) Como eram os detalhes da\n bandana do pai de Layla?",
            "5) Qual era o filme favorito do Marc?",
            "6) Onde Amnit está aprisionada?"
    };

    // Array bidimensional para guardar o texto de cara alternativa
    String[][] alternativa_pergunta ={
            {"Uma Loja de Presentes","Um asilo de idosos","É desempregado","Atendente de supermercado"},
            {"Hórus","Hartor","Amnit","Ísis"},
            {"Um Chacal","Uma humana de 8 braços","Uma águia","Um hipopótamo"},
            {"Tinha escaravelhos feitos a mão","Tinha desenhos de lua","Tinha flores e corações vermelhos","Tinha desenhos de pirâmides"},
            {"As aventuras de Steven Grant","Tomb Buster","Ruster Tomb","Os Trapalhões"},
            {"Na pirâmide de Gize","No sarcófago de Senfu","No templo do céu","Na tumba de Alexandre, o Grade"}
    };

    // Marca em qual questão estamos e o número de acertos
    int pergunta_atual = 0, acertos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // Até view_txt_acertos: pegando o id para usar as views
        rd1 = findViewById(R.id.rd_resposta1);
        rd2 = findViewById(R.id.rd_resposta2);
        rd3 = findViewById(R.id.rd_resposta3);
        rd4 = findViewById(R.id.rd_resposta4);
        view_imagem = findViewById(R.id.img_quiz);
        view_txt_pergunta = findViewById(R.id.txt_quiz);
        view_txt_acertos = findViewById(R.id.txt_acertos);

        // Array com os radio buttons para facilitar colocar o texto nas alternativas;
        RadioButton[] rd_pergunta = {rd1, rd2, rd3, rd4};

        // Array para facilitar colocar as imagens
        int[] imagem = {
                R.drawable.quiz_pergunta1,
                R.drawable.quiz_pergunta2,
                R.drawable.quiz_pergunta3,
                R.drawable.quiz_pergunta4,
                R.drawable.quiz_pergunta5,
                R.drawable.quiz_pergunta6
        };

        // Capa pergunta é uma activity usando a mesma tela e o mesmo código, a linha abaixo é para pegar os valores passados
        Intent dadosIntent = getIntent();

        // Pega a pergunta atual, se viemos do menu não passamos nada, por isso o 0 como valor padrão.
        pergunta_atual = dadosIntent.getIntExtra(quiz_Activity.EXTRA_PERGUNTA,0);

        // Pega o número de acertos
        acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO,0);

        // Até view_imagem: monta a pergunta para o usuário
        for(int i = 0; i < 4; i++)
            rd_pergunta[i].setText(alternativa_pergunta[pergunta_atual][i]);
        view_txt_pergunta.setText(texto_pergunta[pergunta_atual]);
        view_imagem.setImageResource(imagem[pergunta_atual]);

        // Mostra o número de acertos
        view_txt_acertos.setText("Acertos: " + acertos + "/6");
    }

    // As duas linhas abaixo são os parâmetros que vamos passar para a proxima Activity
    static public final String EXTRA_PERGUNTA = ".pergunta";
    static public final String EXTRA_ACERTO = ".acerto";

    public void proximaPergunta(View view) {
        // Guarda quais alternativas são as certas para cada questão
        RadioButton[] rd_resposta = {rd1, rd3, rd4, rd1, rd2, rd4};

        // Se o usuário marcou o radio button certo, ele acertou uma questão
        if(rd_resposta[pergunta_atual].isChecked())
            acertos++;
        if(!(rd1.isChecked() || rd2.isChecked() || rd3.isChecked()) || rd4.isChecked()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.msgbox_quiz_texto).setTitle(R.string.msgbox_quiz_titulo);
            builder.create().show();
        }
        // Se já é a 5 (6) pergunta vai para a tela de resultado
        else if(pergunta_atual == 5){
            Intent intent = new Intent(this, resultado_quiz.class);
            intent.putExtra(EXTRA_ACERTO, acertos);
            startActivity(intent);
        }
        else {
            pergunta_atual++;

            // Instancia a proxima tela
            Intent intent = new Intent(this, quiz_Activity.class);

            // Coloca valores nos parâmetros declarados anteriormente
            intent.putExtra(EXTRA_PERGUNTA, pergunta_atual);
            intent.putExtra(EXTRA_ACERTO, acertos);

            // Inicia a proxima activity
            startActivity(intent);
        }
    }
    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}