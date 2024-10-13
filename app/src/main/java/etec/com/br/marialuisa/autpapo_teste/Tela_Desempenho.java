package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Tela_Desempenho extends AppCompatActivity {

    ImageView btnCadastrar, home, config, btDesempInicio;
    ListView listaDados;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desempenho);

        btnCadastrar = findViewById(R.id.btn_Cadastrar);
        listaDados = findViewById(R.id.listaCriancas);
        home = findViewById(R.id.imgHomeD);
        config= findViewById(R.id.imgConfigD);
        btDesempInicio = findViewById(R.id.imgDesempenhoD);

        CadastroCrianca cadastroCrianca = new CadastroCrianca(Tela_Desempenho.this);
        ArrayList<SpannableString> listaCriancas = cadastroCrianca.consultaCrianca(this);

        // Usar um ArrayAdapter para conectar a lista ao ListView
        ArrayAdapter<SpannableString> adapter = new ArrayAdapter<>(this, R.layout.lista_dados, R.id.textViewItem, listaCriancas);
        listaDados.setAdapter(adapter);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirCadastro = new Intent(Tela_Desempenho.this,Tela_Cadastro_Crianca.class);
                startActivity(abrirCadastro);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirHome = new Intent(Tela_Desempenho.this,Tela_Home.class);
                startActivity(abrirHome);
            }
        });
       config.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent abrirConfig = new Intent(Tela_Desempenho.this,Tela_Config.class);
               startActivity(abrirConfig);
           }
       });
        btDesempInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirDesemp = new Intent(Tela_Desempenho.this,Tela_Desempenho.class);
                startActivity(abrirDesemp);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}