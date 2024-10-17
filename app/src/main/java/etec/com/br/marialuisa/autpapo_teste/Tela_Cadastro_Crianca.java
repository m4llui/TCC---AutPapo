package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.Calendar;

public class Tela_Cadastro_Crianca extends AppCompatActivity {

    Button btSalvar;
    EditText edNome, edAno, edNacionalidade;
    Spinner spGrau;
    ImageView btDesemp, btHome, btConfig;

    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_crianca);

        btSalvar = findViewById(R.id.btnSalvar);
        btDesemp = findViewById(R.id.imgDesempenhoC);
        btHome = findViewById(R.id.imgHomeC);
        btConfig = findViewById(R.id.imgConfigC);
        edNome = findViewById(R.id.edtNome);
        edAno = findViewById(R.id.edtAno);
        edNacionalidade = findViewById(R.id.edtPais);
        spGrau = findViewById(R.id.spinnerNivel);

        SharedPreferences sharedPreferences = getSharedPreferences("MeuApp", MODE_PRIVATE);
        int codUsuario = sharedPreferences.getInt("codUsuario", -1);

        // Verificando se o valor "cadastroUsuario" foi passado
        boolean cadastroUsuario = getIntent().getBooleanExtra("cadastroUsuario", false);

        if (cadastroUsuario) {
            Toast.makeText(this, "Cadastro de Usuário identificado!", Toast.LENGTH_SHORT).show();
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edNome.getText().toString();
                String grauRecebido = spGrau.getSelectedItem().toString();
                String grauNumero = grauRecebido.split(" - ")[0];
                int grau = Integer.parseInt(grauNumero);
                String anoRecebido = edAno.getText().toString();
                String nacionalidade = edNacionalidade.getText().toString();

                if (nome.isEmpty()) {
                    edNome.setError("Nome obrigatório!");
                    edNome.requestFocus();
                } else if (anoRecebido.isEmpty()) {
                    edAno.setError("Ano obrigatório!");
                    edAno.requestFocus();
                } else {
                    try {
                        int ano = Integer.parseInt(anoRecebido);
                        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);

                        if (ano > anoAtual || anoAtual - ano > 50) {
                            edAno.setError("O ano de nascimento está fora do limite de idade!");
                            edAno.requestFocus();
                        } else if (nacionalidade.isEmpty()) {
                            edNacionalidade.setError("Nacionalidade obrigatória!");
                            edNacionalidade.requestFocus();
                        } else {
                            CadastroCrianca crianca = new CadastroCrianca(Tela_Cadastro_Crianca.this, nome, grau, nacionalidade, ano);
                            long codCrianca = crianca.cadastrarCrianca(nome, grau, nacionalidade, ano, codUsuario);

                            if (codCrianca != -1) {
                                Toast.makeText(Tela_Cadastro_Crianca.this, "Cadastro da Criança Realizado com Sucesso!", Toast.LENGTH_SHORT).show();

                                //Mandando para a tela home se tiver vindo pela tela cadastro
                                if (cadastroUsuario) {
                                    handler.postDelayed(() -> {
                                        // Consulta o código da criança pelo código do usuário
                                        CadastroCrianca cadastroCrianca = new CadastroCrianca(Tela_Cadastro_Crianca.this);
                                        int codCriancaConsultado = cadastroCrianca.ConsultaCodCrianca((int) codCrianca);  // Passa o codCrianca retornado do cadastro

                                        if (codCriancaConsultado != -1) {
                                            Intent abrirHome = new Intent(Tela_Cadastro_Crianca.this, Tela_Home.class);
                                            abrirHome.putExtra("codCrianca", codCriancaConsultado);  // Passa o codCrianca consultado
                                            startActivity(abrirHome);
                                        } else {
                                            Toast.makeText(Tela_Cadastro_Crianca.this, "Erro ao consultar o código da criança!", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 1000);
                                }
                            } else {
                                Toast.makeText(Tela_Cadastro_Crianca.this, "Erro ao cadastrar a criança!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (NumberFormatException e) {
                        edAno.setError("Ano inválido!");
                        edAno.requestFocus();
                    }
                }
            }
        });

        // Navegações entre as telas
        btHome.setOnClickListener(view -> {
            Intent abrirHome = new Intent(Tela_Cadastro_Crianca.this, Tela_Home.class);
            startActivity(abrirHome);
        });

        btConfig.setOnClickListener(view -> {
            Intent abrirConfig = new Intent(Tela_Cadastro_Crianca.this, Tela_Home.class);
            startActivity(abrirConfig);
        });

        btDesemp.setOnClickListener(view -> {
            Intent abrirDesemp = new Intent(Tela_Cadastro_Crianca.this, Tela_Desempenho.class);
            startActivity(abrirDesemp);
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.niveis_autismo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrau.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        // Ação ao pressionar o botão de voltar (pode ser personalizada)
    }
}
