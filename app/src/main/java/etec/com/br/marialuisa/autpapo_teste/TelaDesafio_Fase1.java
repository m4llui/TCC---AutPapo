package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TelaDesafio_Fase1 extends AppCompatActivity {

    ImageView btConsegui;
    ImageView btPasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_desafio_fase1);
        btConsegui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(TelaDesafio_Fase1.this,Tela_Finalizacao_fase1.class);
                startActivity(abrirConfig);
            }
        });

        btPasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(TelaDesafio_Fase1.this,Tela_Finalizacao_fase1.class);
                startActivity(abrirConfig);
            }
        });
    }
}




