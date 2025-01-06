package gui.ceng.mu.edu.panic_atack_app;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// OpenAI API'yi tanımlıyoruz
public interface OpenAIApi {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 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"
    })
    @POST("v1/chat/completions")
    Call<ChatResponse> sendMessage(@Body ChatRequest request);

    // Retrofit oluşturucu
    static OpenAIApi create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(OpenAIApi.class);
    }
}
