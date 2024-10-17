package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Tela_Desempenho extends AppCompatActivity {

    ImageView btnCadastrar, home, config, btDesempInicio;
    ListView listaDados;

    int codigoC;
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

        //Recuperando o codUsuario
        SharedPreferences sharedPreferences = getSharedPreferences("MeuApp", Context.MODE_PRIVATE);
        int codUsuario = sharedPreferences.getInt("codUsuario", -1);

        CadastroCrianca cadastroCrianca = new CadastroCrianca(Tela_Desempenho.this);

        ArrayList<Integer> codCriancaList = new ArrayList<>(); // Lista para armazenar codCrianca
        ArrayList<SpannableString> listaCriancas = cadastroCrianca.consultaCriancaPorUsuario(this, codUsuario, codCriancaList);

        // Usar um ArrayAdapter para conectar a lista ao ListView (FORMATAÇÃO)
        ArrayAdapter<SpannableString> adapter = new ArrayAdapter<>(this, R.layout.lista_dados, R.id.textViewItem, listaCriancas);
        listaDados.setAdapter(adapter);

        // CLIQUE NA LISTVIEW PARA IR PARA OS ACERTOS E ERROS DE CADA CRIANÇA
        listaDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obter o codCriança correspondente à posição clicada
                int codCriança = codCriancaList.get(position);
                Intent intent = new Intent(Tela_Desempenho.this, Tela_AcertosErros.class);
                intent.putExtra("codCrianca", codCriança);
                startActivity(intent);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirDesempenho = new Intent(Tela_Desempenho.this,Tela_Cadastro_Crianca.class);
                startActivity(abrirDesempenho);
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