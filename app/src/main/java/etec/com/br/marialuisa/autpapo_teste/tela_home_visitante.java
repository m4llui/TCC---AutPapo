package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class tela_home_visitante extends AppCompatActivity {

    ImageView imgConfig, imgDesemp, imgHome, btFase1, btFase2, btFase3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home_visitante);

        imgDesemp = findViewById(R.id.imgDesempenho);
        imgConfig = findViewById(R.id.imgConfig);
        imgHome = findViewById(R.id.imgDesempenho);
        btFase1 = findViewById(R.id.imgBotao1);
        btFase2 = findViewById(R.id.imgBotao2);
        btFase3 = findViewById(R.id.imgBotao3);

        imgDesemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(tela_home_visitante.this, "Desempenho bloqueado, faça o login!", Toast.LENGTH_SHORT).show();
            }
        });

        imgConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent para tela config
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent para home
            }
        });
         btFase1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(tela_home_visitante.this, Tela_Fase1.class);
                 startActivity(intent);
             }
         });

         btFase2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(tela_home_visitante.this, Tela_fase2.class);
                 startActivity(intent);
             }
         });

         btFase3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(tela_home_visitante.this, Tela_fase3.class);
                 startActivity(intent);
             }
         });
    }

    @Override
    public void onBackPressed() {

    }
}