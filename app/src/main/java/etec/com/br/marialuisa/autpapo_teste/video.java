package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = findViewById(R.id.videoView);

        // Obtém o URI do vídeo na pasta raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testevideo);

        videoView.setVideoURI(videoUri);

        // Adiciona um MediaController para controles de reprodução
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);




        videoView.start();
    }
}
