package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.logoInicio);

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Carregamento.class);
                startActivity(intent);
                finish();
            }
        }, 4000); // Tempo total de exibição da logo
    }
}
