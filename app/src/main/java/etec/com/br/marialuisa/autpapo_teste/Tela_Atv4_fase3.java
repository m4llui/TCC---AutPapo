package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class Tela_Atv4_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    TextView btEnunciado;
    private ImageView btSim, btSimCerto, btCoracao, btCoracaoErrado, btMorango, btMorangoErrado,
            btMaos, btMaosErrado, btVolta, btBalao;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv4_fase3);

        //REFERENCIANDO
        btEnunciado = findViewById(R.id.txtEnunImg);
        btBalao = findViewById(R.id.balao_sim);
        btSim = findViewById(R.id.btn_sim_joia);
        btSimCerto = findViewById(R.id.btn_sim_joia_certo);
        btCoracao = findViewById(R.id.btn_coracao);
        btCoracaoErrado = findViewById(R.id.btn_coracao_errada);
        btMorango = findViewById(R.id.btn_morango4);
        btMorangoErrado = findViewById(R.id.btn_morango4_errado);
        btMaos = findViewById(R.id.btn_maos);
        btMaosErrado = findViewById(R.id.btn_maos_errada);
        btVolta = findViewById(R.id.btnVoltarAtv4Fase3);

        // Toca o áudio uma vez quando a Activity é carregada ESTÁ DUPLICANDO ESTE AUDIO
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.enun_escolha_img);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

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
}