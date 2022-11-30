package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class problemas_Activity extends AppCompatActivity {

    EditText mensagem, email;
    Button enviar;
    String nome_dir = "Relato_de_problemas";
    String nome_arquivo = "mensagem.txt";
    File dir = new File(nome_arquivo);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problemas);
        mensagem = findViewById(R.id.edit_mensagem);
        email = findViewById(R.id.edit_email);
        enviar = findViewById(R.id.btn_enviar);
        ler();
        enviar.setOnClickListener(view ->{
salvar();
        });
    }

    public void ler(){
        if(!dir.exists())
            dir.mkdirs();
            try {
                FileInputStream fis = openFileInput(nome_arquivo);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader leitor = new BufferedReader(isr);
                StringBuilder construtor = new StringBuilder();
                StringBuilder texto = new StringBuilder();
                while (leitor.readLine() != null)
                    texto.append(mensagem.toString());
                mensagem.setText(texto);
                Toast.makeText(this, texto + "aeee", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "B", Toast.LENGTH_LONG).show();
            }

    }

    public void salvar(){
        try {
            Toast.makeText(this,  "aeee", Toast.LENGTH_LONG).show();
            File file = new File(dir, nome_arquivo);
            FileWriter writer = new FileWriter(file);
            writer.append(mensagem.getText());
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}