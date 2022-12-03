package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText edit_nome, edit_email, edit_mensagem;
    Button btn_atualizar, btn_deletar;

    String _id, _nome, _email, _mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit_nome = findViewById(R.id.title_input2);
        edit_email = findViewById(R.id.author_input2);
        edit_mensagem = findViewById(R.id.pages_input2);
        btn_atualizar = findViewById(R.id.update_button);
        btn_deletar = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(_nome);
        }

        btn_atualizar.setOnClickListener(view -> {
            //And only then we call this
            DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
            _nome = edit_nome.getText().toString().trim();
            _email = edit_email.getText().toString().trim();
            _mensagem = edit_mensagem.getText().toString().trim();
            myDB.updateReview(_id, _nome, _email, _mensagem);
        });
        btn_deletar.setOnClickListener(view -> confirmar());
    }

    public void getAndSetIntentData(){
            _id = getIntent().getStringExtra("id");
            _nome = getIntent().getStringExtra("nome");
            _email = getIntent().getStringExtra("email");
            _mensagem = getIntent().getStringExtra("mensagem");

            //Setting Intent Data
            edit_nome.setText(_nome);
            edit_email.setText(_email);
            edit_mensagem.setText(_mensagem);
       /* }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void confirmar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + _nome + " ?");
        builder.setMessage("Are you sure you want to delete " + _nome + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
            myDB.deleteOneRow(_id);
            finish();
        });
        builder.setNegativeButton("Não", (dialogInterface, i) ->
                Toast.makeText(this, "Ação Cancelada.", Toast.LENGTH_SHORT).show()
        );
        builder.create().show();
    }
}
