package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class diferencas_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diferencas);

        Button btn_hq1 = findViewById(R.id.btn_hq1);
        Button btn_hq2 = findViewById(R.id.btn_hq2);
        Button btn_hq3 = findViewById(R.id.btn_hq3);
        Button btn_hq4 = findViewById(R.id.btn_hq4);

        btn_hq1.setOnClickListener(view ->
            comprar("https://produto.mercadolivre.com.br/MLB-2787331921-gibi-cavaleiro-da-lua-volume-1-re-_JM#position=18&search_layout=stack&type=item&tracking_id=93fe0eff-95d5-4ff5-a4cf-ba9dc25f683c")
        );
        btn_hq2.setOnClickListener(view ->
            comprar("https://www.livrospararevenda.com.br/produtos/colecao-historica-paladinos-marvel-vol-3-p14/?pf=gs")
        );
        btn_hq3.setOnClickListener(view ->
            comprar("https://www.amazon.com.br/Paladinos-Marvel-3-Cole%C3%A7%C3%A3o-Hist%C3%B3rica/dp/8542607856/ref=asc_df_8542607856/?tag=googleshopp00-20&linkCode=df0&hvadid=379738409646&hvpos=&hvnetw=g&hvrand=13446028410313321392&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1001773&hvtargid=pla-810874844149&psc=1")
        );
        btn_hq4.setOnClickListener(view ->
            comprar("https://www.martinsfontespaulista.com.br/moon-knight-by-bendis---maleev-997725/p?idsku=997725&srsltid=AR5OiO1c2GQFWVpdYHKVznDwDSJ6hSQycb1N-Q5xU5F9xYhG8ryAtKgurhs")
        );
    }
    // Botões com o link para compra do quadrinho utilizando intent implicita
    private void comprar(String link_site) {
        Uri site = Uri.parse(link_site);
        Intent intent = new Intent(Intent.ACTION_VIEW, site);
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_navegador)));
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
                intent = new Intent(getApplicationContext(),inicio_Activity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
