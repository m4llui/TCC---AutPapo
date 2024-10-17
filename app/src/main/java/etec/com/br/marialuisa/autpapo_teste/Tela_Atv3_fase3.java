package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tela_Atv3_fase3 extends AppCompatActivity {
    MediaPlayer audio;
    //atualizando
    private int codCrianca;

    private ImageView btNao, btNaoErrado, btSim, btSimErrado, btTchau, btTchauCerto,
            btCasa, btCasaErrado, btVolta, btBalao, notCerto,notErro;
    private boolean buttonSelected = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv3_fase3);

        btNao = findViewById(R.id.btn_nao);
        btNaoErrado = findViewById(R.id.btn_nao_errada);
        btSim = findViewById(R.id.btn_sim);
        btSimErrado = findViewById(R.id.btn_sim_errada);
        btTchau = findViewById(R.id.btn_tchau);
        btTchauCerto = findViewById(R.id.btn_tchau_certa);
        btCasa = findViewById(R.id.btn_casa3);
        btCasaErrado = findViewById(R.id.btn_casa_errada);
        btVolta = findViewById(R.id.btnVoltarAtv6Fase3);
        btBalao = findViewById(R.id.balao_enun_tchau);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);


        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();

        // Toca o áudio uma vez quando a Activity é carregada ESTÁ DUPLICANDO ESTE AUDIO
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.enun_tchau);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.enun_tchau);
            }
        });

          //BOTAO VOLTAR
        //Botão para voltar para a home
        btVolta.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer oa udio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv3_fase3.this, Tela_Home.class);
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
        btTchauCerto.setVisibility(View.INVISIBLE);
        btNaoErrado.setVisibility(View.INVISIBLE);
        btSimErrado.setVisibility(View.INVISIBLE);
        btCasaErrado.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btTchauCerto.setEnabled(false);
        btNaoErrado.setEnabled(false);
        btSimErrado.setEnabled(false);
        btCasaErrado.setEnabled(false);
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

                if (id == R.id.btn_tchau) {
                    btTchauCerto.setVisibility(View.VISIBLE);
                    btTchauCerto.setEnabled(true);
                    playAudio(R.raw.tchau);
                } else if (id == R.id.btn_nao) {
                    btNaoErrado.setVisibility(View.VISIBLE);
                    btNaoErrado.setEnabled(true);
                    playAudio(R.raw.nao);
                } else if (id == R.id.btn_sim) {
                    btSimErrado.setVisibility(View.VISIBLE);
                    btSimErrado.setEnabled(true);
                    playAudio(R.raw.sim);
                } else if (id == R.id.btn_casa3) {
                    btCasaErrado.setVisibility(View.VISIBLE);
                    btCasaErrado.setEnabled(true);
                    playAudio(R.raw.casa);
                }
                if(id==R.id.btn_tchau){
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
                        Intent intent = new Intent(Tela_Atv3_fase3.this, Tela_Atv4_fase3.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        };

        // Configura o mesmo listener para todos os botões
        btTchau.setOnClickListener(listener);
        btCasa.setOnClickListener(listener);
        btSim.setOnClickListener(listener);
        btNao.setOnClickListener(listener);
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
