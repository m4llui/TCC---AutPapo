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

public class Tela_Atv1_fase2 extends AppCompatActivity {
    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_Bola, btn_Let_Bola_Certo, btn_Arvore, btn_Arvore_errado, btn_limao, btn_limao_errado,
            btn_mao, btn_mao_errado, btVoltar, btBalao;
    private Handler handler = new Handler();

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
        btEnunciado = findViewById(R.id.txtAtv1_fase2);
        btBalao = findViewById(R.id.imageBalaoB);

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

                playAudio(R.raw.letra_b);
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
                Intent abrirHome =  new Intent(Tela_Atv1_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });


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

                int id = view.getId();

                if (id == R.id.btn_bola) {
                    btn_Let_Bola_Certo.setVisibility(View.VISIBLE);
                    btn_Let_Bola_Certo.setEnabled(true);
                    playAudio(R.raw.bola);

                } else if (id == R.id.btn_arvore) {
                    btn_Arvore_errado.setVisibility(View.VISIBLE);
                    btn_Arvore_errado.setEnabled(true);
                    playAudio(R.raw.arvore);

                } else if (id == R.id.btn_limao) {
                    btn_limao_errado.setVisibility(View.VISIBLE);
                    btn_limao_errado.setEnabled(true);
                    playAudio(R.raw.limao);

                } else if (id == R.id.btn_mao) {
                    btn_mao_errado.setVisibility(View.VISIBLE);
                    btn_mao_errado.setEnabled(true);
                    playAudio(R.raw.mao);

                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv1_fase2.this, Tela_Atv2_fase2.class));
                        finish(); // Navega para a próxima tela
                    }
                }, 2000);
            }
        };

        btn_Bola.setOnClickListener(listener);
        btn_Arvore.setOnClickListener(listener);
        btn_limao.setOnClickListener(listener);
        btn_mao.setOnClickListener(listener);
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