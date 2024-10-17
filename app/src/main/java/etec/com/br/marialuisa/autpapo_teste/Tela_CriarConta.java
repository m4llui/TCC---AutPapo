package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_CriarConta extends AppCompatActivity {

    CheckBox checkBox;
    EditText edEmail, edSenha;
    Button btLogar;
    TextView txSaibaMais;
    String telaCadastro = "cadastroUsuario";
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_conta);

        checkBox = findViewById(R.id.checkBoxConta);
        edEmail = findViewById(R.id.edtEmailC);
        edSenha = findViewById(R.id.edtSenhaC);
        btLogar = findViewById(R.id.btnLogarC);
        txSaibaMais = findViewById(R.id.SaibaMaisColetaDadosC);

        txSaibaMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirPriv = new Intent(Tela_CriarConta.this, Tela_Politica_Priv.class);
                startActivity(abrirPriv);
            }
        });

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();

                if (email.isEmpty()) {
                    edEmail.setError("Email obrigatório!");
                    edEmail.requestFocus();
                } else if (!email.contains("@") || !email.endsWith(".com")) {
                    edEmail.setError("Email inválido! Certifique-se que contém '@' e termina com '.com'.");
                    edEmail.requestFocus();
                } else if (senha.isEmpty()) {
                    edSenha.setError("Senha obrigatória!");
                    edSenha.requestFocus();
                } else if (senha.length() < 4 || senha.length() > 8) {
                    edSenha.setError("A senha deve ter entre 4 e 8 caracteres!");
                    edSenha.requestFocus();
                } else if (!checkBox.isChecked()) {
                    Toast.makeText(Tela_CriarConta.this, "Selecione a checkbox do 'Estou ciente' para a coleta de dados!", Toast.LENGTH_SHORT).show();
                } else {
                    // Todas as verificações passaram
                    CadastroUsuario usuario = new CadastroUsuario(Tela_CriarConta.this, email, senha);

                    boolean usuarioExiste = usuario.consultarUsuarioPorEmail(email);

                    if (usuarioExiste) {
                        Toast.makeText(Tela_CriarConta.this, "Usuário já existe!", Toast.LENGTH_SHORT).show();
                    } else {
                        int codUsuario = usuario.cadastrarUsuario();
                        if (codUsuario != -1) {
                            Toast.makeText(Tela_CriarConta.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            // Armazenar codUsuario
                            SharedPreferences sharedPreferences = getSharedPreferences("MeuApp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("codUsuario", codUsuario);
                            editor.apply();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent abrirCadastroCrianca = new Intent(Tela_CriarConta.this, Tela_Cadastro_Crianca.class);
                                    abrirCadastroCrianca.putExtra("cadastroUsuario", true); // Envie um booleano
                                    abrirCadastroCrianca.putExtra("codUsuario",codUsuario);
                                    startActivity(abrirCadastroCrianca);
                                }
                            }, 900);
                        } else {
                            Toast.makeText(Tela_CriarConta.this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Aqui você pode implementar a lógica que deseja ao pressionar o botão de voltar
    }
}
