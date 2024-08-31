package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Tela_Atv8_fase2 extends AppCompatActivity {

    private ImageView btn_Flor, btn_Flor_errado, btn_Nariz, btn_Nariz_errado, btn_Zebra, btn_Zebra_certa,
            btn_Planeta, btn_Planeta_errado, btVoltar, btEnunciado, btBalao;
    private Handler handler = new Handler();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv8_fase2);

        btn_Flor = findViewById(R.id.btn_Flor);
        btn_Flor_errado = findViewById(R.id.btn_Flor_errado);
        btn_Nariz = findViewById(R.id.btn_Nariz);
        btn_Nariz_errado = findViewById(R.id.btn_Nariz_errado);
        btn_Zebra = findViewById(R.id.btn_Zebra);
        btn_Zebra_certa = findViewById(R.id.btn_Zebra_certa);
        btn_Planeta = findViewById(R.id.btn_Planeta);
        btn_Planeta_errado = findViewById(R.id.btn_Planeta_errado);
        btVoltar = findViewById(R.id.btnVoltarAtv8Fase2);
        btBalao = findViewById(R.id.imageBalaoLetraZ);

        botoesInativados();
        setOnClickListeners();
    }

    private void botoesInativados() {
        btn_Flor_errado.setVisibility(View.INVISIBLE);
        btn_Nariz_errado.setVisibility(View.INVISIBLE);
        btn_Zebra_certa.setVisibility(View.INVISIBLE);
        btn_Planeta_errado.setVisibility(View.INVISIBLE);

        btn_Flor_errado.setEnabled(false);
        btn_Nariz_errado.setEnabled(false);
        btn_Zebra_certa.setEnabled(false);
        btn_Planeta_errado.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_Flor) {
                    btn_Flor_errado.setVisibility(View.VISIBLE);
                    btn_Flor_errado.setEnabled(true);

                } else if (id == R.id.btn_Nariz) {
                    btn_Nariz_errado.setVisibility(View.VISIBLE);
                    btn_Nariz_errado.setEnabled(true);

                } else if (id == R.id.btn_Zebra) {
                    btn_Zebra_certa.setVisibility(View.VISIBLE);
                    btn_Zebra_certa.setEnabled(true);

                } else if (id == R.id.btn_Planeta) {
                    btn_Planeta_errado.setVisibility(View.VISIBLE);
                    btn_Planeta_errado.setEnabled(true);
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv8_fase2.this, Tela_Atv1_fase2.class));
                        finish();
                    }
                }, 2000);
            }
        };

        btn_Flor.setOnClickListener(listener);
        btn_Nariz.setOnClickListener(listener);
        btn_Zebra.setOnClickListener(listener);
        btn_Planeta.setOnClickListener(listener);
    }
}
