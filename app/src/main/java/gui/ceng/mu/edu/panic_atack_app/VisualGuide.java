package gui.ceng.mu.edu.panic_atack_app;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class VisualGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_guide_screen);

        VideoView videoView = findViewById(R.id.video_view);
        Button playButton = findViewById(R.id.btn_play_video);

        // Videoların URI'lerini bir diziye ekleyin
        Uri[] videoUris = {
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_1),
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_2),
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_3),
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_4),
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_5)
        };


        // Rastgele bir video seçmek ve oynatmak için Play butonuna tıklama işlevi
        playButton.setOnClickListener(v -> {
            if (!videoView.isPlaying()) {
                // Rastgele bir video URI'si seç
                int randomIndex = new Random().nextInt(videoUris.length);
                Uri selectedVideo = videoUris[randomIndex];

                // Seçilen videoyu VideoView'e yükle ve başlat
                videoView.setVideoURI(selectedVideo);
                videoView.start();
            }
        });
    }
}
