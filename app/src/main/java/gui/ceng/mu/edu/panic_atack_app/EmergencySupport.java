package gui.ceng.mu.edu.panic_atack_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class EmergencySupport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_support);

        // Varsayılan olarak 4-7-8 Fragmentini yükle
        loadFragment(new TechniqueFragment());

        findViewById(R.id.btn_478_technique).setOnClickListener(v -> loadFragment(new TechniqueFragment()));
        findViewById(R.id.btn_visual_guide).setOnClickListener(v -> loadFragment(new VisualGuideFragment()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
