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

public class Tela_Atv3_fase2 extends AppCompatActivity {

    TextView btEnunciado;
    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_DHIJ, btn_DHIJ_errado, btn_ABFH, btn_ABFH_errado, btn_ABCD, btn_ABCD_Certo,
            btn_XYZB, getBtn_XYZB_errado, btVoltar, btBalao;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv3_fase2);

        // Inicializando os botões com seus respectivos IDs
        btn_DHIJ = findViewById(R.id.btn_dhij);
        btn_DHIJ_errado = findViewById(R.id.btn_dhij_errado);
        btn_ABFH = findViewById(R.id.btn_abfh);
        btn_ABFH_errado = findViewById(R.id.btn_abfh_errado);
        btn_ABCD = findViewById(R.id.btn_abcd);
        btn_ABCD_Certo = findViewById(R.id.btn_abcd_correta);
        btn_XYZB = findViewById(R.id.btn_xyzb);
        getBtn_XYZB_errado = findViewById(R.id.btn_xyzb_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv3Fase2);
        btEnunciado = findViewById(R.id.txtEnunAtv3Fase2);
        btBalao = findViewById(R.id.BalaoAtv3);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.selecionar_sequencia);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.selecionar_sequencia);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.abcd);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv3_fase2.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });


        botoesInativados();


        setOnClickListeners();
    }

    // Método para desativar e ocultar os botões de erro e acerto
    private void botoesInativados() {
        btn_DHIJ_errado.setVisibility(View.INVISIBLE);
        btn_ABFH_errado.setVisibility(View.INVISIBLE);
        btn_ABCD_Certo.setVisibility(View.INVISIBLE);
        getBtn_XYZB_errado.setVisibility(View.INVISIBLE);

        btn_DHIJ_errado.setEnabled(false);
        btn_ABFH_errado.setEnabled(false);
        btn_ABCD_Certo.setEnabled(false);
        getBtn_XYZB_errado.setEnabled(false);
    }

    // Método para definir os listeners dos botões
    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_dhij) {
                    btn_DHIJ_errado.setVisibility(View.VISIBLE);
                    btn_DHIJ_errado.setEnabled(true);
                    playAudio(R.raw.d_h_i_j);

                } else if (id == R.id.btn_abfh) {
                    btn_ABFH_errado.setVisibility(View.VISIBLE);
                    btn_ABFH_errado.setEnabled(true);
                    playAudio(R.raw.a_b_f_h);

                } else if (id == R.id.btn_abcd) {
                    btn_ABCD_Certo.setVisibility(View.VISIBLE);
                    btn_ABCD_Certo.setEnabled(true);
                    playAudio(R.raw.abcd);

                } else if (id == R.id.btn_xyzb) {
                    getBtn_XYZB_errado.setVisibility(View.VISIBLE);
                    getBtn_XYZB_errado.setEnabled(true);
                    playAudio(R.raw.x_y_z_b);
                }

                // Aguarda 2 segundos antes de navegar para a próxima atividade
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv3_fase2.this, Tela_Atv4_fase2.class));
                        finish();
                    }
                }, 2000);
            }
        };

        // Definindo os listeners para cada botão
        btn_DHIJ.setOnClickListener(listener);
        btn_ABFH.setOnClickListener(listener);
        btn_ABCD.setOnClickListener(listener);
        btn_XYZB.setOnClickListener(listener);
    }
    private void salvarResultadoNoBanco(boolean isCorrect) {
        // Código para salvar no banco de dados se a resposta foi correta ou não
        // Exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.inserirResultado(isCorrect ? "Correto" : "Incorreto");
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