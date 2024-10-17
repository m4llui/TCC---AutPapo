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

public class Tela_Atv7_fase3 extends AppCompatActivity {


    MediaPlayer audio;
    //atualizando
    TextView btEnunciado;
    private boolean buttonSelected = false;
    private ImageView btTriste, btTristeCerto, btFeliz, btFelizErrado, btCansado, btCansadoErrado,
            btRaiva, btRaivaErrado, btVolta, btBalao, notCerto, notErro;
    private Handler handler = new Handler();
    private int codCrianca;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv7_fase3);

        btEnunciado = findViewById(R.id.txtEnunEmocao);
        btBalao = findViewById(R.id.balao_triste);
        btFeliz = findViewById(R.id.btn_feliz7);
        btFelizErrado = findViewById(R.id.btn_feliz7_errada);
        btTriste = findViewById(R.id.btn_triste7);
        btTristeCerto = findViewById(R.id.btn_triste7_certa);
        btCansado = findViewById(R.id.btn_cansado7);
        btCansadoErrado = findViewById(R.id.btn_cansado7_errada);
        btRaiva = findViewById(R.id.btn_raiva7);
        btRaivaErrado = findViewById(R.id.btn_raiva7_errada);
        btVolta = findViewById(R.id.btnVoltarAtv7Fase3);
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

                playAudio(R.raw.triste);
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
            //Função p/ fazer oa udio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv7_fase3.this, Tela_Home.class);
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
        btTristeCerto.setVisibility(View.INVISIBLE);
        btFelizErrado.setVisibility(View.INVISIBLE);
        btCansadoErrado.setVisibility(View.INVISIBLE);
        btRaivaErrado.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btTristeCerto.setEnabled(false);
        btFelizErrado.setEnabled(false);
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

                if (id == R.id.btn_triste7) {
                    btTristeCerto.setVisibility(View.VISIBLE);
                    btTristeCerto.setEnabled(true);
                    playAudio(R.raw.triste);

                } else if (id == R.id.btn_feliz7) {
                    btFelizErrado.setVisibility(View.VISIBLE);
                    btFelizErrado.setEnabled(true);
                    playAudio(R.raw.feliz);

                } else if (id == R.id.btn_cansado7) {
                    btCansadoErrado.setVisibility(View.VISIBLE);
                    btCansadoErrado.setEnabled(true);
                    playAudio(R.raw.cansado);

                } else if (id == R.id.btn_raiva7) {
                    btRaivaErrado.setVisibility(View.VISIBLE);
                    btRaivaErrado.setEnabled(true);
                    playAudio(R.raw.raiva);
                }
                if(id==R.id.btn_triste7){
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
                        Intent intent = new Intent(Tela_Atv7_fase3.this, Tela_Video_fase3.class);
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