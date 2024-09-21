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

    private boolean buttonSelected = false;

    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_ABCD, btn_ABCD_errado, btn_EFGH, btn_EFGH_Certo, btn_NBFH, btn_NBFH_errado,
            btn_JKLM, btn_JKLM_errado, btVoltar, btBalao, notCerto, notErro;
    private Handler handler = new Handler();


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
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);


      handler.postDelayed(new Runnable() {
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

        //Botão para voltar para a home
        btVoltar.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer o audio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv4_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
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
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);

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
                if (buttonSelected) {
                    //PARA SELECIONAR SÓ UM BOTÃO
                    return;
                }
                int id = view.getId();
                buttonSelected = true;
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

                if(id==R.id.btn_efgh){
                   handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notCerto.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_acertou);
                        }
                    }, 1900);
                }else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notErro.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_erro);
                        }
                    }, 1900);
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv4_fase2.this, Tela_Atv5_fase2.class));
                        finish();
                    }
                }, 3200);
            }
        };

        // Definindo os listeners para cada botão
        btn_ABCD.setOnClickListener(listener);
        btn_EFGH.setOnClickListener(listener);
        btn_NBFH.setOnClickListener(listener);
        btn_JKLM.setOnClickListener(listener);
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
