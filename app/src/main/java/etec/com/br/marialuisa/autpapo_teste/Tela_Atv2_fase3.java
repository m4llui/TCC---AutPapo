package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

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

        // Leitura do Enunciado automático
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
                    if (!selecionouO) {
                        // Botão 'O' clicado
                        selecionouO = true;
                        btOCerto.setVisibility(View.VISIBLE);
                        btOCerto.setEnabled(true);
                        playAudio(R.raw.letra_o);

                        if (erroI) {
                            // Caso o 'I' tenha sido clicado primeiro e gerado erro
                            btIErrado.setVisibility(View.INVISIBLE);
                            erroI = false;
                        }
                    }

                } else if (id == R.id.btn_letraI) {
                    if (!selecionouI) {
                        // Botão 'I' clicado
                        selecionouI = true;
                        btICerto.setVisibility(View.VISIBLE);
                        btICerto.setEnabled(true);
                        playAudio(R.raw.letra_i);

                        if (erroO) {
                            // Caso o 'O' tenha sido clicado primeiro e gerado erro
                            btOErrado.setVisibility(View.INVISIBLE);
                            erroO = false;
                        }
                    }
                }

                // Verifica se ambos os botões foram selecionados e realiza a transição
                if (selecionouO && selecionouI) {
                    // Transição para a próxima tela
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Tela_Atv2_fase3.this, Tela_Atv3_fase3.class));
                            finish(); // Fecha a tela atual
                        }
                    }, 2000); // Atraso de 2 segundos

                } else if (selecionouO && !selecionouI) {
                    // Ordem incorreta: O clicado sem I
                    erroI = true;
                    btIErrado.setVisibility(View.VISIBLE);
                    btIErrado.setEnabled(true);

                } else if (selecionouI && !selecionouO) {
                    // Ordem incorreta: I clicado sem O
                    erroO = true;
                    btOErrado.setVisibility(View.VISIBLE);
                    btOErrado.setEnabled(true);
                }
            }
        };

        // Configura o mesmo listener para todos os botões
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
}
