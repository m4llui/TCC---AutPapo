package etec.com.br.marialuisa.autpapo_teste;

import static etec.com.br.marialuisa.autpapo_teste.R.id.not_acerto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv2_fase1 extends AppCompatActivity {

    private boolean buttonSelected = false;
    TextView btEnunciado;
    MediaPlayer audio;

    private ImageView btn_Let_A, btn_Let_A_Inc, btn_Let_B, btn_Let_B_Inc, btn_Let_E, btn_Let_E_Certo,
            btn_Let_Y, btn_Let_Y_Inc, btVoltar3, btBalao, notCerto, notErro;
    private Handler handler = new Handler();

    private int codCrianca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv2_fase1);


        Intent intent = getIntent();
        codCrianca = intent.getIntExtra("codCrianca", -1);
        Toast.makeText(this, "codCriança recebido: "+codCrianca, Toast.LENGTH_SHORT).show();


        btn_Let_A = findViewById(R.id.btn_Let_A1);
        btn_Let_A_Inc = findViewById(R.id.btn_Let_A1_errado);
        btn_Let_B = findViewById(R.id.btn_Let_B1);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_B1_errado);
        btn_Let_E = findViewById(R.id.btn_Let_E1);
        btn_Let_E_Certo = findViewById(R.id.btn_Let_E_certo1);
        btn_Let_Y = findViewById(R.id.btn_Let_Y1);
        btn_Let_Y_Inc = findViewById(R.id.btn_Let_Y_errado1);
        btVoltar3 = findViewById(R.id.btnVoltarAtv2Fase1);
        btEnunciado = findViewById(R.id.txtEnunAtv2Fase1);
        btBalao = findViewById(R.id.ImageBalao_Atv2_fase2);
        notCerto = findViewById(R.id.not_acerto);
        notErro = findViewById(R.id.not_erro);

        // Disparar o áudio após 1 segundo
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playAudio(R.raw.qual_letraessa);
            }
        }, 1000);

        // Ações dos botões
        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.qual_letraessa);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.letra_e);
            }
        });

        btVoltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirHome = new Intent(Tela_Atv2_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
                finish();
            }
        });

        // Desativando botões e suas interações
        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Let_A_Inc.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_E_Certo.setVisibility(View.INVISIBLE);
        btn_Let_Y_Inc.setVisibility(View.INVISIBLE);
        notErro.setVisibility(View.INVISIBLE);
        notCerto.setVisibility(View.INVISIBLE);

        btn_Let_A_Inc.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_E_Certo.setEnabled(false);
        btn_Let_Y_Inc.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonSelected) {
                    return;
                }
                int id = view.getId();
                buttonSelected = true;

                // Verificando qual botão foi clicado
                if (id == R.id.btn_Let_A1) {
                    btn_Let_A_Inc.setVisibility(View.VISIBLE);
                    btn_Let_A_Inc.setEnabled(true);
                    playAudio(R.raw.letra_a);

                } else if (id == R.id.btn_Let_B1) {
                    btn_Let_B_Inc.setVisibility(View.VISIBLE);
                    btn_Let_B_Inc.setEnabled(true);
                    playAudio(R.raw.letra_b);

                } else if (id == R.id.btn_Let_E1) {
                    btn_Let_E_Certo.setVisibility(View.VISIBLE);
                    btn_Let_E_Certo.setEnabled(true);
                    playAudio(R.raw.letra_e);

                } else if (id == R.id.btn_Let_Y1) {
                    btn_Let_Y_Inc.setVisibility(View.VISIBLE);
                    btn_Let_Y_Inc.setEnabled(true);
                    playAudio(R.raw.letra_y);
                }

                // Mostrar feedback visual e sonoro de acerto ou erro
                if (id == R.id.btn_Let_E1) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notCerto.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_acertou);
                        }
                    }, 1100);
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notErro.setVisibility(View.VISIBLE);
                            playAudio(R.raw.not_erro);
                        }
                    }, 1100);
                }

                // Após 3 segundos, passar para a próxima tela
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Tela_Atv2_fase1.this, Tela_Atv3_fase1.class);
                        intent.putExtra("codCrianca", codCrianca);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        };

        // Configurar os listeners para os botões
        btn_Let_A.setOnClickListener(listener);
        btn_Let_B.setOnClickListener(listener);
        btn_Let_E.setOnClickListener(listener);
        btn_Let_Y.setOnClickListener(listener);
    }

    // Função para tocar o áudio
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
