package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.content.Context;
import android.content.SharedPreferences;


public class Carregamento extends AppCompatActivity {

    ImageView imageView;
    private SharedPreferences sharedPreferences;
    private boolean primeiroUso;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);
        // Cria um Handler para postar uma mensagem após 2 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Cria a Intent para abrir a nova tela (Carregando.java)
                // Inicializa o SharedPreferences
                sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

                // Verifica se é a primeira vez que o aplicativo está sendo executado
                primeiroUso = sharedPreferences.getBoolean("is_first_time", true);

                if (primeiroUso) {
                    // É a primeira vez que o aplicativo está sendo executado
                    // Executa qualquer lógica que você deseja fazer na primeira execução
                    Intent abrirExplicação =  new Intent(Carregamento.this, Tela_Explicacao.class);
                    startActivity(abrirExplicação);

                    // Atualiza o SharedPreferences para indicar que o aplicativo já foi executado
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("is_first_time", false);
                    editor.apply();
                } else {
                    // O aplicativo já foi executado anteriormente
                    Intent abrirHome =  new Intent(Carregamento.this, Tela_Explicacao.class);
                    startActivity(abrirHome);

                }
            }
        }, 5000); // Atraso de 2 segundos (2000 milissegundos)

        imageView = findViewById(R.id.T1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2E7EF5"));
        }

        // Configura a opacidade inicial da ImageView para 0 (totalmente transparente)
        imageView.setAlpha(0f);

        // Aumenta a opacidade gradualmente para 1 (totalmente visível)
        imageView.animate()
                .alpha(1f)
                .setDuration(3000) // Duração da animação em milissegundos
                .start();


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2E7EF5"));
        }

            }

    @Override
    public void onBackPressed() {

    }
}
