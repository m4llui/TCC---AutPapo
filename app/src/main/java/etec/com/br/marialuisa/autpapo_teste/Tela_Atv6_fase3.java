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

public class Tela_Atv6_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    //atualizando
    private boolean buttonSelected = false;
    TextView btEnunciado;
    private ImageView btFeliz, btFelizCerto, btTriste, btTristeErrado, btCansado, btCansadoErrado,
            btRaiva, btRaivaErrado, btVolta, btBalao, notCerto, notErro;
    private Handler handler = new Handler();

    private int codCrianca;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv6_fase3);

        btEnunciado = findViewById(R.id.txtEnunEmocao6);
        btBalao = findViewById(R.id.balao_feliz);
        btFeliz = findViewById(R.id.btn_feliz);
        btFelizCerto = findViewById(R.id.btn_feliz_certa);
        btTriste = findViewById(R.id.btn_triste);
        btTristeErrado = findViewById(R.id.btn_triste_errada);
        btCansado = findViewById(R.id.btn_cansado);
        btCansadoErrado = findViewById(R.id.btn_cansado_errada);
        btRaiva = findViewById(R.id.btn_raiva);
        btRaivaErrado = findViewById(R.id.btn_raiva_errada);
        btVolta = findViewById(R.id.btnVoltarAtv6Fase3);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.enun_emocao);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.feliz);
            }
        });

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.enun_emocao);
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
                Intent abrirHome =  new Intent(Tela_Atv6_fase3.this, Tela_Home.class);
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
        btFelizCerto.setVisibility(View.INVISIBLE);
        btTristeErrado.setVisibility(View.INVISIBLE);
        btCansadoErrado.setVisibility(View.INVISIBLE);
        btRaivaErrado.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btFelizCerto.setEnabled(false);
        btTristeErrado.setEnabled(false);
        btCansadoErrado.setEnabled(false);
        btRaivaErrado.setEnabled(false);
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

                if (id == R.id.btn_feliz) {
                    btFelizCerto.setVisibility(View.VISIBLE);
                    btFelizCerto.setEnabled(true);
                    playAudio(R.raw.feliz);

                } else if (id == R.id.btn_triste) {
                    btTristeErrado.setVisibility(View.VISIBLE);
                    btTristeErrado.setEnabled(true);
                    playAudio(R.raw.triste);

                } else if (id == R.id.btn_cansado) {
                    btCansadoErrado.setVisibility(View.VISIBLE);
                    btCansadoErrado.setEnabled(true);
                    playAudio(R.raw.cansado);

                } else if (id == R.id.btn_raiva) {
                    btRaivaErrado.setVisibility(View.VISIBLE);
                    btRaivaErrado.setEnabled(true);
                    playAudio(R.raw.raiva);

                }

                if(id==R.id.btn_feliz){
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
                        Intent intent = new Intent(Tela_Atv6_fase3.this, Tela_Atv7_fase3.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        };

        // Configura o mesmo listener para todos os botões
        btFeliz.setOnClickListener(listener);
        btTriste.setOnClickListener(listener);
        btCansado.setOnClickListener(listener);
        btRaiva.setOnClickListener(listener);
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