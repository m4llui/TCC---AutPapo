package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv5_fase1 extends AppCompatActivity {

    private ImageView btn_Nm_1, btn_Nm_1_Inc, btn_Let_U, btn_Let_U_Certo, btn_Let_S, btn_Let_S_Inc,
            btn_Let_D, btn_Let_D_Inc, btVoltar5, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv5_fase1);

        btn_Nm_1 = findViewById(R.id.btn_Nm1);
        btn_Nm_1_Inc = findViewById(R.id.btn_1_inc);
        btn_Let_U = findViewById(R.id.btn_LetU);
        btn_Let_U_Certo = findViewById(R.id.btn_U_Correta);
        btn_Let_S = findViewById(R.id.btn_LetS);
        btn_Let_S_Inc = findViewById(R.id.btn_S_inc);
        btn_Let_D = findViewById(R.id.btn_LetD);
        btn_Let_D_Inc = findViewById(R.id.btn_D_inc);
        btVoltar5 = findViewById(R.id.btnVoltarAtv5Fase1);
        btEnunciado = findViewById(R.id.imageEnunciado_fase1);
        btBalao = findViewById(R.id.ImageBalaofase1);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Nm_1_Inc.setVisibility(View.INVISIBLE);
        btn_Let_U_Certo.setVisibility(View.INVISIBLE);
        btn_Let_S_Inc.setVisibility(View.INVISIBLE);
        btn_Let_D_Inc.setVisibility(View.INVISIBLE);

        btn_Nm_1_Inc.setEnabled(false);
        btn_Let_U_Certo.setEnabled(false);
        btn_Let_S_Inc.setEnabled(false);
        btn_Let_D_Inc.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_Nm1) {
                    btn_Nm_1_Inc.setVisibility(View.VISIBLE);
                    btn_Nm_1_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto

                } else if (id == R.id.btn_LetU) {
                    btn_Let_U_Certo.setVisibility(View.VISIBLE);
                    btn_Let_U_Certo.setEnabled(true);
                    isCorrect = true; // Correto

                } else if (id == R.id.btn_LetS) {
                    btn_Let_S_Inc.setVisibility(View.VISIBLE);
                    btn_Let_S_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto

                } else if (id == R.id.btn_LetD){
                    btn_Let_D_Inc.setVisibility(View.VISIBLE);
                    btn_Let_D_Inc.setEnabled(true);
                    isCorrect = false; // Incorreto
                }

                salvarResultadoNoBanco(isCorrect);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv5_fase1.this, Tela_Atv2_fase1.class));
                        finish();
                    }
                }, 2000);
            }
        };

        btn_Nm_1.setOnClickListener(listener);
        btn_Let_U.setOnClickListener(listener);
        btn_Let_S.setOnClickListener(listener);
        btn_Let_D.setOnClickListener(listener);
    }

    private void salvarResultadoNoBanco(boolean isCorrect) {
        // Código para salvar no banco de dados se a resposta foi correta ou não
        // Exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
    }
}
