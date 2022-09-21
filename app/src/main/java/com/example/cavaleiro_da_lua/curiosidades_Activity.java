package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class curiosidades_Activity extends AppCompatActivity {

    ImageView view_imagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curiosidades);
        view_imagem = findViewById(R.id.img_Curio);
        int luacheia = R.drawable.cheia;
        view_imagem.setImageResource(luacheia);
    }

}