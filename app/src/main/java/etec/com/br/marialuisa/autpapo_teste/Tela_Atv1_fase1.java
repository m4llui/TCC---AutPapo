package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
public class Tela_Atv1_fase1 extends AppCompatActivity {

    private ImageView btn_Let_A, btn_Let_A_Certo, btn_Let_B, btn_Let_B_Inc, btn_Let_C, btn_Let_C_Inc,
            btn_Let_D, btn_Let_D_Inc, btVoltar2, btEnunciado, btBalao;
    private Handler handler = new Handler();
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv1_fase1);

        btn_Let_A = findViewById(R.id.btn_Let_A);
        btn_Let_A_Certo = findViewById(R.id.btn_Let_A_Certo);
        btn_Let_B = findViewById(R.id.btn_Let_B);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_B_Inc);
        btn_Let_C = findViewById(R.id.btn_Let_C);
        btn_Let_C_Inc = findViewById(R.id.btn_Let_C_Inc);
        btn_Let_D = findViewById(R.id.btn_Let_D);
        btn_Let_D_Inc = findViewById(R.id.btn_Let_D_Inc);
        btVoltar2 = findViewById(R.id.btnVoltarAtv1Fase1);
        btBalao = findViewById(R.id.imageBalao);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_A_Certo.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_C_Inc.setVisibility(View.INVISIBLE);
        btn_Let_D_Inc.setVisibility(View.INVISIBLE);

        btn_Let_A_Certo.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_C_Inc.setEnabled(false);
        btn_Let_D_Inc.setEnabled(false);
    }


    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_Let_A) {
                    btn_Let_A_Certo.setVisibility(View.VISIBLE);
                    btn_Let_A_Certo.setEnabled(true);

                } else if (id == R.id.btn_Let_B) {
                    btn_Let_B_Inc.setVisibility(View.VISIBLE);
                    btn_Let_B_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_C) {
                    btn_Let_C_Inc.setVisibility(View.VISIBLE);
                    btn_Let_C_Inc.setEnabled(true);

                } else if (id == R.id.btn_Let_D) {
                    btn_Let_D_Inc.setVisibility(View.VISIBLE);
                    btn_Let_D_Inc.setEnabled(true);


                }

                // Salva o resultado no banco de dados
                //salvarResultadoNoBanco(isCorrect);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv1_fase1.this, Tela_Atv2_fase1.class));
                        finish();//
                    }
                }, 2000);
            }
        };


        btn_Let_A.setOnClickListener(listener);
        btn_Let_B.setOnClickListener(listener);
        btn_Let_C.setOnClickListener(listener);
        btn_Let_D.setOnClickListener(listener);
    }

    private void salvarResultadoNoBanco(boolean isCorrect) {
        // Código para salvar no banco de dados se a resposta foi correta ou não
        // Exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
    }
            }

