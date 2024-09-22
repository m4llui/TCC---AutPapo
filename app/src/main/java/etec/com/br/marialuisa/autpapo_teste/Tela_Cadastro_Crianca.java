package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Tela_Cadastro_Crianca extends AppCompatActivity {

    Spinner select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_crianca);

        select = findViewById(R.id.spinnerNivel);

        // Crie um ArrayAdapter usando o array de strings e o layout padrão do spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveis_autismo, android.R.layout.simple_spinner_item);

        // Especifique o layout a ser usado quando a lista de escolhas aparecer
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplique o adaptador ao spinner
        select.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {

    }
}