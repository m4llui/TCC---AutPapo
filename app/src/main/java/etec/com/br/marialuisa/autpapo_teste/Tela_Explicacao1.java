package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.HashMap;

public class Tela_Explicacao1 extends AppCompatActivity {

    private ScrollView scrollView;
    private HashMap<Integer, Integer> viewAudioMap;
    private MediaPlayer mediaPlayer;
    private View view1, view2, view3, view4, view5;

    Button btCriarConta;
    TextView txLogin, txPular;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_explicacao1);

        btCriarConta = findViewById(R.id.btnCriarConta);
        txLogin = findViewById(R.id.txtLogin);
        txPular = findViewById(R.id.txtPular);
        scrollView = findViewById(R.id.scrollView);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);

        // Clique do botão CRIAR CONTA
        btCriarConta.setOnClickListener(view -> {
            Intent intent = new Intent(Tela_Explicacao1.this, Tela_CriarConta.class);
            startActivity(intent);
        });

        // Clique do botão LOGIN
        txLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Tela_Explicacao1.this, Tela_Login.class);
            startActivity(intent);
        });

        // Clique do botão PULAR - IR PARA HOME
        txPular.setOnClickListener(view -> {
            Intent intent = new Intent(Tela_Explicacao1.this, Tela_Home.class);
            startActivity(intent);
        });

        // Mapa para associar as Views aos áudios
        viewAudioMap = new HashMap<>();
        viewAudioMap.put(view1.getId(), R.raw.audio_exp1);
        viewAudioMap.put(view2.getId(), R.raw.audio_ex2);
        viewAudioMap.put(view3.getId(), R.raw.audio_ex3);
        viewAudioMap.put(view4.getId(), R.raw.audio_ex4);
        viewAudioMap.put(view5.getId(), -1); // view5 não deve ter áudio

        // Configura o Listener de Scroll com lambda
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            stopAllAudio();
            checkAndPlayAudio(view1);
            checkAndPlayAudio(view2);
            checkAndPlayAudio(view3);
            checkAndPlayAudio(view4);
            // Não chama checkAndPlayAudio para view5, pois view5 não deve ter áudio
        });

        // Configura o OnClickListener para view1 com lambda
        view1.setOnClickListener(v -> {
            stopAllAudio();
            playAudio(R.raw.audio_exp1);
        });
    }

    // Método para verificar se a View está visível e tocar o áudio
    private void checkAndPlayAudio(View view) {
        if (isViewVisible(view)) {
            Integer audioResourceId = viewAudioMap.get(view.getId());
            Log.d("CheckAndPlayAudio", "Checking view ID: " + view.getId() + " with audio resource: " + audioResourceId);
            if (audioResourceId != null && audioResourceId != -1) {
                playAudio(audioResourceId);
            }
        }
    }

    // Método para tocar o áudio
    private void playAudio(int audioResourceId) {
        Log.d("AudioPlayback", "Playing audio: " + audioResourceId);
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioResourceId);
        mediaPlayer.start();
    }

    // Método para parar todos os áudios
    private void stopAllAudio() {
        Log.d("AudioPlayback", "Stopping all audio");
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Método para verificar se a View está visível no ScrollView
    private boolean isViewVisible(View view) {
        android.graphics.Rect rect = new android.graphics.Rect();
        boolean visible = view.getGlobalVisibleRect(rect) &&
                rect.top <= scrollView.getHeight() &&
                rect.bottom >= 0;
        Log.d("VisibilityCheck", "View ID: " + view.getId() +
                " Top: " + rect.top + " Bottom: " + rect.bottom +
                " ScrollView Height: " + scrollView.getHeight() +
                " Visible: " + visible);
        return visible;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAllAudio(); // Garantir que o áudio é parado ao destruir a atividade
    }
}
