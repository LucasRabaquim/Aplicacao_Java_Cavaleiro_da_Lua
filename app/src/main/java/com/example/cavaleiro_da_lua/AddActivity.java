package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText edit_nome,edit_email, edit_mensagem;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edit_nome = findViewById(R.id.review_nome);
        edit_email = findViewById(R.id.review_email);
        edit_mensagem = findViewById(R.id.review_mensagem);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
            myDB.insertReview(edit_nome.getText().toString().trim(),
                    edit_email.getText().toString().trim(),
                    edit_mensagem.getText().toString().trim());
            Intent intent = new Intent(getApplicationContext(),Review_Activity.class);
            startActivity(intent);
        });

    }
}