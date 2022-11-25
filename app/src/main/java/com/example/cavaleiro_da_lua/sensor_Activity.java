package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class sensor_Activity extends AppCompatActivity implements SensorEventListener{

    // Declarando as views do design
    TextView view_texto, txt_switch;
    ImageView view_imagem;
    SwitchCompat btn_switch;


    // Variaveis para mexer nos sensores
    SensorManager sensorManager;
    Sensor sensor;

    // Variavel global com a inclinação do celular
    Float inclinacao = 0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        view_texto = findViewById(R.id.txt_sensor);
        view_imagem = findViewById(R.id.img_sensor);
        btn_switch = findViewById(R.id.btn_switch);
        txt_switch = findViewById(R.id.txt_switch);

        // Escolhe o sensor do dispositivo. Se tiver giroscopio usa ele, se não usa o acelerometro.
        // Se não tiver nenhum dos dois, não vai usar sensor.

        // O parâmetro "checado" é para verificar se o botão switch está checado ou não.
        btn_switch.setOnCheckedChangeListener((compoundButton, checado) -> {
            definirSensor();
            // Se tiver sensor
            if(sensor != null){
                // Reseta a inclinação toda ves que é clicado.
                inclinacao = 0f;
                String texto = getResources().getString((checado) ?
                        R.string.txt_balanca_on : R.string.txt_balanca_off);
                txt_switch.setText(texto);
            }
            // Se não tiver um sensor mostra um erro na tela.
            else {
                erro();
            }
        });


    }

    public void definirSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
        else if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        else {
            sensor = null;
        }
        sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void erro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msgbox_texto).setTitle(R.string.msgbox_titulo);
        builder.create().show();
        btn_switch.setChecked(false);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
            Float[] angulos;
            if(sensor == sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)) {
                inclinacao += event.values[2];
                angulos = new Float[]{-6f, -4f, 2f, 4f, 7f};
            }
            else{
                inclinacao = event.values[0];
                angulos = new Float[]{-5f, -3f, 1f, 3f, 7f};
            }
            int index_mensagem;
            if(inclinacao < angulos[0])
                index_mensagem = 0;
            else if(inclinacao < angulos[1]) // Mesma coisa de, por ex: "se está entre -16f e -8f"
                index_mensagem = 1;
            else if(inclinacao < angulos[2])
                index_mensagem = 2;
            else if(inclinacao < angulos[3])
                index_mensagem = 3;
            else
                index_mensagem = 4;
            mudarInformacoes(index_mensagem);
            // Por favor Ler linha 137
    }

    public void mudarInformacoes(int index){
        int[] imagem = {
            R.drawable.mr_knight,R.drawable.steven_grant,R.drawable.sensor_neutro,
            R.drawable.mark_spector,R.drawable.moon_knight
        };
        String[] texto = getResources().getStringArray(R.array.txt_sensor);
        view_imagem.setImageResource(imagem[index]);
        view_texto.setText(texto[index]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch(item.getItemId()){
            case R.id.item1:
                intent = new Intent(getApplicationContext(), curiosidades_Activity.class);
                break;
            case R.id.item2:
                intent = new Intent(getApplicationContext(), mapa_activity.class);
                break;
            case R.id.item3:
                intent = new Intent(getApplicationContext(), diferencas_Activity.class);
                break;
            case R.id.item4:
                intent = new Intent(getApplicationContext(), sensor_Activity.class);
                break;
            case R.id.item5:
                intent = new Intent(getApplicationContext(), quiz_Activity.class);
                break;
            case R.id.item6:
                intent = new Intent(getApplicationContext(),tema_Activity.class);
                break;
	    case R.id.item7:
                intent = new Intent(getApplicationContext(),inicio_Activity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

/* Forma alternativa de fazer a checagem do valor da ângulação no método onSensorChanged():

          // Método onSensorChanged()
            Float[] angulos;
            if(sensor == sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)) {
                inclinacao += event.values[2];
            }
            else{
                inclinacao = event.values[0];
            }
            int index_mensagem = angulacao(inclinacao);
            mudarInformacoes(index_mensagem);
            *
            *
         // Método da angulacao
            int angulacao(float inclinacao){
            Float[] angulos;
            if(sensor == sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)) {
                angulos = new Float[]{-16f, -8f, 8f, 16f, 9999f};
            }
            else{
                angulos = new Float[]{-6f, -3f, 3f, 6f, 9999f};
            }
            for(int i = 0; i < angulos.length; i++){
                if(inclinacao < angulo[i]) {
                    return i;
                }
            }
*/