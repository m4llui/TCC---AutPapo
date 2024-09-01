package etec.com.br.marialuisa.autpapo_teste;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Tela_Finalizacao_fase2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_finalizacao_fase2);

        ImageView btnHome = findViewById(R.id.btn_home_fase2);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(Tela_Finalizacao_fase2.this, Tela_Home.class);
            startActivity(intent);
        });
    }
}
