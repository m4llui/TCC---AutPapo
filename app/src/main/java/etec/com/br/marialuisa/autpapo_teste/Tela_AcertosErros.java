package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_AcertosErros extends AppCompatActivity {

    TextView txNomeIdade, txNivel, txAcertos, txErros;
    ImageView btDesemp, btConfig, btHome, btiniciarAtv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_acertos_erros);

        txNomeIdade = findViewById(R.id.txtNomeIdade);
        txNivel = findViewById(R.id.txtNivel);
        txAcertos = findViewById(R.id.txtAcertos);
        btiniciarAtv = findViewById(R.id.btn_iniciar_atv);
        txErros = findViewById(R.id.txtErros);
        btDesemp = findViewById(R.id.imgDesempenhoAE);
        btConfig = findViewById(R.id.imgConfigAE);
        btHome = findViewById(R.id.imgHomeAE);

        // Recuperar o código da criança
        Intent intent = getIntent();
        int codCrianca = intent.getIntExtra("codCrianca", -1);


        //MOSTRANDO CONSULTAS
        CadastroCrianca cadastroCrianca = new CadastroCrianca(Tela_AcertosErros.this);

        String[] info = cadastroCrianca.ConsultaPorCodigo(codCrianca);

        if (info != null) {
            txNomeIdade.setText( info[0] +"," + info[1] +" anos ");
            txNivel.setText("NÍVEL " + info[2]);
        } else {
            Toast.makeText(this, "Criança não encontrada!", Toast.LENGTH_SHORT).show();
        }


        btiniciarAtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirHome = new Intent(Tela_AcertosErros.this,Tela_Home.class);
                abrirHome.putExtra("codCrianca",codCrianca);
                startActivity(abrirHome);
            }
        });
        btDesemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirDesemp = new Intent(Tela_AcertosErros.this,Tela_Desempenho.class);
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