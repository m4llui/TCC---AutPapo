package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tela_Home extends AppCompatActivity {

    ImageView imfase1,imfase2,imfase3, imDesempenho, imConfig, imHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);

        // Recuperar o codUsuario passado pelo login
        int codUsuario = getIntent().getIntExtra("codUsuario", -1);

        // Recuperar o código da criança
        Intent intent = getIntent();
        int codCrianca = intent.getIntExtra("codCrianca", -1);



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
                intent.putExtra("codCrianca",codCrianca);
                startActivity(intent);
            }
        });

        imfase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_fase2.class);
                intent.putExtra("codCrianca",codCrianca);
                startActivity(intent);
            }
        });

        imfase3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_fase3.class);
                intent.putExtra("codCrianca",codCrianca);
                startActivity(intent);
            }
        });


        imHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_Home.class);
                startActivity(intent);
            }
        });

        imDesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_Desempenho.class);
                startActivity(intent);
            }
        });

        imConfig = findViewById(R.id.imgConfig);
        imConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Home.this, Tela_Config.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}
