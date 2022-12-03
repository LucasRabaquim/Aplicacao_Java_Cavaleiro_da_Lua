package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class sorteio_Activity extends AppCompatActivity {

    EditText mensagem, email;
    Button enviar;
    String nome_dir = "Sorteio";
    String nome_arquivo = "dados.txt";
    File dir = new File(nome_arquivo);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio);
        mensagem = findViewById(R.id.edit_mensagem);
        enviar = findViewById(R.id.btn_enviar);

        ler();
        enviar.setOnClickListener(view ->{salvar(); });
    }

    public void ler(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(nome_arquivo);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader leitor = new BufferedReader(isr);
                StringBuilder construtor = new StringBuilder();
                String texto;
                while ((texto = leitor.readLine()) != null)
                    construtor.append(texto);
                mensagem.setText(construtor.toString());
                Toast.makeText(this, texto + "aeee", Toast.LENGTH_LONG).show();
                fis.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "A", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "B", Toast.LENGTH_LONG).show();
            }

    }

    public void salvar(){
        String string = mensagem.getText().toString();
        if(!dir.exists())
            dir.mkdirs();
        try {
            FileOutputStream fos = openFileOutput(nome_arquivo, MODE_PRIVATE);
            fos.write(string.getBytes());
            Toast.makeText(this,  "aeee", Toast.LENGTH_LONG).show();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "merda", Toast.LENGTH_LONG).show();
        }
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