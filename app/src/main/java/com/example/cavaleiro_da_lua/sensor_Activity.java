package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.IOException;
import java.util.List;

public class sensor_Activity extends AppCompatActivity implements SensorEventListener{

    // As duas linhas abaixo: variaveis necessárias para gerenciar o sensor e definir qual sensor é respectivamente.
    SensorManager sensorManager;
    Sensor sensor;

    //Até ToggleButton: pega o id das views na tela
    TextView view_texto, txt_switch;
    ImageView view_imagem;
    Switch btn_switch;

    // Boleano para verificar se o dispositivo tem o sensor.
    boolean sensor_existe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Permite o uso dos serviços de sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Pega o id das views
        view_texto = findViewById(R.id.txt_sensor);
        view_imagem = findViewById(R.id.img_sensor);
        btn_switch = findViewById(R.id.btn_switch);
        txt_switch = findViewById(R.id.txt_switch);

        // Define o tipo do sensor.
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        else if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        else
            sensor = null;
        // "Liga o sensor" (faz com que o sensor reaja as mudanças na rotação e passe essas informações.)
        sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        btn_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sensor != null){
                    inclinacao = 0f;
                    sensor_ligado = btn_switch.isChecked();
                    String texto = (sensor_ligado) ? "Parar a balança" : "Iniciar a balança";
                    txt_switch.setText(texto);
                }
                else
                    erro();
            }
        });

    }

    private void erro() {
        // Cria um alerta na tela
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msgbox_texto).setTitle(R.string.msgbox_titulo);
        builder.create().show();
        btn_switch.setChecked(false);
    }

    // As 2 linhas abaixo: Array para facilitar a apresentação das informações.
    int[] imagem = {R.drawable.mr_knight,R.drawable.steven_grant,R.drawable.sensor_neutro,R.drawable.mark_spector,R.drawable.moon_knight};
    String[] texto = {"Mr. Knight","Steven","Gire o celular","Mark","Moon Knight"};

    // Index das informações dos arrays imagem e texto.
    int index_mensagem = 0;

    // Inclinação do celular
    Float inclinacao = 0f;

    // Na pratica só define se a activity vai usar ou não as informações do sensor
    boolean sensor_ligado;

    // É chamada com a variação do sensor
    @Override
    public void onSensorChanged(SensorEvent event){
        if(sensor_ligado) {
            Float[] angulos;
            // O Giroscópio só mede a variação no angulo, e não a angulação de forma perpendicular ao chão, por isso a soma
            if(sensor == sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)) {
                inclinacao += event.values[2];
                angulos = new Float[]{24f, 16f, 8f, -8f, -16f};
            }
            else{
                inclinacao = event.values[0];
                angulos = new Float[]{10f,6f,3f,-3f,-6f};
            }

            /* Reduz a quantidade de if/else por ver se está entre o valor comparado[i] e o proximo
               valor (pois a inclinação será testada denovo
               para ver se está entre o próximo e o depois dele).*/
            for(int i = 0; i < 5; i++)
                /*Se for maior que o atual vai pegar a mensagem correspondente a esse angulo,
                * se for menor que o próximo valor (quando comparar denovo), vai manter o valor,
                * ou seja pega o valor que está entre os dois.*/
                if(inclinacao < angulos[i]) index_mensagem = i;
            mudarInformacoes(index_mensagem);
        }
    }

    public void mudarInformacoes(int index){
        view_imagem.setImageResource(imagem[index]);
        view_texto.setText(texto[index]);
    }

    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}