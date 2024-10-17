package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Tela_Video_fase1 extends AppCompatActivity {

    VideoView videoView;
    ImageView btProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView1);
        btProximo = findViewById(R.id.btn_Proximo_V1);

        // Obtém o URI do vídeo na pasta raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_fase1);

        videoView.setVideoURI(videoUri);

        // Adiciona um MediaController para controles de reprodução
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirConfig = new Intent(Tela_Video_fase1.this,TelaDesafio_Fase1.class);
                startActivity(abrirConfig);
            }
        });
    }
}