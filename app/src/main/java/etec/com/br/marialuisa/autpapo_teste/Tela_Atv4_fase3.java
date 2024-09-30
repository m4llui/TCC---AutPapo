package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv4_fase3 extends AppCompatActivity {

    private boolean buttonSelected = false;
    MediaPlayer audio;
    TextView btEnunciado;
    private ImageView btSim, btSimCerto, btCoracao, btCoracaoErrado, btMorango, btMorangoErrado,
            btNao, btNaoErrado, btVolta, btBalao,notCerto, notErro;
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
        btNao = findViewById(R.id.btn_nao_joia);
        btNaoErrado = findViewById(R.id.btn_nao_joia_errado);
        btVolta = findViewById(R.id.btnVoltarAtv4Fase3);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

        // Toca o áudio uma vez quando a Activity é carregada ESTÁ DUPLICANDO ESTE AUDIO
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
                playAudio(R.raw.sim);
            }
        });

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.enun_escolha_figplvra);
            }
        });

        //BOTAO VOLTAR
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
                Intent abrirHome =  new Intent(Tela_Atv4_fase3.this, Tela_Home.class);
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
        btSimCerto.setVisibility(View.INVISIBLE);
        btCoracaoErrado.setVisibility(View.INVISIBLE);
        btMorangoErrado.setVisibility(View.INVISIBLE);
        btNaoErrado.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btSimCerto.setEnabled(false);
        btCoracaoErrado.setEnabled(false);
        btMorangoErrado.setEnabled(false);
        btNaoErrado.setEnabled(false);
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

                if (id == R.id.btn_sim_joia) {
                    btSimCerto.setVisibility(View.VISIBLE);
                    btSimCerto.setEnabled(true);
                    playAudio(R.raw.sim);

                } else if (id == R.id.btn_coracao) {
                    btCoracaoErrado.setVisibility(View.VISIBLE);
                    btCoracaoErrado.setEnabled(true);
                    playAudio(R.raw.coracao);

                } else if (id == R.id.btn_morango4) {
                    btMorangoErrado.setVisibility(View.VISIBLE);
                    btMorangoErrado.setEnabled(true);
                    playAudio(R.raw.morango);

                } else if (id == R.id.btn_nao_joia) {
                    btNaoErrado.setVisibility(View.VISIBLE);
                    btNaoErrado.setEnabled(true);
                    playAudio(R.raw.nao);

                }
                if(id==R.id.btn_sim_joia){
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
                        startActivity(new Intent(Tela_Atv4_fase3.this, Tela_Atv5_fase3.class));
                        finish(); // Fecha a tela atual
                    }
                }, 3000); // Atraso de 3 segundos em milissegundos
            }
        };

        // Configura o mesmo listener para todos os botões
        btMorango.setOnClickListener(listener);
        btNao.setOnClickListener(listener);
        btSim.setOnClickListener(listener);
        btCoracao.setOnClickListener(listener);
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