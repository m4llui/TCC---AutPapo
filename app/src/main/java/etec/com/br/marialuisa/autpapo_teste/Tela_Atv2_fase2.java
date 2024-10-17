package etec.com.br.marialuisa.autpapo_teste;

import static etec.com.br.marialuisa.autpapo_teste.R.id.btnVoltarAtv1Fase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv2_fase2 extends AppCompatActivity {

    private boolean buttonSelected = false;
    //atualizando
    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_Let_L, btn_Let_L_Inc, btn_Let_I, btn_Let_I_Inc,
            btn_Let_M, btn_Let_M_Inc, btn_Let_G, btn_Let_G_Certo, btVoltar2, btBalao,
            notCerto, notErro;
    private Handler handler = new Handler();

    private int codCrianca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase2);

        // Vincular as views do XML com o código
        btn_Let_L = findViewById(R.id.btn_Let_L1);
        btn_Let_L_Inc = findViewById(R.id.btn_Let_L_errado2);
        btn_Let_I = findViewById(R.id.btn_Let_I2);
        btn_Let_I_Inc = findViewById(R.id.btn_Let_B_errado2);
        btn_Let_M = findViewById(R.id.btn_Let_M2);
        btn_Let_M_Inc = findViewById(R.id.btn_Let_M_errado2);
        btn_Let_G = findViewById(R.id.btn_Let_G2);
        btn_Let_G_Certo = findViewById(R.id.btn_Let_G_certo2);
        btVoltar2 = findViewById(R.id.btnVoltarAtv2Fase2);
        btEnunciado = findViewById(R.id.txtEnunAtv2Fase2);
        btBalao = findViewById(R.id.ImageBalao_Atv2_fase1);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);


        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.animal_letra);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.animal_letra);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.gato);
            }
        });

        //Botão para voltar para a home
        btVoltar2.setOnClickListener(new View.OnClickListener() {
            //Função p/ fazer o audio para quando sair da atividade
            @Override
            public void onClick(View view) {
                if (audio != null && audio.isPlaying()) {
                    audio.stop();
                    audio.release();
                    audio = null;
                }
                Intent abrirHome =  new Intent(Tela_Atv2_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });

        botoesInativados();


        setOnClickListeners();
    }

    // Método para esconder os botões de respostas até que o usuário clique em uma letra
    private void botoesInativados() {
        btn_Let_L_Inc.setVisibility(View.INVISIBLE);
        btn_Let_I_Inc.setVisibility(View.INVISIBLE);
        btn_Let_M_Inc.setVisibility(View.INVISIBLE);
        btn_Let_G_Certo.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        btn_Let_L_Inc.setEnabled(false);
        btn_Let_I_Inc.setEnabled(false);
        btn_Let_M_Inc.setEnabled(false);
        btn_Let_G_Certo.setEnabled(false);
    }

    // Configurar os ouvintes de clique para os botões de letras
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
                // Verificar qual letra foi clicada e exibir a resposta correspondente
                if (id == R.id.btn_Let_L1) {
                    btn_Let_L_Inc.setVisibility(View.VISIBLE);
                    btn_Let_L_Inc.setEnabled(true);
                    playAudio(R.raw.letra_l);

                } else if (id == R.id.btn_Let_I2) {
                    btn_Let_I_Inc.setVisibility(View.VISIBLE);
                    btn_Let_I_Inc.setEnabled(true);
                    playAudio(R.raw.letra_i);

                } else if (id == R.id.btn_Let_M2) {
                    btn_Let_M_Inc.setVisibility(View.VISIBLE);
                    btn_Let_M_Inc.setEnabled(true);
                    playAudio(R.raw.letra_m);

                } else if (id == R.id.btn_Let_G2) {
                    btn_Let_G_Certo.setVisibility(View.VISIBLE);
                    btn_Let_G_Certo.setEnabled(true);
                    playAudio(R.raw.letra_g);
                }
                if(id==R.id.btn_Let_G2){
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

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Tela_Atv2_fase2.this, Tela_Atv3_fase2.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        };

        // Associar os ouvintes aos botões
        btn_Let_L.setOnClickListener(listener);
        btn_Let_I.setOnClickListener(listener);
        btn_Let_M.setOnClickListener(listener);
        btn_Let_G.setOnClickListener(listener);
    }


    private void playAudio(int audioResId) {

        if (audio != null) {
            audio.release();
        }

        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
    }
}
