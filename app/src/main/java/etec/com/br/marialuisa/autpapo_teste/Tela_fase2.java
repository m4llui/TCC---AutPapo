package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tela_fase2 extends AppCompatActivity {

    private MediaPlayer audio;
    private ImageView btVoltar;
    private static final int DELAY_MILLIS = 4000; // 4 segundos
    private Handler handler;
    private Runnable startAtv1Runnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fase2);

        btVoltar = findViewById(R.id.btnVoltarFase2);
        audio = MediaPlayer.create(this, R.raw.intro_fase2);
        audio.start();

        handler = new Handler();
        startAtv1Runnable = () -> {
            startActivity(new Intent(Tela_fase2.this, Tela_Atv1_fase2.class));
            finish();
        };


        handler.postDelayed(startAtv1Runnable, DELAY_MILLIS);

        btVoltar.setOnClickListener(view -> {

            handler.removeCallbacks(startAtv1Runnable);
            Intent voltarHome = new Intent(Tela_fase2.this, Tela_Home.class);
            startActivity(voltarHome);
            //LIBERA O AUDIO
            if (audio != null) {
                audio.stop();
                audio.release();
                audio = null;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //PARA E LIBERA O AUDIO EM SEGUNDO PLANO
        if (audio != null) {
            audio.stop();
            audio.release();
            audio = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //LIBERANDO O AUDIO QUANDO A ACTIVITY É DESTRUIDA
        if (audio != null) {
            audio.release();
            audio = null;
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Use a seta do app para voltar!", Toast.LENGTH_SHORT).show();
    }
}

