package gui.ceng.mu.edu.panic_atack_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GuideScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_screen);

        Button btnPositiveAffirmations = findViewById(R.id.btn_positive_affirmations);
        Button btnVisualGuide = findViewById(R.id.btn_visual_guide);

        // Positive Affirmations sayfasına yönlendir
        btnPositiveAffirmations.setOnClickListener(v -> {
            Intent intent = new Intent(GuideScreen.this, PositiveAffirmations.class);
            startActivity(intent);
        });

        // Visual Guide sayfasına yönlendir
        btnVisualGuide.setOnClickListener(v -> {
            Intent intent = new Intent(GuideScreen.this, VisualGuide.class);
            startActivity(intent);
        });
    }
}
