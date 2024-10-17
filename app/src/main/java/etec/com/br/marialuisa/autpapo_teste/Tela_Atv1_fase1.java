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

public class Tela_Atv1_fase1 extends AppCompatActivity {

    private boolean buttonSelected = false;
    TextView btEnunciado;
    MediaPlayer audio;
    //atualizando
    private ImageView btn_Let_A, btn_Let_A_Certo, btn_Let_B, btn_Let_B_Inc, btn_Let_C, btn_Let_C_Inc,
            btn_Let_D, btn_Let_D_Inc, btVoltar2, btBalao, notErro, notAcerto;
    private Handler handler = new Handler();

     private int codCrianca;
//


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv1_fase1);

        btn_Let_A = findViewById(R.id.btn_LetraA);
        btn_Let_A_Certo = findViewById(R.id.btn_Let_ACerto);
        btn_Let_B = findViewById(R.id.btn_B1);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_B_errado);
        btn_Let_C = findViewById(R.id.btn_C);
        btn_Let_C_Inc = findViewById(R.id.btn_Let_C_errado);
        btn_Let_D = findViewById(R.id.btn_D);
        btn_Let_D_Inc = findViewById(R.id.btn_Let_D_errado);
        btVoltar2 = findViewById(R.id.btnVoltarAtv1Fase1);
        btEnunciado = findViewById(R.id.txtEnunAtv8Fase2);
        btBalao = findViewById(R.id.imageBalaoLetraA);
        notErro = findViewById(R.id.not_erro);
        notAcerto = findViewById(R.id.not_acerto);

        // Recuperar o código da criança
        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.qual_letraessa);
            }
        }, 1000);

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.letra_a);
            }
        });

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.qual_letraessa);
            }
        });

        //Botão para voltar para a home
        btVoltar2.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer o audio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv1_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });


        botoesInativados();
        setOnClickListeners();
    }


    private void botoesInativados() {
        btn_Let_A_Certo.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_C_Inc.setVisibility(View.INVISIBLE);
        btn_Let_D_Inc.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notAcerto.setVisibility(View.INVISIBLE);

        btn_Let_A_Certo.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_C_Inc.setEnabled(false);
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

                if (id == R.id.btn_LetraA) {
                    btn_Let_A_Certo.setVisibility(View.VISIBLE);
                    btn_Let_A_Certo.setEnabled(true);
                    btn_Let_A.setVisibility(View.INVISIBLE);
                    btn_Let_A.setEnabled(false);
                    playAudio(R.raw.letra_a);

                } else if (id == R.id.btn_B1) {
                    btn_Let_B_Inc.setVisibility(View.VISIBLE);
                    btn_Let_B_Inc.setEnabled(true);
                    playAudio(R.raw.letra_b);

                } else if (id == R.id.btn_C) {
                    btn_Let_C_Inc.setVisibility(View.VISIBLE);
                    btn_Let_C_Inc.setEnabled(true);
                    playAudio(R.raw.letra_c);

                } else if (id == R.id.btn_D) {
                    btn_Let_D_Inc.setVisibility(View.VISIBLE);
                    btn_Let_D_Inc.setEnabled(true);
                    playAudio(R.raw.letra_d);


                }
                if(id==R.id.btn_LetraA){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notAcerto.setVisibility(View.VISIBLE);
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
                        Intent intent = new Intent(Tela_Atv1_fase1.this, Tela_Atv2_fase1.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        };


        btn_Let_A.setOnClickListener(listener);
        btn_Let_B.setOnClickListener(listener);
        btn_Let_C.setOnClickListener(listener);
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

