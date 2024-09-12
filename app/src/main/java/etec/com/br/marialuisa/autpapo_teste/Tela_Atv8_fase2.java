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

    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_Flor, btn_Flor_errado, btn_Nariz, btn_Nariz_errado, btn_Zebra, btn_Zebra_certa,
            btn_Planeta, btn_Planeta_errado, btVoltar, btBalao;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv8_fase2);

        btn_Flor = findViewById(R.id.btn_Flor);
        btn_Flor_errado = findViewById(R.id.btn_Flor_errado);
        btn_Nariz = findViewById(R.id.btn_Nariz);
        btn_Nariz_errado = findViewById(R.id.btn_Nariz_errado);
        btn_Zebra = findViewById(R.id.btn_Zebra);
        btn_Zebra_certa = findViewById(R.id.btn_Zebra_certa);
        btn_Planeta = findViewById(R.id.btn_Planeta);
        btn_Planeta_errado = findViewById(R.id.btn_Planeta_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv8Fase2);
        btEnunciado = findViewById(R.id.txtEnunAtv1Fase1);
        btBalao = findViewById(R.id.imageBalaoLetraZ);

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

        btn_Flor_errado.setEnabled(false);
        btn_Nariz_errado.setEnabled(false);
        btn_Zebra_certa.setEnabled(false);
        btn_Planeta_errado.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_Flor) {
                    btn_Flor_errado.setVisibility(View.VISIBLE);
                    btn_Flor_errado.setEnabled(true);
                    playAudio(R.raw.flor);

                } else if (id == R.id.btn_Nariz) {
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

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv8_fase2.this, Tela_Video_fase2.class));
                        finish();
                    }
                }, 2000);
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
