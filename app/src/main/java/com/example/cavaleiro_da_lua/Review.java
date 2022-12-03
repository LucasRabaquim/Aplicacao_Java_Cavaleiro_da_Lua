package com.example.cavaleiro_da_lua;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Review extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_ajuda;
    Button btn_criar;
    ImageView empty_imageview;
    TextView no_data;

    DatabaseHelper myDB;
    ArrayList<String> _id, _nome, _email, _mensagem;
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
                Intent intent = new Intent(Review.this, AddActivity.class);
                startActivity(intent);
        });

        myDB = new DatabaseHelper(Review.this);
        _id = new ArrayList<>();
        _nome = new ArrayList<>();
        _email = new ArrayList<>();
        _mensagem = new ArrayList<>();

        criarArraydeDados();
        customAdapter = new CustomAdapter(Review.this,this, _id, _nome, _email);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Review.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void criarArraydeDados(){
        Cursor cursor = myDB.selectReview();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
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
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item1){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            DatabaseHelper myDB = new DatabaseHelper(Review.this);
            myDB.deleteAllData();
            Intent intent = new Intent(Review.this, Review.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });
        builder.create().show();
    }
}
