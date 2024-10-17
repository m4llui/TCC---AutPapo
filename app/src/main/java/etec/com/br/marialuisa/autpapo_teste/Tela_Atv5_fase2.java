package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv5_fase2 extends AppCompatActivity {

    MediaPlayer audio;
    //atualizando
    private boolean buttonSelected = false;
    TextView enunciado;


    private ImageView btJP, btNA, btJPCerto, btNAerrado,
            btVoltarAtv5Fase2, btbalaoSeq, triangulo, losangulo,jVerde, jVermelho,pVerde, pVermelho,
    notCerto, notErro;
    private Handler handler = new Handler();
    private int codCrianca;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv5_fase2);


        btJP = findViewById(R.id.btn_JP);
        enunciado = findViewById(R.id.txtEnunciadoSeq1);
        btJPCerto = findViewById(R.id.btn_JPcerto);
        btNA = findViewById(R.id.btn_NA);
        btNAerrado = findViewById(R.id.btn_NAerrada);
        btVoltarAtv5Fase2 = findViewById(R.id.btnVoltarAtv5Fase2);
        btbalaoSeq = findViewById(R.id.balaoSeq2);
        triangulo = findViewById(R.id.imgTriang);
        losangulo = findViewById(R.id.imgLosangulo);
        jVerde = findViewById(R.id.j_verde);
        jVermelho = findViewById(R.id.j_vermelho);
        pVerde = findViewById(R.id.p_verde);
        pVermelho = findViewById(R.id.p_vermelho);
        notErro = findViewById(R.id.not_erro);
        notCerto = findViewById(R.id.not_acerto);

        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.enun_complete_seq);
            }
        }, 1000);

        btbalaoSeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.enun_escolha_letras);
            }
        });

        enunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.enun_complete_seq);
            }
        });

        btVoltarAtv5Fase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirHome = new Intent(Tela_Atv5_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });


        botoesInativados();
        setOnClickListeners();
    }


    private void botoesInativados() {

        btJPCerto.setVisibility(View.INVISIBLE);
        btNAerrado.setVisibility(View.INVISIBLE);
        jVerde.setVisibility(View.INVISIBLE);
        jVermelho.setVisibility(View.INVISIBLE);
        pVermelho.setVisibility(View.INVISIBLE);
        pVerde.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);

        jVermelho.setEnabled(false);
        jVerde.setEnabled(false);
        pVerde.setEnabled(false);
        pVermelho.setEnabled(false);
        btJPCerto.setEnabled(false);
        btNAerrado.setEnabled(false);

    }

    private void setOnClickListeners() {

        btJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonSelected) {
                    //PARA SELECIONAR SÓ UM BOTÃO
                    return;
                }

                buttonSelected = true;

                btJPCerto.setVisibility(View.VISIBLE);
                playAudio(R.raw.j_p);
                btJP.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        triangulo.setVisibility(View.INVISIBLE);
                        losangulo.setVisibility(View.INVISIBLE);
                        pVerde.setVisibility(View.VISIBLE);
                        jVerde.setVisibility(View.VISIBLE);
                        notCerto.setVisibility(View.VISIBLE);
                        playAudio(R.raw.not_acertou);

                    }
                }, 1200);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Tela_Atv5_fase2.this, Tela_Atv6_fase2.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        });


        btNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btNAerrado.setVisibility(View.VISIBLE);
                playAudio(R.raw.n_a);
                btNA.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        triangulo.setVisibility(View.INVISIBLE);
                        losangulo.setVisibility(View.INVISIBLE);

                    }
                }, 1100);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pVermelho.setVisibility(View.VISIBLE);
                        jVermelho.setVisibility(View.VISIBLE);
                        notErro.setVisibility(View.VISIBLE);
                        playAudio(R.raw.not_erro);
                    }
                }, 1200);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Tela_Atv5_fase2.this, Tela_Atv6_fase2.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3200);


            }
        });
    }

    private void playAudio(int audioResId) {

        if (audio != null) {
            audio.release();
        }

        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }

    @Override
    public void onBackPressed(){

    }

}