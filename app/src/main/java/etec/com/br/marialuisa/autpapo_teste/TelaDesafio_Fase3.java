package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaDesafio_Fase3 extends AppCompatActivity {

    ImageView btConsegui3, btPasso3;
    TextView txEnun;//Para tocar e o audio sair (enunciado)
    MediaPlayer audio;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio_fase3);

        btConsegui3 = findViewById(R.id.imgConseguiFase3);
        btPasso3 = findViewById(R.id.imgPassoFase3);
        txEnun = findViewById(R.id.txtEnunDesafio3);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.desafio_fase3);
            }
        }, 1000);

        txEnun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.desafio_fase3);
            }
        });
        btConsegui3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.dconsegui);
                Intent abrirCon = new Intent(TelaDesafio_Fase3.this,Tela_Consegui.class);
                startActivity(abrirCon);
            }
        });

        btPasso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.dpasso);
                Intent abrirPasso = new Intent(TelaDesafio_Fase3.this,Tela_Passo.class);
                startActivity(abrirPasso);
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
    public void onBackPressed() {

    }
}





