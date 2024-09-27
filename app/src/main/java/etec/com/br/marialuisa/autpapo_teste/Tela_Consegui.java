package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class Tela_Consegui extends AppCompatActivity {

    private static final int DELAY_TIME = 5000;
    MediaPlayer audio;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consegui);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playAudio(R.raw.audio_consegui);
            }
        }, 1000);

        if (getIntent().hasExtra("fase2")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent abrirLogin = new Intent(Tela_Consegui.this, Tela_Finalizacao_fase2.class);
                    startActivity(abrirLogin);
                }
            }, DELAY_TIME);

        } else if (getIntent().hasExtra("fase1")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Tela_Consegui.this, Tela_Finalizacao_fase1.class);
                    startActivity(intent);
                    finish();
                }
            }, DELAY_TIME);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Tela_Consegui.this, Tela_Finalizacao_fase3.class);
                    startActivity(intent);
                    finish();
                }
            }, DELAY_TIME);
        }
    }

    // Método playAudio fora do onCreate
    private void playAudio(int audioResId) {
        if (audio != null) {
            audio.release();
        }
        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }

    @Override
    public void onBackPressed() {
    }
}
