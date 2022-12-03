package com.example.cavaleiro_da_lua;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Review_Activity extends AppCompatActivity {

   RecyclerView recyclerView;
   Button btn_criar;
   ImageView empty_imageview;
   TextView no_data;

   DatabaseHelper myDB;
   ArrayList <String> _id, _nome, _email, _mensagem;
   CustomAdapter customAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_review);

      recyclerView = findViewById(R.id.recyclerView);
      btn_criar = findViewById(R.id.btn_criar_review);
      empty_imageview = findViewById(R.id.empty_imageview);
      no_data = findViewById(R.id.no_data);
      btn_criar.setOnClickListener(view -> {
         Intent intent = new Intent(Review_Activity.this, AddActivity.class);
         startActivity(intent);
      });

      myDB = new DatabaseHelper(Review_Activity.this);
      _id = new ArrayList < > ();
      _nome = new ArrayList < > ();
      _email = new ArrayList < > ();
      _mensagem = new ArrayList < > ();

      criarArraydeDados();
      customAdapter = new CustomAdapter(Review_Activity.this, this, _id, _nome, _email, _mensagem);
      recyclerView.setAdapter(customAdapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(Review_Activity.this));
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == 1)
         recreate();
   }

   public void criarArraydeDados() {
      Cursor cursor = myDB.selectReview();
      if (cursor.getCount() == 0) {
         empty_imageview.setVisibility(View.VISIBLE);
         no_data.setVisibility(View.VISIBLE);
      } else {
         while (cursor.moveToNext()) {
            _id.add(cursor.getString(0));
            _nome.add(cursor.getString(1));
            _email.add(cursor.getString(2));
            _mensagem.add(cursor.getString(3));
         }
         empty_imageview.setVisibility(View.GONE);
         no_data.setVisibility(View.GONE);
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu_review, menu);
      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.item_Deletar:
         perguntar(true);
         break;
      case R.id.item_Ajuda:
         ajuda();
         break;
      case R.id.item_voltar:
         finish();
         break;
      case R.id.item_backup:
         perguntar(false);
         break;
      default:
         return super.onOptionsItemSelected(item);
      }
      return super.onOptionsItemSelected(item);
   }

   public void perguntar(boolean deletar) {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      String titulo, mensagem, cancelamento;
      if (deletar) {
         titulo = getResources().getString(R.string.item_apagar);
         mensagem = getResources().getString(R.string.dialogo_apagar_texto);
         cancelamento = getResources().getString(R.string.deletar_cancelado);
      } else {
         titulo = getResources().getString(R.string.item_backup);
         mensagem = getResources().getString(R.string.dialogo_backup_texto);
         cancelamento = getResources().getString(R.string.backup_cancelado);
      }
      builder.setTitle(titulo);
      builder.setMessage(mensagem);
      builder.setPositiveButton(R.string.sim, (dialogInterface, i) -> {
         if (deletar)
            apagarDados();
         else
            backup();
      });
      builder.setNegativeButton(R.string.nao, (dialogInterface, i) ->
         Toast.makeText(this, cancelamento, Toast.LENGTH_SHORT).show());
      builder.create().show();
   }
   public void apagarDados() {
      DatabaseHelper myDB = new DatabaseHelper(Review_Activity.this);
      myDB.deleteAllData();
      Intent intent = new Intent(Review_Activity.this, Review_Activity.class);
      startActivity(intent);
      finish();
   }
   public void ajuda() {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle(R.string.dialogo_ajuda_titulo);
      builder.setMessage(R.string.dialogo_ajuda_texto);
      builder.create().show();
   }
   public void backup() {
      String state = Environment.getExternalStorageState();
      if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
         PackageManager.PERMISSION_GRANTED) {
         ActivityCompat.requestPermissions(this, new String[] {
               Manifest.permission.WRITE_EXTERNAL_STORAGE
            },
            1);

      }
      else {
         if (Environment.MEDIA_MOUNTED.equals(state)) {

            try {
               ArrayList < reviewClass > lista = myDB.selectListaReview();
               JSONArray jLista = new JSONArray();
               for (reviewClass c: lista)
                  jLista.put(c.getJSONObject());
               File dir = Environment.getExternalStorageDirectory();
               if (!dir.exists())
                  dir.mkdirs();
               File subDir = new File(dir, String.valueOf(R.string.app_name));
               if (!subDir.exists())
                  subDir.mkdirs();
               String name = null;
               if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                  name = "backup_" + LocalDateTime.now() + ".json";
               File f = new File(subDir, name);

               FileWriter fileWriter = new FileWriter(f);
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               bufferedWriter.write(jLista.toString());
               bufferedWriter.close();
               Toast.makeText(getApplicationContext(), "Backup salvo em: " + f.getAbsolutePath(),Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
               e.printStackTrace();
               Toast.makeText(getApplicationContext(), e.getMessage(),
                  Toast.LENGTH_SHORT).show();
            }
         }
      }
   }
}