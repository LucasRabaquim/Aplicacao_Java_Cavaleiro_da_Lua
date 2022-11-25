package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class resultado_Activity extends AppCompatActivity {

    ImageView view_imagem;
    TextView view_txt_resultado;
    TextView view_txt_descricao;
    TextView view_txt_acertos;
    Button btn_menu;
    Button btn_voltar_quiz;
    FloatingActionButton floatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);
        view_imagem = findViewById(R.id.img_resultado);
        btn_menu = findViewById(R.id.btn_menu);
        btn_voltar_quiz = findViewById(R.id.btn_voltar);
        floatingButton = findViewById(R.id.screenShot);
        view_txt_resultado = findViewById(R.id.txt_resultado);
        view_txt_descricao = findViewById(R.id.txt_descricao);
        view_imagem = findViewById(R.id.img_resultado);
        view_txt_acertos = findViewById(R.id.txt_resultado_quiz);

        // Pega os dados passados pelo quiz
        Intent dadosIntent = getIntent();
        int acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO,0);
        view_txt_acertos.setText(acertos + "/6");

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
        floatingButton.setOnClickListener(view -> tela());
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

    public Bitmap screenShot(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    public void tela(){

        String state = Environment.getExternalStorageState();

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        else{
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                Date now = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                try {
                    // image naming and path  to include sd card  appending name you choose for file
                    String dPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

                    // create bitmap screen capture
                    View v1 = getWindow().getDecorView().getRootView();
                    v1.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
                    v1.setDrawingCacheEnabled(false);

                    File imageFile = new File(dPath);

                    FileOutputStream outputStream = new FileOutputStream(imageFile);
                    int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Toast.makeText(this,"Captura de tela salva", Toast.LENGTH_SHORT).show();

                } catch (Throwable e) {
                    // Several error may come out with file handling or DOM
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }




}