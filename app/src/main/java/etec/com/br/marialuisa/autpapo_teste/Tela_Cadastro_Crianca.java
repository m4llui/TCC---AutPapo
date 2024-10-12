package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Tela_Cadastro_Crianca extends AppCompatActivity {

    Spinner select;
    Button btSalvar,btDesempenho;
    EditText edNome,edAno, edNacionalidade;
    Spinner spGrau;

    private Handler handler = new Handler();

    ImageView btDesemp,btHome, btConfig, btDesempInicio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_crianca);

        btSalvar = findViewById(R.id.btnSalvar);
        btDesempenho = findViewById(R.id.btnDesemp);
        btDesemp = findViewById(R.id.imgDesempenhoC);
        btHome = findViewById(R.id.imgHomeC);
        btConfig = findViewById(R.id.imgConfigC);
        edNome = findViewById(R.id.edtNome);
        edAno = findViewById(R.id.edtAno);
        edNacionalidade = findViewById(R.id.edtPais);
        spGrau = findViewById(R.id.spinnerNivel);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edNome.getText().toString();
                String graurecebido = spGrau.getSelectedItem().toString();
                String grauNumero = graurecebido.split(" - ")[0];
                int grau = Integer.parseInt(grauNumero);
                String anorecebido = edAno.getText().toString();
                String nacionalidade = edNacionalidade.getText().toString();

                if (nome.isEmpty()) {
                    edNome.setError("nome obrigatório!");
                    edNome.requestFocus();
                } else if (anorecebido.isEmpty()) {
                    edAno.setError("ano obrigatório!");
                    edAno.requestFocus();
                } else {
                    int ano = Integer.parseInt(anorecebido);
                    if (nacionalidade.isEmpty()) {
                        edNacionalidade.setError("nacionalidade obrigatória!");
                        edNacionalidade.requestFocus();
                    } else {

                        CadastroCrianca usuariologin = new CadastroCrianca(Tela_Cadastro_Crianca.this, nome, grau, nacionalidade, ano);
                        boolean usuarioLogado = usuariologin.cadastrarCrianca(nome, grau, nacionalidade, ano);

                        if (usuarioLogado) {
                            Toast.makeText(Tela_Cadastro_Crianca.this, "Cadastro da Criança Realizado com Sucesso!", Toast.LENGTH_SHORT).show();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Intent abrirDesemp = new Intent(Tela_Cadastro_Crianca.this, Tela_Desempenho.class);
                                    startActivity(abrirDesemp);
                                }
                            }, 1000);
                        } else {
                            Toast.makeText(Tela_Cadastro_Crianca.this, "Erro ao cadastrar a criança!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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

        btDesemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirDesemp= new Intent(Tela_Cadastro_Crianca.this,Tela_Desempenho.class);
                startActivity(abrirDesemp);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.niveis_autismo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrau.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {

    }
}