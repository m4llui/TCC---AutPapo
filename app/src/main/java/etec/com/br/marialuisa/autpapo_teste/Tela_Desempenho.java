package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class Tela_Desempenho extends AppCompatActivity {

    ImageView btnCadastrar, home, desemp, config;
    ListView listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desempenho);

        btnCadastrar = findViewById(R.id.btn_Cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirCadastro = new Intent(Tela_Desempenho.this,Tela_Cadastro_Crianca.class);
                startActivity(abrirCadastro);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}