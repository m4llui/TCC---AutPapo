package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv3_fase1 extends AppCompatActivity {

    private boolean buttonSelected = false;

    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;

    private ImageView btn_Let_K, btn_Let_K_Inc, btn_Let_I, btn_Let_I_Certo, btn_Let_O, btn_Let_O_Inc,
            btn_Let_G, btn_Let_G_Inc, btVoltar4, btBalao, notErro, notCerto;
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
        btVoltar4 = findViewById(R.id.btnVoltarAtv3Fase1);
        btEnunciado = findViewById(R.id.txtEnunAtv3Fase1);
        btBalao = findViewById(R.id.BalaoAtv3Fase1);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.qual_letraessa);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.qual_letraessa);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.letra_i);
            }
        });

        //Botão para voltar para a home
        btVoltar4.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer o audio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv3_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });


        botoesInativados();
        setOnClickListeners();
    }


    private void botoesInativados() {
        btn_Let_K_Inc.setVisibility(View.INVISIBLE);
        btn_Let_I_Certo.setVisibility(View.INVISIBLE);
        btn_Let_O_Inc.setVisibility(View.INVISIBLE);
        btn_Let_G_Inc.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);

        btn_Let_K_Inc.setEnabled(false);
        btn_Let_I_Certo.setEnabled(false);
        btn_Let_O_Inc.setEnabled(false);
        btn_Let_G_Inc.setEnabled(false);
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


                if (id == R.id.btn_K) {
                    btn_Let_K_Inc.setVisibility(View.VISIBLE);
                    btn_Let_K_Inc.setEnabled(true);

                    playAudio(R.raw.letra_k);

                } else if (id == R.id.btn_I) {
                    btn_Let_I_Certo.setVisibility(View.VISIBLE);
                    btn_Let_I_Certo.setEnabled(true);

                    playAudio(R.raw.letra_i);

                } else if (id == R.id.btn_O) {
                    btn_Let_O_Inc.setVisibility(View.VISIBLE);
                    btn_Let_O_Inc.setEnabled(true);

                    playAudio(R.raw.letra_o);

                } else if (id == R.id.btn_G) {
                    btn_Let_G_Inc.setVisibility(View.VISIBLE);
                    btn_Let_G_Inc.setEnabled(true);

                    playAudio(R.raw.letra_g);
                }
                if(id==R.id.btn_I){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notCerto.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_acertou);
                        }
                    }, 1100);
                }else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notErro.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_erro);
                        }
                    }, 1100);
                }


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv3_fase1.this, Tela_Atv4_fase1.class));
                        finish();
                    }
                }, 3000);
            }
        };

        btn_Let_K.setOnClickListener(listener);
        btn_Let_I.setOnClickListener(listener);
        btn_Let_O.setOnClickListener(listener);
        btn_Let_G.setOnClickListener(listener);
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


