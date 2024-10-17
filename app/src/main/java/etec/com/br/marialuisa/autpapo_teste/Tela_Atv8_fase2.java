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

public class Tela_Atv8_fase2 extends AppCompatActivity {

    private boolean buttonSelected = false;
    //atualizando
    TextView btEnunciado;
    MediaPlayer audio;



    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_Flor, btn_Flor_errado, btn_Nariz, btn_Nariz_errado, btn_Zebra, btn_Zebra_certa,
            btn_Planeta, btn_Planeta_errado, btVoltar, btBalao, notCerto, notErro;
    private Handler handler = new Handler();


    private int codCrianca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv8_fase2);

        btn_Flor = findViewById(R.id.btn_Flor);
        btn_Flor_errado = findViewById(R.id.btn_Flor_errado);
        btn_Nariz = findViewById(R.id.btn_Nariz1);
        btn_Nariz_errado = findViewById(R.id.btn_Nariz_errado);
        btn_Zebra = findViewById(R.id.btn_Zebra);
        btn_Zebra_certa = findViewById(R.id.btn_Zebra_certa);
        btn_Planeta = findViewById(R.id.btn_Planeta);
        btn_Planeta_errado = findViewById(R.id.btn_Planeta_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv8Fase2);
        btEnunciado = findViewById(R.id.txtEnunAtv8Fase2);
        btBalao = findViewById(R.id.imageBalaoLetraZ);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.letra_objeto);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.letra_objeto);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.letra_z);
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
                Intent abrirHome =  new Intent(Tela_Atv8_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Flor_errado.setVisibility(View.INVISIBLE);
        btn_Nariz_errado.setVisibility(View.INVISIBLE);
        btn_Zebra_certa.setVisibility(View.INVISIBLE);
        btn_Planeta_errado.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        btn_Flor_errado.setEnabled(false);
        btn_Nariz_errado.setEnabled(false);
        btn_Zebra_certa.setEnabled(false);
        btn_Planeta_errado.setEnabled(false);
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

                if (id == R.id.btn_Flor) {
                    btn_Flor_errado.setVisibility(View.VISIBLE);
                    btn_Flor_errado.setEnabled(true);
                    playAudio(R.raw.flor);

                } else if (id == R.id.btn_Nariz1) {
                    btn_Nariz_errado.setVisibility(View.VISIBLE);
                    btn_Nariz_errado.setEnabled(true);
                    playAudio(R.raw.nariz);

                } else if (id == R.id.btn_Zebra) {
                    btn_Zebra_certa.setVisibility(View.VISIBLE);
                    btn_Zebra_certa.setEnabled(true);
                    playAudio(R.raw.zebra);

                } else if (id == R.id.btn_Planeta) {
                    btn_Planeta_errado.setVisibility(View.VISIBLE);
                    btn_Planeta_errado.setEnabled(true);
                    playAudio(R.raw.planeta);
                }
                if (id == R.id.btn_Zebra) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notCerto.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_acertou);
                        }
                    }, 1100);
                } else {
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
                        Intent intent = new Intent(Tela_Atv8_fase2.this, Tela_Video_fase2.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3200);
            }


        };

        btn_Flor.setOnClickListener(listener);
        btn_Nariz.setOnClickListener(listener);
        btn_Zebra.setOnClickListener(listener);
        btn_Planeta.setOnClickListener(listener);
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
