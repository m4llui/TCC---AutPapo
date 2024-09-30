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

public class Tela_Atv6_fase2 extends AppCompatActivity {

    MediaPlayer audio;
    //atualizando
    private boolean buttonSelected = false;
    TextView btEnunciado;

    private ImageView btSU, btAB, btSUCerto, btABerrado, btVoltarAtv6Fase2, btbalaoSeq2, retangulo1,
            retangulo2,sVerde, sVermelho,uVerde,uVermelho, notCerto, notErro;
    private Handler handler = new Handler();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv6_fase2);


        btSU = findViewById(R.id.btn_SU);
        btSUCerto = findViewById(R.id.btn_SUcerto);
        btAB = findViewById(R.id.btn_AB);
        btABerrado = findViewById(R.id.btn_ABerrada);
        btVoltarAtv6Fase2 = findViewById(R.id.btnVoltarAtv6Fase);
        btEnunciado = findViewById(R.id.txtEnunciadoSeq2);
        btbalaoSeq2 = findViewById(R.id.balaoSeq2);
        retangulo1 = findViewById(R.id.retangulo1);
        retangulo2 = findViewById(R.id.retangulo2);
        sVerde = findViewById(R.id.s_verde);
        sVermelho =findViewById(R.id.s_vermelho);
        uVerde = findViewById(R.id.u_verde);
        uVermelho = findViewById(R.id.u_vermelha);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.enun_complete_seq);
            }
        }, 1000);

        btbalaoSeq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.enun_escolha_letras);
            }
        });

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.enun_complete_seq);
            }
        });

        btVoltarAtv6Fase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirHome = new Intent(Tela_Atv6_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {

        btSUCerto.setVisibility(View.INVISIBLE);
        btABerrado.setVisibility(View.INVISIBLE);
        sVerde.setVisibility(View.INVISIBLE);
        sVermelho.setVisibility(View.INVISIBLE);
        uVerde.setVisibility(View.INVISIBLE);
        uVermelho.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);


        btSUCerto.setEnabled(false);
        btABerrado.setEnabled(false);
        sVerde.setEnabled(false);
        sVermelho.setEnabled(false);
        uVermelho.setEnabled(false);
        uVerde.setEnabled(false);
    }

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

                if (id == R.id.btn_SU) {

                    btSUCerto.setVisibility(View.VISIBLE);
                    btSU.setEnabled(false);
                    playAudio(R.raw.s_u);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            retangulo1.setVisibility(View.INVISIBLE);
                            retangulo2.setVisibility(View.INVISIBLE);
                            uVerde.setVisibility(View.VISIBLE);
                            sVerde.setVisibility(View.VISIBLE);
                            notCerto.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_acertou);
                        }
                    }, 1000);


                } else if (id == R.id.btn_AB) {

                    btABerrado.setVisibility(View.VISIBLE);
                    playAudio(R.raw.a_b);
                    btAB.setEnabled(false);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            retangulo1.setVisibility(View.INVISIBLE);
                            retangulo2.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uVermelho.setVisibility(View.VISIBLE);
                            sVermelho.setVisibility(View.VISIBLE);
                            notErro.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_erro);
                        }
                    }, 1000);
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv6_fase2.this, Tela_Atv7_fase2.class));
                        finish();
                    }
                }, 2000);
            }
        };


        btSU.setOnClickListener(listener);
        btAB.setOnClickListener(listener);
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
