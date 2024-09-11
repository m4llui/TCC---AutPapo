package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv2_fase1 extends AppCompatActivity {
    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_Let_A, btn_Let_A_Inc, btn_Let_B, btn_Let_B_Inc, btn_Let_E, btn_Let_E_Certo,
            btn_Let_Y, btn_Let_Y_Inc, btVoltar3, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase1);

        btn_Let_A = findViewById(R.id.btn_Let_A2);
        btn_Let_A_Inc = findViewById(R.id.btn_Let_A_errado2);
        btn_Let_B = findViewById(R.id.btn_Let_B2);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_B_errado);
        btn_Let_E = findViewById(R.id.btn_Let_E);
        btn_Let_E_Certo = findViewById(R.id.btn_Let_E_certo);
        btn_Let_Y = findViewById(R.id.btn_Let_Y);
        btn_Let_Y_Inc = findViewById(R.id.btn_Let_y_errado);
        btVoltar3 = findViewById(R.id.btnVoltarAtv2Fase1);
        btEnunciado = findViewById(R.id.txtEnunAtv2Fase2);
        btBalao = findViewById(R.id.ImageBalao_Atv2_fase1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.qual_letraessa);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.qual_letraessa);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.letra_e);
            }
        });

        btVoltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv2_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });


        botoesInativados();


        setOnClickListeners();
    }
    private void botoesInativados() {
        btn_Let_A_Inc.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_E_Certo.setVisibility(View.INVISIBLE);
        btn_Let_Y_Inc.setVisibility(View.INVISIBLE);

        btn_Let_A_Inc.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_E_Certo.setEnabled(false);
        btn_Let_Y_Inc.setEnabled(false);
    }

    // Método para definir os listeners dos botões
    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;


                int id = view.getId();

                if (id == R.id.btn_Let_A2) {
                    btn_Let_A_Inc.setVisibility(View.VISIBLE);
                    btn_Let_A_Inc.setEnabled(true);
                    playAudio(R.raw.letra_a);

                } else if (id == R.id.btn_Let_B2) {
                    btn_Let_B_Inc.setVisibility(View.VISIBLE);
                    btn_Let_B_Inc.setEnabled(true);
                    playAudio(R.raw.letra_b);

                } else if (id == R.id.btn_Let_E) {
                    btn_Let_E_Certo.setVisibility(View.VISIBLE);
                    btn_Let_E_Certo.setEnabled(true);
                    playAudio(R.raw.letra_e);

                } else if (id == R.id.btn_Let_Y) {
                    btn_Let_Y_Inc.setVisibility(View.VISIBLE);
                    btn_Let_Y_Inc.setEnabled(true);
                    playAudio(R.raw.letra_y);
                }


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv2_fase1.this, Tela_Atv3_fase1.class));
                        finish();
                    }
                }, 2000);
            }
        };


        btn_Let_A.setOnClickListener(listener);
        btn_Let_B.setOnClickListener(listener);
        btn_Let_E.setOnClickListener(listener);
        btn_Let_Y.setOnClickListener(listener);
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
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
    }
}

