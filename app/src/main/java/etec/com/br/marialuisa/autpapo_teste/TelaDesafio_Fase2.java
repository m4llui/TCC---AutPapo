package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class TelaDesafio_Fase2 extends AppCompatActivity {

    ImageView btConsegui2;
    ImageView btPasso2;
    String fase;
    MediaPlayer audio;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio_fase2);

        btConsegui2 = findViewById(R.id.imgConseguiFase2);
        btPasso2 = findViewById(R.id.imgPassoFase2);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.desafio_fase2);
            }
        }, 1000);

        btConsegui2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.dconsegui);
                Intent abrirCon = new Intent(TelaDesafio_Fase2.this,Tela_Consegui.class);
                abrirCon.putExtra("fase2",fase);
                startActivity(abrirCon);
            }
        });

        btPasso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.dpasso);
                Intent abrirPasso = new Intent(TelaDesafio_Fase2.this,Tela_Passo.class);
                abrirPasso.putExtra("fase2",fase);
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





