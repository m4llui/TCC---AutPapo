package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv2_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    private ImageView btI, btO,btOi, btIErrado, btOErrado, btICerto, btOCerto, btVolta, btBalao;

    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase3);

        btBalao = findViewById(R.id.balao_enun);
        btI = findViewById(R.id.btn_letraI);
        btO = findViewById(R.id.btn_letraO);
        btICerto = findViewById(R.id.btn_letraI_certa);
        btIErrado = findViewById(R.id.btn_letraI_errada);
        btOCerto = findViewById(R.id.btn_letraO_certa);
        btOErrado = findViewById(R.id.btn_letraO_errada);
        btVolta = findViewById(R.id.btnVoltar);
        btOi = findViewById(R.id.imgOi);

        //Leitura do Enunciado automatico
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playAudio(R.raw.monte_palavra);
            }
        }, 1000); // Atraso de 1 milissegundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.monte_palavra);
            }
        });

        btOi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.oi);
            }
        });

        //PARTE DOS BOTÕES

    }

    private void playAudio(int audioResId) {
        // Libere o MediaPlayer anterior, se houver
        if (audio != null) {
            audio.release();
        }
        // Cria um novo MediaPlayer e toca o áudio
        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }

}
