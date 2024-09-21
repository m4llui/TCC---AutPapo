package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TelaDesafio_Fase2 extends AppCompatActivity {

    ImageView btConsegui2;
    ImageView btPasso2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio_fase2);
        btConsegui2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(TelaDesafio_Fase2.this,Tela_Finalizacao_fase2.class);
                startActivity(abrirConfig);
            }
        });

        btPasso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(TelaDesafio_Fase2.this,Tela_Finalizacao_fase2.class);
                startActivity(abrirConfig);
            }
        });
    }
}





