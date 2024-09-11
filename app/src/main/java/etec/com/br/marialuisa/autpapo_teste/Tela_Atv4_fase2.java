package etec.com.br.marialuisa.autpapo_teste;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv4_fase2 extends AppCompatActivity {

    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_ABCD, btn_ABCD_errado, btn_EFGH, btn_EFGH_Certo, btn_NBFH, btn_NBFH_errado,
            btn_JKLM, btn_JKLM_errado, btVoltar, btBalao;
    private Handler delayHandler = new Handler();  // Renomeado de 'handler' para 'delayHandler'


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv4_fase2);

        // Inicializando os botões com seus respectivos IDs (ordem trocada)
        btn_ABCD = findViewById(R.id.btn_abcd_atvd4);
        btn_ABCD_errado = findViewById(R.id.btn_abcd_errado);
        btn_EFGH = findViewById(R.id.btn_efgh);
        btn_EFGH_Certo = findViewById(R.id.btn_efgh_certo);
        btn_NBFH = findViewById(R.id.btn_nbfh);
        btn_NBFH_errado = findViewById(R.id.btn_nbfh_errado);
        btn_JKLM = findViewById(R.id.btn_jklm);
        btn_JKLM_errado = findViewById(R.id.btn_jklm_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv4Fase2);
        btEnunciado = findViewById(R.id.txtEnunAtv4Fase2);
        btBalao = findViewById(R.id.balaoAtv4);


        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.selecionar_sequencia);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.selecionar_sequencia);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.efgh);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv4_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });


        botoesInativados();


        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_ABCD_errado.setVisibility(View.INVISIBLE);
        btn_EFGH_Certo.setVisibility(View.INVISIBLE);
        btn_NBFH_errado.setVisibility(View.INVISIBLE);
        btn_JKLM_errado.setVisibility(View.INVISIBLE);

        btn_ABCD_errado.setEnabled(false);
        btn_EFGH_Certo.setEnabled(false);
        btn_NBFH_errado.setEnabled(false);
        btn_JKLM_errado.setEnabled(false);
    }

    // Método para definir os listeners dos botões
    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_abcd_atvd4) {
                    btn_ABCD_errado.setVisibility(View.VISIBLE);
                    btn_ABCD_errado.setEnabled(true);
                    playAudio(R.raw.abcd);

                } else if (id == R.id.btn_efgh) {
                    btn_EFGH_Certo.setVisibility(View.VISIBLE);
                    btn_EFGH_Certo.setEnabled(true);
                    playAudio(R.raw.efgh);

                } else if (id == R.id.btn_nbfh) {
                    btn_NBFH_errado.setVisibility(View.VISIBLE);
                    btn_NBFH_errado.setEnabled(true);
                    playAudio(R.raw.n_b_f_h);

                } else if (id == R.id.btn_jklm) {
                    btn_JKLM_errado.setVisibility(View.VISIBLE);
                    btn_JKLM_errado.setEnabled(true);
                    playAudio(R.raw.j_k_l_m);
                }

                // Aguarda 2 segundos antes de navegar para a próxima atividade
                delayHandler.postDelayed(new Runnable() {  // Usando 'delayHandler' em vez de 'handler'
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv4_fase2.this, Tela_Atv7_fase2.class));
                        finish();
                    }
                }, 2000);
            }
        };

        // Definindo os listeners para cada botão
        btn_ABCD.setOnClickListener(listener);
        btn_EFGH.setOnClickListener(listener);
        btn_NBFH.setOnClickListener(listener);
        btn_JKLM.setOnClickListener(listener);
    }
 private void salvarResultadoNoBanco(boolean isCorrect) {
    // Código para salvar no banco de dados se a resposta foi correta ou não
    // Exemplo:
    // DatabaseHelper db = new DatabaseHelper(this);
    // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
}

    private void playAudio(int audioResId) {

        if (audio != null) {
            audio.release();
        }

        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
    }
}
