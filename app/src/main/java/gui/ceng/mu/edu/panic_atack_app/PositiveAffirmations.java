package gui.ceng.mu.edu.panic_atack_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositiveAffirmations extends AppCompatActivity {

    private EditText messageInput;
    private Button sendButton;
    private TextView responseTextView;
    private ScrollView responseScrollView;

    // Mesaj geçmişini saklamak için bir liste
    private List<ChatRequest.Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positive_affirmations);

        // UI elemanlarını bağlama
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        responseTextView = findViewById(R.id.responseTextView);
        responseScrollView = findViewById(R.id.responseScrollView);

        // Mesaj geçmişini başlat
        messages = new ArrayList<>();

        // Sohbetin başlangıç mesajını ekle (opsiyonel)
        messages.add(new ChatRequest.Message("system", "You are a helpful assistant."));

        // "Send" butonuna tıklama olayı
        sendButton.setOnClickListener(v -> {
            String userMessage = messageInput.getText().toString();
            if (!userMessage.isEmpty()) {
                sendMessageToAPI(userMessage);
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ChatGPT API'ye mesaj gönderme
    private void sendMessageToAPI(String userMessage) {
        OpenAIApi api = OpenAIApi.create();

        // Kullanıcı mesajını geçmişe ekle
        messages.add(new ChatRequest.Message("user", userMessage));

        // İstek nesnesini oluştur
        ChatRequest request = new ChatRequest("gpt-3.5-turbo", messages);

        // API çağrısı
        api.sendMessage(request).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String botResponse = response.body().getChoices().get(0).getMessage().getContent();

                    // Bot yanıtını geçmişe ekle
                    messages.add(new ChatRequest.Message("assistant", botResponse));

                    // Yanıtı TextView'e ekleyin
                    runOnUiThread(() -> {
                        responseTextView.append("You: " + userMessage + "\n");
                        responseTextView.append("Bot: " + botResponse + "\n\n");

                        // Otomatik kaydırma için ScrollView'i en alta kaydır
                        responseScrollView.post(() -> responseScrollView.fullScroll(ScrollView.FOCUS_DOWN));
                    });
                } else {
                    Log.e("API_ERROR", "Response Code: " + response.code());
                    Toast.makeText(PositiveAffirmations.this, "Failed to get response. Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(PositiveAffirmations.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
