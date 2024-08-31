package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv4_fase2 extends AppCompatActivity {

    private ImageView btn_NBFH, btn_NBFH_errado, btn_JKLM, btn_JKLM_errado, btn_EFGH, btn_EFGH_Certo,
            btn_ABCD, btn_ABCD_errado, btVoltar, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv4_fase2);

        // Inicializando os botões com seus respectivos IDs
        btn_NBFH = findViewById(R.id.btn_nbfh);
        btn_NBFH_errado = findViewById(R.id.btn_nbfh_errado);
        btn_JKLM = findViewById(R.id.btn_jklm);
        btn_JKLM_errado = findViewById(R.id.btn_jklm_errado);
        btn_EFGH = findViewById(R.id.btn_efgh);
        btn_EFGH_Certo = findViewById(R.id.btn_efgh_certo);
        btn_ABCD = findViewById(R.id.btn_abcd_atvd4);
        btn_ABCD_errado = findViewById(R.id.btn_abcd_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv4Fase2);
        btEnunciado = findViewById(R.id.imageEnunciadoAtv4Fase2);
        btBalao = findViewById(R.id.balaoAtv4);

        // Desativando e ocultando os botões de erro e acerto inicialmente
        botoesInativados();

        // Definindo os listeners para os botões
        setOnClickListeners();
    }

    // Método para desativar e ocultar os botões de erro e acerto
    private void botoesInativados() {
        btn_NBFH_errado.setVisibility(View.INVISIBLE);
        btn_JKLM_errado.setVisibility(View.INVISIBLE);
        btn_EFGH_Certo.setVisibility(View.INVISIBLE);
        btn_ABCD_errado.setVisibility(View.INVISIBLE);

        btn_NBFH_errado.setEnabled(false);
        btn_JKLM_errado.setEnabled(false);
        btn_EFGH_Certo.setEnabled(false);
        btn_ABCD_errado.setEnabled(false);
    }

    // Método para definir os listeners dos botões
    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_nbfh) {
                    btn_NBFH_errado.setVisibility(View.VISIBLE);
                    btn_NBFH_errado.setEnabled(true);

                } else if (id == R.id.btn_jklm) {
                    btn_JKLM_errado.setVisibility(View.VISIBLE);
                    btn_JKLM_errado.setEnabled(true);

                } else if (id == R.id.btn_efgh) {
                    btn_EFGH_Certo.setVisibility(View.VISIBLE);
                    btn_EFGH_Certo.setEnabled(true);

                } else if (id == R.id.btn_abcd_atvd4) {
                    btn_ABCD_errado.setVisibility(View.VISIBLE);
                    btn_ABCD_errado.setEnabled(true);
                }

                // Aguarda 2 segundos antes de navegar para a próxima atividade
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv4_fase2.this, Tela_Atv3_fase1.class));
                        finish();
                    }
                }, 2000);
            }
        };

        // Definindo os listeners para cada botão
        btn_NBFH.setOnClickListener(listener);
        btn_JKLM.setOnClickListener(listener);
        btn_EFGH.setOnClickListener(listener);
        btn_ABCD.setOnClickListener(listener);
    }
}
