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

public class Tela_Atv5_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    TextView btEnunciado;
    private ImageView btNao, btNaoCerto, btKpop, btKpopErrado, btNavio, btNavioErrado,
            btViola, btViolaErrado, btVolta, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv5_fase3);

        btEnunciado = findViewById(R.id.txtEnunFigura);
        btBalao = findViewById(R.id.balao_nao);
        btNao = findViewById(R.id.btn_joia_nao);
        btNaoCerto = findViewById(R.id.btn_joia_nao_certa);
        btKpop = findViewById(R.id.btn_kpop);
        btKpopErrado = findViewById(R.id.btn_kpop_errada);
        btNavio = findViewById(R.id.btn_navio);
        btNavioErrado = findViewById(R.id.btn_navio_errada);
        btViola = findViewById(R.id.btn_viola);
        btViolaErrado = findViewById(R.id.btn_viola_errada);
        btVolta = findViewById(R.id.btnVoltarAtv5Fase3);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.enun_escolha_figplvra);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.nao);
            }
        });

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.enun_escolha_figplvra);
            }
        });

        //BOTAO VOLTAR
        btVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv5_fase3.this, Tela_Home.class);
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
        btNaoCerto.setVisibility(View.INVISIBLE);
        btNavioErrado.setVisibility(View.INVISIBLE);
        btKpopErrado.setVisibility(View.INVISIBLE);
        btViolaErrado.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btNaoCerto.setEnabled(false);
        btNavioErrado.setEnabled(false);
        btKpopErrado.setEnabled(false);
        btViolaErrado.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_joia_nao) {
                    btNaoCerto.setVisibility(View.VISIBLE);
                    btNaoCerto.setEnabled(true);

                } else if (id == R.id.btn_navio) {
                    btNavioErrado.setVisibility(View.VISIBLE);
                    btNavioErrado.setEnabled(true);

                } else if (id == R.id.btn_kpop) {
                    btKpopErrado.setVisibility(View.VISIBLE);
                    btKpopErrado.setEnabled(true);

                } else if (id == R.id.btn_viola) {
                    btViolaErrado.setVisibility(View.VISIBLE);
                    btViolaErrado.setEnabled(true);

                }

                // HANDLER É QUEM FAZ O ATRASO ANTES DE IR PARA A PRÓXIMA TELA
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv5_fase3.this, Tela_Atv6_fase3.class));
                        finish(); // Fecha a tela atual
                    }
                }, 2000); // Atraso de 2 segundos em milissegundos
            }
        };

        // Configura o mesmo listener para todos os botões
        btNao.setOnClickListener(listener);
        btNavio.setOnClickListener(listener);
        btKpop.setOnClickListener(listener);
        btViola.setOnClickListener(listener);
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

    //BLOQUEIO DO BOTÃO VOLTAR DO CELULAR
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
    }
}