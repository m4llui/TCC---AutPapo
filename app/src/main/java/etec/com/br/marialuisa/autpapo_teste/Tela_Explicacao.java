package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.HashMap;

public class Tela_Explicacao extends AppCompatActivity {

    private ScrollView scrollView;
    private HashMap<Integer, Integer> viewAudioMap;
    private MediaPlayer mediaPlayer;
    private View view1, view2, view3, view4, view5;
    private TextView textViewMessage;
    private ImageView imageViewIcon;

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
        textViewMessage = findViewById(R.id.textViewMessage);
        imageViewIcon = findViewById(R.id.imageViewIcon);

        // Clique do botão CRIAR CONTA
        btCriarConta.setOnClickListener(view -> {
            Intent intent = new Intent(Tela_Explicacao.this, Tela_CriarConta.class);
            startActivity(intent);
        });

        // Clique do botão LOGIN
        txLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Tela_Explicacao.this, Tela_Login.class);
            startActivity(intent);
        });

        // Clique do botão PULAR - IR PARA HOME
        txPular.setOnClickListener(view -> {
            Intent intent = new Intent(Tela_Explicacao.this, Tela_Home.class);
            startActivity(intent);
        });

        // Mapa para associar as Views aos áudios
        viewAudioMap = new HashMap<>();
        viewAudioMap.put(view1.getId(), R.raw.audio_exp1);
        viewAudioMap.put(view2.getId(), R.raw.audio_ex2);
        viewAudioMap.put(view3.getId(), R.raw.audio_ex3);
        viewAudioMap.put(view4.getId(), R.raw.audio_ex4);
        viewAudioMap.put(view5.getId(), -1); // view5 não deve ter áudio

        // Configura o OnClickListener para cada view
        setupClickListener(view1, R.raw.audio_exp1);
        setupClickListener(view2, R.raw.audio_ex2);
        setupClickListener(view3, R.raw.audio_ex3);
        setupClickListener(view4, R.raw.audio_ex4);

        // Exibe a mensagem e o ícone apenas na view1
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (isViewVisible(view1)) {
                textViewMessage.setVisibility(View.VISIBLE);
                imageViewIcon.setVisibility(View.VISIBLE);
            } else {
                textViewMessage.setVisibility(View.GONE);
                imageViewIcon.setVisibility(View.GONE);
            }
        });
    }

    // Configura o OnClickListener para uma view específica
    private void setupClickListener(View view, int audioResourceId) {
        view.setOnClickListener(v -> {
            stopAllAudio();
            playAudio(audioResourceId);
        });
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
        return visible;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAllAudio(); // Garantir que o áudio é parado ao destruir a atividade
    }
}
