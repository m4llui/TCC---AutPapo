package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Tela_Home extends AppCompatActivity {

    ImageView imfase1,imfase2,imfase3, imFasesBloqueadas, imDesempenho, imConfig, imHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Adicionar logo na Toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.logo_colorida);

        imfase1 = findViewById(R.id.imgBotao1);
        imfase2 = findViewById(R.id.imgBotao2);
        imfase3 = findViewById(R.id.imgBotao3);
        imHome = findViewById(R.id.imgHome);
        imDesempenho = findViewById(R.id.imgDesempenho);
        imConfig = findViewById(R.id.imgConfig);

        imfase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_Fase1.class);
                startActivity(intent);
            }
        });

        imfase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_fase2.class);
                startActivity(intent);
            }
        });

        imfase3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_Atv_1_fase3.class);
                startActivity(intent);
            }
        });


        imHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Tela_Home.this, "Home", Toast.LENGTH_SHORT).show();
                //Colocar Intent para tela Home
            }
        });

        imDesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Tela_Home.this, "Desempenho", Toast.LENGTH_SHORT).show();
                //Colocar Intent para tela Desempenho
            }
        });

        imConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Tela_Home.this, "Configurações", Toast.LENGTH_SHORT).show();
                //  Colocar Intent para tela Configurações
            }
        });
    }
}
