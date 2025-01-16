package gui.ceng.mu.edu.panic_atack_app;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class VisualGuideFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visual_guide, container, false);

        VideoView videoView = view.findViewById(R.id.video_view);
        Button playButton = view.findViewById(R.id.btn_play_video);

        // Videoların URI'lerini bir diziye ekleyin
        Uri[] videoUris = {
                Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.video_1),
                Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.video_2),
                Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.video_3),
                Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.video_4),
                Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.video_5)
        };

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

        return view;
    }
}
