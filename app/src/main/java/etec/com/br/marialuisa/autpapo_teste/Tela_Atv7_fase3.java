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
    TextView btEnunciado;
    private ImageView btTriste, btTristeCerto, btFeliz, btFelizErrado, btCansado, btCansadoErrado,
            btRaiva, btRaivaErrado, btVolta, btBalao;
    private Handler handler = new Handler();

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
                int id = view.getId();

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

                // HANDLER É QUEM FAZ O ATRASO ANTES DE IR PARA A PRÓXIMA TELA
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv7_fase3.this, Tela_Video_fase3.class));
                        finish(); // Fecha a tela atual - VER ERRO, NÃO FOI PARA A PROXÍMA TELA
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