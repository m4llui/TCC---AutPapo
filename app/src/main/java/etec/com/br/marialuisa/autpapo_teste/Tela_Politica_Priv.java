package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Tela_Politica_Priv extends AppCompatActivity {

    ImageView btnVoltar;
    String telaRecebida;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_politica_priv);

        btnVoltar = findViewById(R.id.btnVoltarP);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().hasExtra("login")) {
                    // Valor recebido da Tela_Login
                    Intent abrirLogin = new Intent(Tela_Politica_Priv.this, Tela_Login.class);
                    startActivity(abrirLogin);

                } else if (getIntent().hasExtra("conta")) {
                    Intent abrirLogin = new Intent(Tela_Politica_Priv.this, Tela_CriarConta.class);
                    startActivity(abrirLogin);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}