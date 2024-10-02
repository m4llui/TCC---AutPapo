package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Login extends AppCompatActivity {

    CheckBox checkBox;
    EditText edEmail, edSenha;
    Button btLogar;
    TextView txSaibaMais;
    String tela;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        checkBox = findViewById(R.id.checkBoxLogin);
        edEmail = findViewById(R.id.edtEmailL);
        edSenha = findViewById(R.id.edtSenhaL);
        btLogar = findViewById(R.id.btnLogarL);
        txSaibaMais = findViewById(R.id.SaibaMaisColetaDadosL);

        txSaibaMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirPriv = new Intent(Tela_Login.this, Tela_Politica_Priv.class);
                abrirPriv.putExtra("login",tela);
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
                }
                else if (!email.contains("@") || !email.endsWith(".com")) {
                    edEmail.setError("Email inválido! Certifique-se que contém '@' e termina com '.com'.");
                    edEmail.requestFocus();
                }
                else if (senha.isEmpty()) {
                    edSenha.setError("Senha obrigatória!");
                    edSenha.requestFocus();
                }
                else if (senha.length() < 4 || senha.length() > 8) {
                    edSenha.setError("A senha deve ter entre 4 e 8 caracteres!");
                    edSenha.requestFocus();
                }
                else if (!checkBox.isChecked()) {
                    Toast.makeText(Tela_Login.this, "Selecione a checkbox do 'Estou ciente' para a coleta de dados!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //BANCO E IR PARA HOME
                    Toast.makeText(Tela_Login.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Lógica de login aqui, como abrir uma nova Activity
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}