package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tela_fase3 extends AppCompatActivity {

    private MediaPlayer audio;
    private ImageView btVoltar;
    private static final int DELAY_MILLIS = 4000; // 4 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fase3);

        btVoltar = findViewById(R.id.btnVoltar);
        audio = MediaPlayer.create(this, R.raw.intro_fase3);
        audio.start();

        if (btVoltar != null) {
            btVoltar.setOnClickListener(view -> {
                Intent intent = new Intent(Tela_fase3.this, Tela_Fase1.class);
                startActivity(intent);
            });
        } else {
            Toast.makeText(this, "Botão 'Voltar' não encontrado", Toast.LENGTH_SHORT).show();
        }


        new Handler().postDelayed(() -> {
            startActivity(new Intent(Tela_fase3.this, Tela_Atv_1_fase3.class));
            finish();
        }, DELAY_MILLIS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (audio != null) {
            audio.release();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Use a seta do app para voltar!", Toast.LENGTH_SHORT).show();
    }
}
