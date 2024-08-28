package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv3_fase1 extends AppCompatActivity {

    private ImageView btn_Let_K, btn_Let_K_Inc, btn_Let_I, btn_Let_I_Certo, btn_Let_O, btn_Let_O_Inc,
            btn_Let_G, btn_Let_G_Inc, btVoltar4, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv3_fase1);

        btn_Let_K = findViewById(R.id.btn_K);
        btn_Let_K_Inc = findViewById(R.id.btn_K_errado);
        btn_Let_I = findViewById(R.id.btn_I);
        btn_Let_I_Certo = findViewById(R.id.btn_I_Certo);
        btn_Let_O = findViewById(R.id.btn_O);
        btn_Let_O_Inc = findViewById(R.id.btn_O_errado);
        btn_Let_G = findViewById(R.id.btn_G);
        btn_Let_G_Inc = findViewById(R.id.btn_G_errado);
        btVoltar4 = findViewById(R.id.btnVoltar4);
        btEnunciado = findViewById(R.id.imageEnunciado3);
        btBalao = findViewById(R.id.imgBalao3);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_K_Inc.setVisibility(View.INVISIBLE);
        btn_Let_I_Certo.setVisibility(View.INVISIBLE);
        btn_Let_O_Inc.setVisibility(View.INVISIBLE);
        btn_Let_G_Inc.setVisibility(View.INVISIBLE);

        btn_Let_K_Inc.setEnabled(false);
        btn_Let_I_Certo.setEnabled(false);
        btn_Let_O_Inc.setEnabled(false);
        btn_Let_G_Inc.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_K) {
                    btn_Let_K_Inc.setVisibility(View.VISIBLE);
                    btn_Let_K_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto

                } else if (id == R.id.btn_I) {
                    btn_Let_I_Certo.setVisibility(View.VISIBLE);
                    btn_Let_I_Certo.setEnabled(true);
                    isCorrect = true; // Correto

                } else if (id == R.id.btn_O) {
                    btn_Let_O_Inc.setVisibility(View.VISIBLE);
                    btn_Let_O_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto

                } else if (id == R.id.btn_G) {
                    btn_Let_G_Inc.setVisibility(View.VISIBLE);
                    btn_Let_G_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto
                }

                salvarResultadoNoBanco(isCorrect);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv3_fase1.this, Tela_Atv2_fase1.class));
                        finish();
                    }
                }, 2000);
            }
        };

        btn_Let_K.setOnClickListener(listener);
        btn_Let_I.setOnClickListener(listener);
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
