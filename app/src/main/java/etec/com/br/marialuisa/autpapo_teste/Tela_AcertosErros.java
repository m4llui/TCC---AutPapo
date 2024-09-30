package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Tela_AcertosErros extends AppCompatActivity {

    TextView txNomeIdade, txNivel, txAcertos, txErros;
    ImageView btDesemp, btConfig, btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_acertos_erros);

        txNomeIdade = findViewById(R.id.txtNomeIdade);
        txNivel = findViewById(R.id.txtNivel);
        txAcertos = findViewById(R.id.txtAcertos);
        txErros = findViewById(R.id.txtErros);
        btDesemp = findViewById(R.id.imgDesempenhoAE);
        btConfig = findViewById(R.id.imgConfigAE);
        btHome = findViewById(R.id.imgHomeAE);

        btDesemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirDesemp = new Intent(Tela_AcertosErros.this,Tela_Home.class);
                startActivity(abrirDesemp);
            }
        });

        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirConfig = new Intent(Tela_AcertosErros.this, Tela_Config.class);
                startActivity(abrirConfig);

            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirHome = new Intent(Tela_AcertosErros.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}