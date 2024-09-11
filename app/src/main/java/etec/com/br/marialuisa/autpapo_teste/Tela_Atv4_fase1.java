package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv4_fase1 extends AppCompatActivity {

    private ImageView btn_Let_P, btn_Let_P_Inc, btn_Let_E, btn_Let_E_Inc, btn_Let_O, btn_Let_O_Certo,
            btn_Let_G, btn_Let_G_Inc, btVoltar4, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv4_fase1);

        btn_Let_P = findViewById(R.id.btn_P2);
        btn_Let_P_Inc = findViewById(R.id.btn_P_Inc);
        btn_Let_E = findViewById(R.id.btn_E2);
        btn_Let_E_Inc = findViewById(R.id.btn_E_Inc);
        btn_Let_O = findViewById(R.id.btn_O2);
        btn_Let_O_Certo = findViewById(R.id.btn_O_Correta);
        btn_Let_G = findViewById(R.id.btn_G2);
        btn_Let_G_Inc = findViewById(R.id.btn_G_Inc);
        btVoltar4 = findViewById(R.id.btnVoltarAtv4Fase1);
        btEnunciado = findViewById(R.id.ImageEnunciado_fase1_atv4);
        btBalao = findViewById(R.id.ImageBalao3);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_P_Inc.setVisibility(View.INVISIBLE);
        btn_Let_E_Inc.setVisibility(View.INVISIBLE);
        btn_Let_O_Certo.setVisibility(View.INVISIBLE);
        btn_Let_G_Inc.setVisibility(View.INVISIBLE);

        btn_Let_P_Inc.setEnabled(false);
        btn_Let_E_Inc.setEnabled(false);
        btn_Let_O_Certo.setEnabled(false);
        btn_Let_G_Inc.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_P2) {
                    btn_Let_P_Inc.setVisibility(View.VISIBLE);
                    btn_Let_P_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto

                } else if (id == R.id.btn_E2) {
                    btn_Let_E_Inc.setVisibility(View.VISIBLE);
                    btn_Let_E_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto

                } else if (id == R.id.btn_O2) {
                    btn_Let_O_Certo.setVisibility(View.VISIBLE);
                    btn_Let_O_Certo.setEnabled(true);
                    isCorrect = true; // Correto

                } else if (id == R.id.btn_G2) {
                    btn_Let_G_Inc.setVisibility(View.VISIBLE);
                    btn_Let_G_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto
                }

                salvarResultadoNoBanco(isCorrect);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv4_fase1.this, Tela_Atv5_fase1.class));
                        finish();
                    }
                }, 2000);
            }
        };

        btn_Let_P.setOnClickListener(listener);
        btn_Let_E.setOnClickListener(listener);
        btn_Let_O.setOnClickListener(listener);
        btn_Let_G.setOnClickListener(listener);
    }

    private void salvarResultadoNoBanco(boolean isCorrect) {
        // Código para salvar no banco de dados se a resposta foi correta ou não
        // Exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
    }
}
