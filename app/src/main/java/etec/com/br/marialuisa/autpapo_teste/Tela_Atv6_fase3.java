package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Tela_Atv6_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    TextView btEnunciado;
    private ImageView btFeliz, btFelizCerto, btTriste, btTristeErrado, btCansado, btCansadoErrado,
            btRaiva, btRaivaErrado, btVolta, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv6_fase3);

        btEnunciado = findViewById(R.id.txtEnunEmocao6);
        btBalao = findViewById(R.id.balao_feliz);
        btFeliz = findViewById(R.id.btn_feliz);
        btFelizCerto = findViewById(R.id.btn_feliz_certa);
        btTriste = findViewById(R.id.btn_triste);
        btTristeErrado = findViewById(R.id.btn_triste_errada);
        btCansado = findViewById(R.id.btn_cansado);
        btCansadoErrado = findViewById(R.id.btn_cansado_errada);
        btRaiva = findViewById(R.id.btn_raiva);
        btRaivaErrado = findViewById(R.id.btn_raiva_errada);
        btVolta = findViewById(R.id.btnVoltarAtv6Fase3);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.enun_emocao);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.feliz);
            }
        });
    }
    private void playAudio(int audioResId) {
        // Libere o MediaPlayer anterior, se houver
        if (audio != null) {
            audio.release();
        }
        // Cria um novo MediaPlayer e toca o áudio
        audio = MediaPlayer.create(this, audioResId);
        audio.start();

        // Listener para liberar o MediaPlayer após a reprodução
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                audio = null;
            }
        });

    }
}