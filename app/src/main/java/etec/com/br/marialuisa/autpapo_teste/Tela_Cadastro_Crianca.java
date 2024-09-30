package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class Tela_Cadastro_Crianca extends AppCompatActivity {

    Spinner select;
    Button btSalvar,btDesempenho;

    ImageView btDesemp,btHome, btConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_crianca);

        select = findViewById(R.id.spinnerNivel);
        btSalvar = findViewById(R.id.btnSalvar);
        btDesempenho = findViewById(R.id.btnDesemp);
        //btDesemp = findViewById(R.id.imgDesempenhoC);
        btHome = findViewById(R.id.imgHomeC);
        btConfig = findViewById(R.id.imgConfigC);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirHome = new Intent(Tela_Cadastro_Crianca.this,Tela_Home.class);
                startActivity(abrirHome);
            }
        });
        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirConfig = new Intent(Tela_Cadastro_Crianca.this,Tela_Home.class);
                startActivity(abrirConfig);
            }
        });

        btDesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirAcertosErros = new Intent(Tela_Cadastro_Crianca.this,Tela_AcertosErros.class);
                startActivity(abrirAcertosErros);
            }
        });

        // Crie um ArrayAdapter usando o array de strings e o layout padrão do spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.niveis_autismo, android.R.layout.simple_spinner_item);
        // Especificando o layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {

    }
}