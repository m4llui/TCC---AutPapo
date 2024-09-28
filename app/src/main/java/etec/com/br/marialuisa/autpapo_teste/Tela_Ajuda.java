package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tela_Ajuda extends AppCompatActivity {

    ImageView btHome, btDesemp, btConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ajuda);

        btHome = findViewById(R.id.imgHomeA);
        btDesemp = findViewById(R.id.imgDesempenhoA);
        btConfig = findViewById(R.id.imgConfigA);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para home
                Intent abrirHome = new Intent(Tela_Ajuda.this,Tela_Home.class);
                startActivity(abrirHome);
            }
        });

        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(Tela_Ajuda.this,Tela_Config.class);
                startActivity(abrirConfig);
            }
        });

        btDesemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para Desemp
                Intent abrirDesemp = new Intent(Tela_Ajuda.this,Tela_Desempenho.class);
                startActivity(abrirDesemp);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize o menu do aplicativo!", Toast.LENGTH_SHORT).show();

    }
}