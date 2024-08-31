package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
public class Tela_Atv7_fase2 extends AppCompatActivity {

    private ImageView btn_Let_Y, btn_Let_Y_Certo, btn_Let_A, btn_Let_A_Inc, btn_Let_K, btn_Let_K_Inc,
            btn_Let_Q, btn_Let_Q_Inc, btVoltar, btEnunciado, btBalao;
    private Handler handler = new Handler();
    //
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv7_fase2);

        btn_Let_Y = findViewById(R.id.btn_Flor);
        btn_Let_Y_Certo = findViewById(R.id.btn_Let_YCerto);
        btn_Let_A = findViewById(R.id.btn_Nariz);
        btn_Let_A_Inc = findViewById(R.id.btn_Let_A_errado);
        btn_Let_K = findViewById(R.id.btn_Zebra);
        btn_Let_K_Inc = findViewById(R.id.btn_Let_K_errado);
        btn_Let_Q = findViewById(R.id.btn_Planeta);
        btn_Let_Q_Inc = findViewById(R.id.btn_Let_Q_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv7Fase2);
        btBalao = findViewById(R.id.imageBalaoLetraZ);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_Y_Certo.setVisibility(View.INVISIBLE);
        btn_Let_A_Inc.setVisibility(View.INVISIBLE);
        btn_Let_K_Inc.setVisibility(View.INVISIBLE);
        btn_Let_Q_Inc.setVisibility(View.INVISIBLE);

        btn_Let_Y_Certo.setEnabled(false);
        btn_Let_Q_Inc.setEnabled(false);
        btn_Let_K_Inc.setEnabled(false);
        btn_Let_Q_Inc.setEnabled(false);
    }


    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_Flor) {
                    btn_Let_Y_Certo.setVisibility(View.VISIBLE);
                    btn_Let_Y_Certo.setEnabled(true);

                } else if (id == R.id.btn_Nariz) {
                    btn_Let_A_Inc.setVisibility(View.VISIBLE);
                    btn_Let_A_Inc.setEnabled(true);

                } else if (id == R.id.btn_Zebra) {
                    btn_Let_K_Inc.setVisibility(View.VISIBLE);
                    btn_Let_K_Inc.setEnabled(true);

                } else if (id == R.id.btn_Planeta) {
                    btn_Let_Q_Inc.setVisibility(View.VISIBLE);
                    btn_Let_Q_Inc.setEnabled(true);


                }

                // Salva o resultado no banco de dados
                //salvarResultadoNoBanco(isCorrect);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv7_fase2.this, Tela_Atv8_fase2.class));
                        finish();//
                    }
                }, 2000);
            }
        };


        btn_Let_Y.setOnClickListener(listener);
        btn_Let_A.setOnClickListener(listener);
        btn_Let_K.setOnClickListener(listener);
        btn_Let_Q.setOnClickListener(listener);
    }

    private void salvarResultadoNoBanco(boolean isCorrect) {
        // Código para salvar no banco de dados se a resposta foi correta ou não
        // Exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
    }
}

