package com.example.cavaleiro_da_lua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class diferencas_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diferencas);
    }

    // Botões com o link para compra do quadrinho utilizando intent implicita
    public void btn_hq1(View view) {
        Uri site = Uri.parse("https://produto.mercadolivre.com.br/MLB-2787331921-gibi-cavaleiro-da-lua-volume-1-re-_JM#position=18&search_layout=stack&type=item&tracking_id=93fe0eff-95d5-4ff5-a4cf-ba9dc25f683c");
        Intent intent = new Intent(Intent.ACTION_VIEW, site);
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_navegador)));
    }

    public void btn_hq2(View view) {
        Uri site = Uri.parse("https://www.livrospararevenda.com.br/produtos/colecao-historica-paladinos-marvel-vol-3-p14/?pf=gs");
        Intent intent = new Intent(Intent.ACTION_VIEW, site);
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_navegador)));
    }

    public void btn_hq3(View view) {
        Uri site = Uri.parse("https://www.amazon.com.br/Paladinos-Marvel-3-Cole%C3%A7%C3%A3o-Hist%C3%B3rica/dp/8542607856/ref=asc_df_8542607856/?tag=googleshopp00-20&linkCode=df0&hvadid=379738409646&hvpos=&hvnetw=g&hvrand=13446028410313321392&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1001773&hvtargid=pla-810874844149&psc=1");
        Intent intent = new Intent(Intent.ACTION_VIEW, site);
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_navegador)));
    }

    public void btn_hq4(View view) {
        Uri site = Uri.parse("https://www.martinsfontespaulista.com.br/moon-knight-by-bendis---maleev-997725/p?idsku=997725&srsltid=AR5OiO1c2GQFWVpdYHKVznDwDSJ6hSQycb1N-Q5xU5F9xYhG8ryAtKgurhs");
        Intent intent = new Intent(Intent.ACTION_VIEW, site);
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_navegador)));
    }
    public void voltarMenu(View view){
        Intent intent = new Intent(this, menu_Activity.class);
        startActivity(intent);
    }
}
