package etec.com.br.marialuisa.autpapo_teste;

import androidx.annotation.NonNull;
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

public class Tela_Atv7_fase2 extends AppCompatActivity {

    private boolean buttonSelected = false;
    //atualizando
    TextView btEnunciado;
    MediaPlayer audio;

    private int codCrianca;
    private ImageView btn_Let_Y, btn_Let_Y_Certo, btn_Let_A, btn_Let_A_Inc, btn_Let_K, btn_Let_K_Inc,
            btn_Let_Q, btn_Let_Q_Inc, btVoltar, btBalao, notErro, notCerto;
    private Handler handler = new Handler();
    //

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv7_fase2);

        btn_Let_Y = findViewById(R.id.btn_Y2);
        btn_Let_Y_Certo = findViewById(R.id.btn_Let_YCerto);
        btn_Let_A = findViewById(R.id.btn_A);
        btn_Let_A_Inc = findViewById(R.id.btn_Let_A_errado);
        btn_Let_K = findViewById(R.id.btn_K2);
        btn_Let_K_Inc = findViewById(R.id.btn_Let_K_errado);
        btn_Let_Q = findViewById(R.id.btn_Q);
        btn_Let_Q_Inc = findViewById(R.id.btn_Let_Q_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv1Fase1);
        btEnunciado = findViewById(R.id.txtEnunAtv7Fase2);
        btBalao = findViewById(R.id.imageBalaoLetraYakult);
        notErro = findViewById(R.id.not_erro);
        notCerto = findViewById(R.id.not_acerto);

        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.bebida_letra);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.bebida_letra);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.balaoyakult);
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
                Intent abrirHome =  new Intent(Tela_Atv7_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });


        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_Y_Certo.setVisibility(View.INVISIBLE);
        btn_Let_A_Inc.setVisibility(View.INVISIBLE);
        btn_Let_K_Inc.setVisibility(View.INVISIBLE);
        btn_Let_Q_Inc.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);

        btn_Let_Y_Certo.setEnabled(false);
        btn_Let_A_Inc.setEnabled(false);
        btn_Let_K_Inc.setEnabled(false);
        btn_Let_Q_Inc.setEnabled(false);
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

                if (id == R.id.btn_Y2) {
                    btn_Let_Y_Certo.setVisibility(View.VISIBLE);
                    btn_Let_Y_Certo.setEnabled(true);
                    playAudio(R.raw.letra_y);

                } else if (id == R.id.btn_A) {
                    btn_Let_A_Inc.setVisibility(View.VISIBLE);
                    btn_Let_A_Inc.setEnabled(true);
                    playAudio(R.raw.letra_a);

                } else if (id == R.id.btn_K2) {
                    btn_Let_K_Inc.setVisibility(View.VISIBLE);
                    btn_Let_K_Inc.setEnabled(true);
                    playAudio(R.raw.letra_k);

                } else if (id == R.id.btn_Q) {
                    btn_Let_Q_Inc.setVisibility(View.VISIBLE);
                    btn_Let_Q_Inc.setEnabled(true);
                    playAudio(R.raw.letra_q);

                }
                if(id==R.id.btn_Y2){
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
                        Intent intent = new Intent(Tela_Atv7_fase2.this, Tela_Atv8_fase2.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3200);
            }
        };


        btn_Let_Y.setOnClickListener(listener);
        btn_Let_A.setOnClickListener(listener);
        btn_Let_K.setOnClickListener(listener);
        btn_Let_Q.setOnClickListener(listener);
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