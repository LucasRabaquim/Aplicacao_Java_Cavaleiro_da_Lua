package com.example.cavaleiro_da_lua;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class resultado_Activity extends AppCompatActivity {

   ImageView view_imagem;
   TextView view_txt_resultado, view_txt_descricao, view_txt_acertos;
   Button btn_voltar_quiz;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_resultado_quiz);
      view_imagem = findViewById(R.id.img_resultado);
      btn_voltar_quiz = findViewById(R.id.btn_voltar);
      view_txt_resultado = findViewById(R.id.txt_resultado);
      view_txt_descricao = findViewById(R.id.txt_descricao);
      view_imagem = findViewById(R.id.img_resultado);
      view_txt_acertos = findViewById(R.id.txt_resultado_quiz);
      Intent dadosIntent = getIntent();
      int acertos = dadosIntent.getIntExtra(quiz_Activity.EXTRA_ACERTO, 0);
      view_txt_acertos.setText(acertos + "/6");
      if (acertos > 4)
         criarPagina(0);
      else if (acertos > 2)
         criarPagina(1);
      else if (acertos > 0)
         criarPagina(2);
      else
         criarPagina(3);

      btn_voltar_quiz.setOnClickListener(view -> {
         Intent intent = new Intent(resultado_Activity.this, quiz_Activity.class);
         startActivity(intent);
      });
   }

   public void criarPagina(int index) {
      int[] img = { R.drawable.acertos6, R.drawable.acertos4, R.drawable.acertos2, R.drawable.acertos0};
      String[] txt_resultado = getResources().getStringArray(R.array.txt_resultado_quiz);
      String[] txt_descricao = getResources().getStringArray(R.array.txt_descricao_quiz);
      view_imagem.setImageResource(img[index]);
      view_txt_resultado.setText(txt_resultado[index]);
      view_txt_descricao.setText(txt_descricao[index]);
   }

   public void tela() {
      String state = Environment.getExternalStorageState();
      if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
         PackageManager.PERMISSION_GRANTED) {
         ActivityCompat.requestPermissions(this, new String[] {
               Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
      }
      else {
         if (Environment.MEDIA_MOUNTED.equals(state)) {
            Date now = new Date();
            android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
            try {
               String dPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
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
               Toast.makeText(this, "Captura de tela salva", Toast.LENGTH_SHORT).show();
            } catch (Throwable e) {
               Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
               e.printStackTrace();
            }
         }
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu, menu);
      menu.add(Menu.NONE, 8, Menu.NONE, "Capturar Tela");
      return true;
   }

   public boolean onOptionsItemSelected(MenuItem item) {
      super.onOptionsItemSelected(item);
      MenuClass menu = new MenuClass();
      Intent intent = menu.selecionarMenu(getApplicationContext(), item);
      if (intent != null)
         startActivity(intent);
      else
         tela();
      return super.onOptionsItemSelected(item);
   }
}