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
    TextView btEnunciado;
    private ImageView btFeliz, btFelizCerto, btTriste, btTristeErrado, btCansado, btCansadoErrado,
            btRaiva, btRaivaErrado, btVolta, btBalao;
    private Handler handler = new Handler();

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

        btVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv6_fase3.this, tela_home_visitante.class);
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
        btFelizCerto.setVisibility(View.INVISIBLE);
        btTristeErrado.setVisibility(View.INVISIBLE);
        btCansadoErrado.setVisibility(View.INVISIBLE);
        btRaivaErrado.setVisibility(View.INVISIBLE);

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
                int id = view.getId();

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

                // HANDLER É QUEM FAZ O ATRASO ANTES DE IR PARA A PRÓXIMA TELA
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv6_fase3.this, Tela_Atv7_fase3.class));
                        finish(); // Fecha a tela atual
                    }
                }, 2000); // Atraso de 2 segundos em milissegundos
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