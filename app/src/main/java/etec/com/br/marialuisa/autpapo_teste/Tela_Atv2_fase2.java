package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv2_fase2 extends AppCompatActivity {

    private ImageView btn_Let_L, btn_Let_L_Inc, btn_Let_I, btn_Let_I_Inc,
            btn_Let_M, btn_Let_M_Inc, btn_Let_G, btn_Let_G_Certo, btVoltar2, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase2);

        // Vincular as views do XML com o código
        btn_Let_L = findViewById(R.id.btn_Let_L);
        btn_Let_L_Inc = findViewById(R.id.btn_Let_L_errado);
        btn_Let_I = findViewById(R.id.btn_Let_I);
        btn_Let_I_Inc = findViewById(R.id.btn_Let_I_errado);
        btn_Let_M = findViewById(R.id.btn_Let_M);
        btn_Let_M_Inc = findViewById(R.id.btn_Let_M_errado);
        btn_Let_G = findViewById(R.id.btn_Let_G);
        btn_Let_G_Certo = findViewById(R.id.btn_Let_G_certo);
        btVoltar2 = findViewById(R.id.btnVoltarAtv2Fase2);
        btBalao = findViewById(R.id.ImageBalao_Atv2_fase2);

        botoesInativados();
        setOnClickListeners();
    }

    // Método para esconder os botões de respostas até que o usuário clique em uma letra
    private void botoesInativados() {
        btn_Let_L_Inc.setVisibility(View.INVISIBLE);
        btn_Let_I_Inc.setVisibility(View.INVISIBLE);
        btn_Let_M_Inc.setVisibility(View.INVISIBLE);
        btn_Let_G_Certo.setVisibility(View.INVISIBLE);

        btn_Let_L_Inc.setEnabled(false);
        btn_Let_I_Inc.setEnabled(false);
        btn_Let_M_Inc.setEnabled(false);
        btn_Let_G_Certo.setEnabled(false);
    }

    // Configurar os ouvintes de clique para os botões de letras
    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                // Verificar qual letra foi clicada e exibir a resposta correspondente
                if (id == R.id.btn_Let_L) {
                    btn_Let_L_Inc.setVisibility(View.VISIBLE);
                    btn_Let_L_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_I) {
                    btn_Let_I_Inc.setVisibility(View.VISIBLE);
                    btn_Let_I_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_M) {
                    btn_Let_M_Inc.setVisibility(View.VISIBLE);
                    btn_Let_M_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_G) {
                    btn_Let_G_Certo.setVisibility(View.VISIBLE);
                    btn_Let_G_Certo.setEnabled(true);
                }

                // Delay antes de mudar de tela
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv2_fase2.this, Tela_Atv3_fase2.class));
                        finish();
                    }
                }, 2000); // 2 segundos de atraso
            }
        };

        // Associar os ouvintes aos botões
        btn_Let_L.setOnClickListener(listener);
        btn_Let_I.setOnClickListener(listener);
        btn_Let_M.setOnClickListener(listener);
        btn_Let_G.setOnClickListener(listener);
    }
}
