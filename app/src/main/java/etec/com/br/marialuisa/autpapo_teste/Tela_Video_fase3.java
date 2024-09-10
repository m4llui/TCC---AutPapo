package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Tela_Video_fase3 extends AppCompatActivity {

    VideoView videoView;
    ImageView btProximo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_video_fase3);

        videoView = findViewById(R.id.videoView3);
        btProximo = findViewById(R.id.btn_Proximo_V3);

        // Obtém o URI do vídeo na pasta raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testevideo);

        videoView.setVideoURI(videoUri);

        // Adiciona um MediaController para controles de reprodução
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
        //ACRESCENTAR INTENT PARA O BTPROXIMO
    }
}