package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv_1_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    private ImageView btPfv, btPfvCerto, btCarro, btCarroErrado, btMorango, btMorangoErrado,
            btCasa, btCasaErrado, btVolta, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv1_fase3);

        // Referenciando todos os botões
        btPfv = findViewById(R.id.btn_pfv);
        btPfvCerto = findViewById(R.id.btn_pfv_certo);
        btCarro = findViewById(R.id.btn_carro);
        btCarroErrado = findViewById(R.id.btn_carro_errado);
        btMorango = findViewById(R.id.btn_morango);
        btMorangoErrado = findViewById(R.id.btn_morango_errado);
        btCasa = findViewById(R.id.btn_casa);
        btCasaErrado = findViewById(R.id.btn_casa_errado);
        btVolta = findViewById(R.id.btnVoltar);
        btEnunciado = findViewById(R.id.imgEnunciado);
        btBalao = findViewById(R.id.imgBalao);

        // Toca o áudio assim que a Activity é carregada com atraso (O HANDLER É PARA "ATRASO")
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playAudio(R.raw.enun_pedir);
            }
        }, 1000); // Atraso de 1 milissegundo

        // Configura o clique do botão Enunciado para tocar o áudio
        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.enun_pedir);
            }
        });

        // Configura o clique do botão Balao para tocar o áudio
        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.audio_pfv);
            }
        });

        // Inicializa os botões desativados e invisíveis
        botoesInativados();

        // Configura os OnClickListeners
        setOnClickListeners();
    }

    private void playAudio(int audioResId) {
        // Libere o MediaPlayer anterior, se houver
        if (audio != null) {
            audio.release();
        }
        // Cria um novo MediaPlayer e toca o áudio
        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }

    private void botoesInativados() {
        // Inicializa os botões "certo" e "errado" como invisíveis
        btPfvCerto.setVisibility(View.INVISIBLE);
        btCarroErrado.setVisibility(View.INVISIBLE);
        btMorangoErrado.setVisibility(View.INVISIBLE);
        btCasaErrado.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btPfvCerto.setEnabled(false);
        btCarroErrado.setEnabled(false);
        btMorangoErrado.setEnabled(false);
        btCasaErrado.setEnabled(false);
    }

    private void setOnClickListeners() {
        btPfv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btPfvCerto.setVisibility(View.VISIBLE);
                btPfvCerto.setEnabled(true);
                playAudio(R.raw.audio_pfv);
            }
        });

        btCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btCarroErrado.setVisibility(View.VISIBLE);
                btCarroErrado.setEnabled(true);
                playAudio(R.raw.carro);
            }
        });

        btMorango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btMorangoErrado.setVisibility(View.VISIBLE);
                btMorangoErrado.setEnabled(true);
                playAudio(R.raw.morango);
            }
        });

        btCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btCasaErrado.setVisibility(View.VISIBLE);
                btCasaErrado.setEnabled(true);
                playAudio(R.raw.casa);
            }
        });
    }
    //deu certo??? deu simmm

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (audio != null) {
            audio.release(); // Libere os recursos
            audio = null;
        }
    }
}
