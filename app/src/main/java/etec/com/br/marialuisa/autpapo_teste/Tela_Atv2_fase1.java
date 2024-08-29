package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv2_fase1 extends AppCompatActivity {

    private ImageView btn_Let_A, btn_Let_A_Inc, btn_Let_B, btn_Let_B_Inc, btn_Let_E, btn_Let_E_Certo,
            btn_Let_Y, btn_Let_Y_Inc, btVoltar3, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase1);

        // Inicializando os botões com seus respectivos IDs
        btn_Let_A = findViewById(R.id.btn_Let_A_2);
        btn_Let_A_Inc = findViewById(R.id.btn_Let_A_Inc);
        btn_Let_B = findViewById(R.id.btn_Let_B2);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_B_Inc2);
        btn_Let_E = findViewById(R.id.btn_Let_E_2);
        btn_Let_E_Certo = findViewById(R.id.btn_Let_E_Certo);
        btn_Let_Y = findViewById(R.id.btn_Let_Y);
        btn_Let_Y_Inc = findViewById(R.id.btn_Let_Y_Inc);
        btVoltar3 = findViewById(R.id.btnVoltarAtv6Fase3);
        btEnunciado = findViewById(R.id.ImageBoneco_fase);
        btBalao = findViewById(R.id.ImageBalao2);

        // Desativando e ocultando os botões de erro e acerto inicialmente
        botoesInativados();

        // Definindo os listeners para os botões
        setOnClickListeners();
    }

    // Método para desativar e ocultar os botões de erro e acerto
    private void botoesInativados() {
        btn_Let_A_Inc.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_E_Certo.setVisibility(View.INVISIBLE);
        btn_Let_Y_Inc.setVisibility(View.INVISIBLE);

        btn_Let_A_Inc.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_E_Certo.setEnabled(false);
        btn_Let_Y_Inc.setEnabled(false);
    }

    // Método para definir os listeners dos botões
    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_Let_A_2) {
                    btn_Let_A_Inc.setVisibility(View.VISIBLE);
                    btn_Let_A_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_B2) {
                    btn_Let_B_Inc.setVisibility(View.VISIBLE);
                    btn_Let_B_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_E_2) {
                    btn_Let_E_Certo.setVisibility(View.VISIBLE);
                    btn_Let_E_Certo.setEnabled(true);

                } else if (id == R.id.btn_Let_Y) {
                    btn_Let_Y_Inc.setVisibility(View.VISIBLE);
                    btn_Let_Y_Inc.setEnabled(true);
                }

                // Aguarda 2 segundos antes de navegar para a próxima atividade
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv2_fase1.this, Tela_Atv3_fase1.class));
                        finish();
                    }
                }, 2000);
            }
        };

        // Definindo os listeners para cada botão
        btn_Let_A.setOnClickListener(listener);
        btn_Let_B.setOnClickListener(listener);
        btn_Let_E.setOnClickListener(listener);
        btn_Let_Y.setOnClickListener(listener);
    }
}
