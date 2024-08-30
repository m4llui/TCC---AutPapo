package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
public class Tela_Atv1_fase2 extends AppCompatActivity {

    private ImageView btn_Bola, btn_Let_Bola_Certo, btn_Arvore, btn_Arvore_errado, btn_limao, btn_limao_errado,
            btn_mao, btn_mao_errado, btVoltar, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv1_fase2);

        btn_Bola = findViewById(R.id.btn_bola);
        btn_Let_Bola_Certo = findViewById(R.id.btn_bola_certo);
        btn_Arvore = findViewById(R.id.btn_arvore);
        btn_Arvore_errado = findViewById(R.id.btn_arvore_errado);
        btn_limao = findViewById(R.id.btn_limao);
        btn_limao_errado = findViewById(R.id.btn_limao_errado);
        btn_mao = findViewById(R.id.btn_mao);
        btn_mao_errado = findViewById(R.id.btn_mao_errada);
        btVoltar = findViewById(R.id.btnVoltarAtv1Fase2);
        btBalao = findViewById(R.id.imageBalaoB);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_Bola_Certo.setVisibility(View.INVISIBLE);
        btn_Arvore_errado.setVisibility(View.INVISIBLE);
        btn_limao_errado.setVisibility(View.INVISIBLE);
        btn_mao_errado.setVisibility(View.INVISIBLE);

        btn_Let_Bola_Certo.setEnabled(false);
        btn_Arvore_errado.setEnabled(false);
        btn_limao_errado.setEnabled(false);
        btn_mao_errado.setEnabled(false);
    }


    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_bola) {
                    btn_Let_Bola_Certo.setVisibility(View.VISIBLE);
                    btn_Let_Bola_Certo.setEnabled(true);

                } else if (id == R.id.btn_arvore) {
                    btn_Arvore_errado.setVisibility(View.VISIBLE);
                    btn_Arvore_errado.setEnabled(true);

                } else if (id == R.id.btn_limao) {
                    btn_limao_errado.setVisibility(View.VISIBLE);
                    btn_limao_errado.setEnabled(true);

                } else if (id == R.id.btn_mao) {
                    btn_mao_errado.setVisibility(View.VISIBLE);
                    btn_mao_errado.setEnabled(true);


                }

                // Salva o resultado no banco de dados
                //salvarResultadoNoBanco(isCorrect);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv1_fase2.this, Tela_Atv3_fase2.class));
                        finish();//
                    }
                }, 2000);
            }
        };


        btn_Bola.setOnClickListener(listener);
        btn_Arvore.setOnClickListener(listener);
        btn_limao.setOnClickListener(listener);
        btn_mao.setOnClickListener(listener);
    }

    private void salvarResultadoNoBanco(boolean isCorrect) {
        // Código para salvar no banco de dados se a resposta foi correta ou não
        // Exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
    }
}

