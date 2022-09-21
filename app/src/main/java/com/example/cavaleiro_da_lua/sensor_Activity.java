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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.List;

public class sensor_Activity extends AppCompatActivity implements SensorEventListener{

    // As duas linhas abaixo: variaveis necessárias para gerenciar o sensor e definir qual sensor é respectivamente.
    SensorManager sensorManager;
    Sensor giroscopio;
    Sensor acelerometro;
    Sensor sensor;


    //Até ToggleButton: pega o id das views na tela
    TextView view_texto;
    ImageView view_imagem;
    ToggleButton toggleButton;

    // Boleano para verificar se o dispositivo tem o sensor.
    boolean sensor_existe;
    boolean giroscopio_existe = false;
    boolean acelerometro_existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Permite o uso dos serviços de sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Pega o id das views
        view_texto = findViewById(R.id.txt_sensor);
        view_imagem = findViewById(R.id.img_sensor);
        toggleButton = findViewById(R.id.tg_btn_sensor);

        // Verifica se o dispositivo tem sensor de giroscópio
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s : deviceSensors) {
            Log.d("Sensors: ", s.getName());
            if(s.getName().equals("LIS2DS Gyroscope")) giroscopio_existe = true;
            if(s.getName().equals("LIS2DS Accelerometer")) acelerometro_existe = true;
        }
        // Define o tipo do sensor.
       /* if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);*/
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        else
            sensor = null;
        // "Liga o sensor" (faz com que o sensor reaja as mudanças na rotação e passe essas informações.)
        sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    // As 2 linhas abaixo: Array para facilitar a apresentação das informações.
    int[] imagem = {R.drawable.mr_knight,R.drawable.steven_grant,R.drawable.sensor_neutro,R.drawable.mark_spector,R.drawable.moon_knight};
    String[] texto = {"Mr. Knight","Steven","Gire o celular","Mark","Moon Knight"};

    // Os valores de angulação para cada informação ser mostrada
    Float[] angulos = {10f,6f,3f,-3f,-6f};

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
            // O Giroscópio só mede a variação no angulo, e não a angulação de forma perpendicular ao chão, por isso a soma
            /*if(giroscopio_existe)
                inclinacao += event.values[2];
            else*/
                inclinacao = event.values[0];
            /* Reduz a quantidade de if/else por ver se está entre o valor comparado[i] e o proximo valor (pois a inclinação será testada denovo
               para ver se está entre o próximo e o depois dele).*/
            for(int i = 0; i < 5; i++)
                /*Se for maior que o atual vai pegar a mensagem correspondente a esse angulo,
                * se for menor que o próximo valor (quando comparar denovo), vai manter o valor,
                * ou seja pega o valor que está entre os dois.*/
                if(inclinacao < angulos[i]) index_mensagem = i;
            mudarInformacoes(index_mensagem,inclinacao);
        }
    }

    public void mudarInformacoes(int index,float f){
        view_imagem.setImageResource(imagem[index]);
        view_texto.setText(texto[index]);
    }

    public void tgClique(View view){
        if(sensor != null){
            inclinacao = 0f;
            sensor_ligado = toggleButton.isChecked();
        }
        else{
            // Cria um alerta na tela
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.msgbox_texto).setTitle(R.string.msgbox_titulo);
            builder.create().show();
            toggleButton.setChecked(false);
        }
    }

    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}