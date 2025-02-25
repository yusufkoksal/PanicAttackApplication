package gui.ceng.mu.edu.panic_atack_app;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TechniqueFragment extends Fragment {

    private TextView timerText;
    private Button startButton;
    private Button audioButton;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private int step = 0;
    private ObjectAnimator animator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_478_technique, container, false);

        timerText = view.findViewById(R.id.tv_timer);
        startButton = view.findViewById(R.id.btn_start);
        audioButton = view.findViewById(R.id.btn_audio);
        progressBar = view.findViewById(R.id.progress_circular);

        handler = new Handler();

        // ProgressBar animasyonu
        animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(0);
        animator.setRepeatCount(0);

        // Ses dosyası çalma
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.breathing_audio);
        audioButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                audioButton.setText("Play Audio Guide");
            } else {
                mediaPlayer.start();
                audioButton.setText("Pause Audio Guide");
            }
        });

        // Egzersiz Başlatma
        startButton.setOnClickListener(v -> startBreathingExercise());

        return view;
    }

    private void startBreathingExercise() {
        step = 0;
        progressBar.setProgress(0);
        animator.setDuration(0);
        handler.post(breathingRunnable);
    }

    private final Runnable breathingRunnable = new Runnable() {
        @Override
        public void run() {
            switch (step) {
                case 0:
                    timerText.setText("Breathe In");
                    startCountdown(4);
                    animateProgressBar(0, 40, 4000);
                    handler.postDelayed(() -> timerText.setText("Breathe In - Complete!"), 4000);
                    step++;
                    handler.postDelayed(this, 6000);
                    break;
                case 1:
                    timerText.setText("Hold Breath");
                    startCountdown(7);
                    animateProgressBar(40, 70, 7000);
                    handler.postDelayed(() -> timerText.setText("Hold Breath - Complete!"), 7000);
                    step++;
                    handler.postDelayed(this, 9000);
                    break;
                case 2:
                    timerText.setText("Exhale");
                    startCountdown(8);
                    animateProgressBar(70, 100, 8000);
                    handler.postDelayed(() -> timerText.setText("Exhale - Complete!"), 8000);
                    step++;
                    handler.postDelayed(this, 10000);
                    break;
                default:
                    timerText.setText("Exercise Complete!");
                    progressBar.setProgress(100);
                    break;
            }
        }
    };

    private void animateProgressBar(int start, int end, int duration) {
        animator.setDuration(duration);
        animator.setIntValues(start, end);
        animator.start();
    }

    private void startCountdown(int seconds) {
        new CountDownTimer(seconds * 1000L, 1000) {
            int remainingTime = seconds;

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(--remainingTime));
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(breathingRunnable);
    }
}
