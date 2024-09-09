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

public class Tela_Atv2_fase3 extends AppCompatActivity {


    //AINDA TÁ DANDO ERRADO ESSA CARALHA -- ARRUMAR
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btI, btO, btOi, btIErrado, btOErrado, btICerto, btOCerto, btVolta, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase3);

        btBalao = findViewById(R.id.balao_enun);
        btI = findViewById(R.id.btn_letraI);
        btO = findViewById(R.id.btn_letraO);
        btICerto = findViewById(R.id.btn_letraI_certa);
        btIErrado = findViewById(R.id.btn_letraI_errada);
        btOCerto = findViewById(R.id.btn_letraO_certa);
        btOErrado = findViewById(R.id.btn_letraO_errada);
        btVolta = findViewById(R.id.btnVoltarAtv6Fase3);
        btOi = findViewById(R.id.imgOi);

        //ENUNCIADO AUTOMÁTICO
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.monte_palavra);
            }
        }, 1000); // Atraso de 1 segundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.monte_palavra);
            }
        });

        btOi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.oi);
            }
        });

        //BOTAO VOLTAR
        btVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv2_fase3.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });



        // Inicializa os botões desativados e invisíveis
        botoesInativados();

        // Configura os OnClickListeners
        setOnClickListeners();
    }

    private void botoesInativados() {
        // Inicializa os botões "certo" e "errado" como invisíveis
        btICerto.setVisibility(View.INVISIBLE);
        btIErrado.setVisibility(View.INVISIBLE);
        btOErrado.setVisibility(View.INVISIBLE);
        btOCerto.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btICerto.setEnabled(false);
        btIErrado.setEnabled(false);
        btOErrado.setEnabled(false);
        btOCerto.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_letraO) {
                    if (!selecionouI) {
                        selecionouO = true;
                        btOCerto.setVisibility(View.VISIBLE);
                        btOCerto.setEnabled(true);
                        playAudio(R.raw.letra_o);
                    } else {
                        btOErrado.setVisibility(View.VISIBLE);
                        btOErrado.setEnabled(true);
                        playAudio(R.raw.letra_o);
                    }
                } else if (id == R.id.btn_letraI) {
                    if (selecionouO) {
                        selecionouI = true;
                        btICerto.setVisibility(View.VISIBLE);
                        btICerto.setEnabled(true);
                        btI.setVisibility(View.INVISIBLE);
                        btI.setEnabled(false);
                        playAudio(R.raw.letra_i);
                    } else {
                        selecionouI = true;
                        btIErrado.setVisibility(View.VISIBLE);
                        btIErrado.setEnabled(true);
                        playAudio(R.raw.letra_i);
                    }
                }

                // Sempre vai para a próxima tela, independentemente das seleções
                if ((selecionouO && selecionouI) || (btIErrado.getVisibility() == View.VISIBLE && btOErrado.getVisibility() == View.VISIBLE)) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Tela_Atv2_fase3.this, Tela_Atv3_fase3.class));
                            finish();
                        }
                    }, 2000); // Atraso de 2 segundos
                }
            }
        };

        btI.setOnClickListener(listener);
        btO.setOnClickListener(listener);
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

    //BLOQUEIO DO BOTÃO VOLTAR DO CELULAR
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
    }
}
