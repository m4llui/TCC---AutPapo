package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Tela_Config extends AppCompatActivity {

    ImageView btSobre, btAjuda, btHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_config);


        btSobre = findViewById(R.id.btn_sobre);
        btAjuda = findViewById(R.id.btn_ajuda);
        btHome = findViewById(R.id.btn_home_config);

        btSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Tela_Config.this, Tela_Sobre.class);
                startActivity(intent);
            }
        });

        btAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Tela_Config.this, Tela_Ajuda.class);
                startActivity(intent);
            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Tela_Config.this, Tela_Home.class);
                startActivity(intent);
            }
        });
    }
}
