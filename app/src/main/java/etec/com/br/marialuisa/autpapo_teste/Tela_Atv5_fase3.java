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
    private boolean buttonSelected = false;
    TextView btEnunciado;
    private ImageView btNao, btNaoCerto, btDois, btDoisErrado, btNavio, btNavioErrado,
            btViola, btViolaErrado, btVolta, btBalao, notCerto, notErro;
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
        btDois = findViewById(R.id.btn_dois);
        btDoisErrado = findViewById(R.id.btn_dois_errada);
        btNavio = findViewById(R.id.btn_navio);
        btNavioErrado = findViewById(R.id.btn_navio_errada);
        btViola = findViewById(R.id.btn_viola);
        btViolaErrado = findViewById(R.id.btn_viola_errada);
        btVolta = findViewById(R.id.btnVoltarAtv5Fase3);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

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

        //Botão para voltar para a home
        btVolta.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer o audio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv5_fase3.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
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
        btDoisErrado.setVisibility(View.INVISIBLE);
        btViolaErrado.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btNaoCerto.setEnabled(false);
        btNavioErrado.setEnabled(false);
        btDoisErrado.setEnabled(false);
        btViolaErrado.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonSelected) {
                    //PARA SELECIONAR SÓ UM BOTÃO
                    return;
                }
                int id = view.getId();
                buttonSelected = true;

                if (id == R.id.btn_joia_nao) {
                    btNaoCerto.setVisibility(View.VISIBLE);
                    btNaoCerto.setEnabled(true);
                    playAudio(R.raw.nao);

                } else if (id == R.id.btn_navio) {
                    btNavioErrado.setVisibility(View.VISIBLE);
                    btNavioErrado.setEnabled(true);
                    playAudio(R.raw.navio);

                } else if (id == R.id.btn_dois) {
                    btDoisErrado.setVisibility(View.VISIBLE);
                    btDoisErrado.setEnabled(true);
                    playAudio(R.raw.dois);

                } else if (id == R.id.btn_viola) {
                    btViolaErrado.setVisibility(View.VISIBLE);
                    btViolaErrado.setEnabled(true);
                    playAudio(R.raw.violao);

                }

                if(id==R.id.btn_joia_nao){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notCerto.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_acertou);
                        }
                    }, 1100);
                }else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notErro.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_erro);
                        }
                    }, 1100);
                }


                // HANDLER É QUEM FAZ O ATRASO ANTES DE IR PARA A PRÓXIMA TELA
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv5_fase3.this, Tela_Atv6_fase3.class));
                        finish(); // Fecha a tela atual
                    }
                }, 3000);
            }
        };

        // Configura o mesmo listener para todos os botões
        btNao.setOnClickListener(listener);
        btNavio.setOnClickListener(listener);
        btDois.setOnClickListener(listener);
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