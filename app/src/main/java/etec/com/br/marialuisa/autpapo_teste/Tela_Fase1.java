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

public class Tela_Fase1 extends AppCompatActivity {


    private MediaPlayer audio;
    private ImageView btVoltar;
    private static final int DELAY_MILLIS = 4000; // 4 segundos
    private Handler handler;
    private Runnable startAtv1Runnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fase1);

        btVoltar = findViewById(R.id.btnVoltarFase1);
        audio = MediaPlayer.create(this, R.raw.intro_fase1);
        audio.start();

        Intent intent = getIntent();
        int codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca+"!", Toast.LENGTH_SHORT).show();

        handler = new Handler();
        startAtv1Runnable = () -> {
            Intent intentAtv1 = new Intent(Tela_Fase1.this, Tela_Atv1_fase1.class);
            intentAtv1.putExtra("codCrianca", codCrianca);
            startActivity(intentAtv1);
            finish();
        };


        handler.postDelayed(startAtv1Runnable, DELAY_MILLIS);

        btVoltar.setOnClickListener(view -> {

            handler.removeCallbacks(startAtv1Runnable);
            Intent voltarHome = new Intent(Tela_Fase1.this, Tela_Home.class);
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

