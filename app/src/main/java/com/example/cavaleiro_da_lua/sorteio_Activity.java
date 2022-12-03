package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AlertDialog;
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

    EditText mensagem;
    Button enviar;
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
                fis.close();
        }
        catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        catch (IOException e) {
                e.printStackTrace();
        }

    }

    public void salvar(){
        String string = mensagem.getText().toString();
        if(!dir.exists())
            dir.mkdirs();
        try {
            FileOutputStream fos = openFileOutput(nome_arquivo, MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
            Toast.makeText(this,"As informações foram salvas", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sorteio, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.item_ajuda:
                ajuda();
                break;
            case R.id.item_voltar:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    public void ajuda(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogo_sorteio_titulo);
        builder.setMessage(R.string.dialogo_sorteio_texto);
        builder.create().show();
    }
}