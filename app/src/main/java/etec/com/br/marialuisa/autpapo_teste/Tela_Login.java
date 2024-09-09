package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

        //CHECKBOX FUNCIONANDO
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
            //pode ir para a home
        }else {
            Toast.makeText(this, "Selecione a checkbox do 'Estou ciente' para a coleta de dados!", Toast.LENGTH_SHORT).show();
    }
}

    }
