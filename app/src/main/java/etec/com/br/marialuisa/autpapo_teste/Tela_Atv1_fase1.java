package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv1_fase1 extends AppCompatActivity {

    private ImageView btn_Let_A, btn_Let_A_Certo, btn_Let_B, btn_Let_B_Inc, btn_Let_C, btn_Let_C_Inc,
            btn_Let_D, btn_Let_D_Inc, btVoltar2, btEnunciado, btBalao;
    private Handler handler = new Handler();
    private MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv1_fase1);

        // Referenciando todos os botões
        btn_Let_A = findViewById(R.id.btn_Let_A);
        btn_Let_A_Certo = findViewById(R.id.btn_Let_A_Certo);
        btn_Let_B = findViewById(R.id.btn_Let_B);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_B_Inc);
        btn_Let_C = findViewById(R.id.btn_Let_C);
        btn_Let_C_Inc = findViewById(R.id.btn_Let_C_Inc);
        btn_Let_D = findViewById(R.id.btn_Let_D);
        btn_Let_D_Inc = findViewById(R.id.btn_Let_D_Inc);
        btVoltar2 = findViewById(R.id.btnVoltar2);
        btEnunciado = findViewById(R.id.imageEnunciado1);
        btBalao = findViewById(R.id.imageBalao);

        // Inicializa os botões desativados e invisíveis
        botoesInativados();

        // Configura os OnClickListeners
        setOnClickListeners();
    }

    private void botoesInativados() {
        // Inicializa os botões "certo" e "errado" como invisíveis
        btn_Let_A_Certo.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_C_Inc.setVisibility(View.INVISIBLE);
        btn_Let_D_Inc.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btn_Let_A_Certo.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_C_Inc.setEnabled(false);
        btn_Let_D_Inc.setEnabled(false);
    }

    private void setOnClickListeners() {
        btn_Let_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Let_A_Certo.setVisibility(View.VISIBLE);
                btn_Let_A_Certo.setEnabled(true);
                playAudio(R.raw.audio_pfv);
            }
        });

        btn_Let_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Let_B_Inc.setVisibility(View.VISIBLE);
                btn_Let_B_Inc.setEnabled(true);
                playAudio(R.raw.carro);
            }
        });

        btn_Let_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Let_C_Inc.setVisibility(View.VISIBLE);
                btn_Let_C_Inc.setEnabled(true);
                playAudio(R.raw.morango);
            }
        });

        btn_Let_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Let_D_Inc.setVisibility(View.VISIBLE);
                btn_Let_D_Inc.setEnabled(true);
                playAudio(R.raw.casa);
            }
        });
    }

    private void playAudio(int audioResId) {
        if (audio != null) {
            audio.release(); // Libera o recurso de qualquer áudio anterior
        }
        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }

    // Destroi o audio após a atividade

        }


