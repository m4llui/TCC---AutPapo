package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaDesafio_Fase1 extends AppCompatActivity {

    ImageView btConsegui;
    TextView txEnun;
    ImageView btPasso;
    MediaPlayer audio;
    String fase;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio_fase1);

        btConsegui = findViewById(R.id.imgConseguiFase1);
        btPasso = findViewById(R.id.imgPassoFase1);
        txEnun = findViewById(R.id.txtEnunDesafio1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.desafio_fase1);
            }
        }, 1000);

        txEnun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.desafio_fase1);
            }
        });

        btConsegui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.dconsegui);
                Intent abrirCon = new Intent(TelaDesafio_Fase1.this,Tela_Consegui.class);
                abrirCon.putExtra("fase1",fase);
                startActivity(abrirCon);
            }
        });

        btPasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.dpasso);
                Intent abrirPasso = new Intent(TelaDesafio_Fase1.this,Tela_Passo.class);
                abrirPasso.putExtra("fase1",fase);
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




