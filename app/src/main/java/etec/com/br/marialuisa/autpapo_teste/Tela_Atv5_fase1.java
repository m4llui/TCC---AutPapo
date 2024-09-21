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

public class Tela_Atv5_fase1 extends AppCompatActivity {

    private boolean buttonSelected = false;

    TextView btEnunciado;

    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;

    private ImageView btn_Nm_1, btn_Nm_1_Inc, btn_Let_U, btn_Let_U_Certo, btn_Let_S, btn_Let_S_Inc,
            btn_Let_D, btn_Let_D_Inc, btVoltar5, btBalao, notCerto, notErro;
    private Handler handler = new Handler();

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
        btEnunciado = findViewById(R.id.txtEnunAtv5Fase1);
        btBalao = findViewById(R.id.ImageBalaofase1);
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

                playAudio(R.raw.letra_u);
            }
        });

        //Botão para voltar para a home
        btVoltar5.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer o audio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv5_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });


        botoesInativados();
        setOnClickListeners();
    }
    private void botoesInativados() {
        btn_Nm_1_Inc.setVisibility(View.INVISIBLE);
        btn_Let_U_Certo.setVisibility(View.INVISIBLE);
        btn_Let_S_Inc.setVisibility(View.INVISIBLE);
        btn_Let_D_Inc.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        btn_Nm_1_Inc.setEnabled(false);
        btn_Let_U_Certo.setEnabled(false);
        btn_Let_S_Inc.setEnabled(false);
        btn_Let_D_Inc.setEnabled(false);
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

                if (id == R.id.btn_Nm1) {
                    btn_Nm_1_Inc.setVisibility(View.VISIBLE);
                    btn_Nm_1_Inc.setEnabled(true);
                    playAudio(R.raw.num_1);


                } else if (id == R.id.btn_LetU) {
                    btn_Let_U_Certo.setVisibility(View.VISIBLE);
                    btn_Let_U_Certo.setEnabled(true);
                    playAudio(R.raw.letra_u);


                } else if (id == R.id.btn_LetS) {
                    btn_Let_S_Inc.setVisibility(View.VISIBLE);
                    btn_Let_S_Inc.setEnabled(true);
                    playAudio(R.raw.letra_s);


                } else if (id == R.id.btn_LetD){
                    btn_Let_D_Inc.setVisibility(View.VISIBLE);
                    btn_Let_D_Inc.setEnabled(true);
                    playAudio(R.raw.letra_d);

                }
                if(id==R.id.btn_LetU){
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
                        startActivity(new Intent(Tela_Atv5_fase1.this, Tela_Finalizacao_fase1.class));
                        finish();
                    }
                }, 3000);
            }
        };

        btn_Nm_1.setOnClickListener(listener);
        btn_Let_U.setOnClickListener(listener);
        btn_Let_S.setOnClickListener(listener);
        btn_Let_D.setOnClickListener(listener);
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