package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Sobre extends AppCompatActivity {

    ImageView btHome, btDesemp, btConfig;
    TextView email;

     String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);

        btHome = findViewById(R.id.imgHomeS);
        btDesemp = findViewById(R.id.imgDesempenhoS);
        btConfig = findViewById(R.id.imgConfigS);
        email = findViewById(R.id.txtEmailAutPapo);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtém o texto do TextView
                emailText = email.getText().toString();
                // Chama a função para enviar o email
                enviarEmail(emailText);
            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para home
                Intent abrirHome = new Intent(Tela_Sobre.this,Tela_Home.class);
                startActivity(abrirHome);
            }
        });

        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para config
                Intent abrirConfig = new Intent(Tela_Sobre.this,Tela_Config.class);
                startActivity(abrirConfig);
            }
        });

        btDesemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para Desemp
                //Intent abrirDesemp = new Intent(Tela_Sobre.this,Tela_Home.class);
                //startActivity(abrirDesemp);
            }
        });
    }
    public void enviarEmail(String emailText) {
        //QUEM RECEBE O EMAIL
        String mailto = "mailto:" + emailText +
                //QUEM RECEBE UMA CÓPIA (OPCIONAL)
                "?cc=" + "" +
                //ASSUNTO DO EMAIL
                "&subject=" + Uri.encode("Olá AutPapo") +
                //CORPO/MENSAGEM
                "&body=" + Uri.encode("Tenho uma observação!");

        //PREPARANDO A ABERTURA DO EMAIL
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        //EXECUTANDO A INTENT DE E-MAIL
        emailIntent.setData(Uri.parse(mailto));

        //CASO DÊ ERRO AO CRIAR O E-MAIL
        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Erro ao enviar o e-mail: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize o menu do aplicativo!", Toast.LENGTH_SHORT).show();
    }
}