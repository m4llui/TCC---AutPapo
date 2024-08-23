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
    private MediaPlayer mediaPlayer;
    ImageView btVoltar;
    private static final int DELAY_MILLIS = 4000; // 4 segundos em milissegundos

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fase1);

        btVoltar = findViewById(R.id.btnVoltar);

        // Inicializa o MediaPlayer com o áudio do recurso raw
        mediaPlayer = MediaPlayer.create(this, R.raw.intro_fase1);

        // Inicia a reprodução do áudio
        mediaPlayer.start();

        // Libere o MediaPlayer quando a Activity for destruída
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        // Usar Handler para iniciar a nova Activity após 5 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia a nova Activity após o delay
                Intent intent = new Intent(Tela_Fase1.this, Tela_Atv1_fase1.class);
                startActivity(intent);
                finish(); // Finaliza a Activity atual
            }
        }, DELAY_MILLIS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Certifique-se de liberar o MediaPlayer quando a Activity for destruída
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        // Bloqueia a ação de voltar e mostra uma mensagem
        Toast.makeText(this, "Use a seta do app para voltar!", Toast.LENGTH_SHORT).show();
    }
}
