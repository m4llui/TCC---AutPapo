package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TelaDesafio_Fase3 extends AppCompatActivity {

    ImageView btConsegui3;
    ImageView btPasso3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio_fase3);
        btConsegui3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(TelaDesafio_Fase3.this,Tela_Finalizacao_fase3.class);
                startActivity(abrirConfig);
            }
        });

        btPasso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(TelaDesafio_Fase3.this,Tela_Finalizacao_fase3.class);
                startActivity(abrirConfig);
            }
        });
    }
}





