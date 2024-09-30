package etec.com.br.marialuisa.autpapo_teste;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_Finalizacao_fase1 extends AppCompatActivity {

    ImageView btnHome;
    MediaPlayer audio;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_finalizacao_fase1);

        btnHome = findViewById(R.id.btn_home_fase1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.parabens_fase1);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirHome =  new Intent(Tela_Finalizacao_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
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

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize o botão da home para voltar!", Toast.LENGTH_SHORT).show();

    }
}
